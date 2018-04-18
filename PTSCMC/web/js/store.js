//thuhc
function loadMsvTab(funcHandle) {
    callAjax('msvTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        displayTabs("msvTabs","tabChild",msvTabHandle);
        if (funcHandle==null) {
            loadMsvList();
        } else {
            funcHandle();
        }
    });
}

function msvTabHandle(child) {
    if (child.id=='tabChild1') {
        loadMsvList();
    } else if (child.id=='tabChild2') {
        loadDmvList();
    } else if (child.id=='tabChild3') {
        loadMrvList();
    }
}

function loadMsvList(params) {
    callAjaxEx('msvPanel.do','tabChild1','searchMsv.do','msvList',params);
    return false;
}
function loadMsvListSort(params) {    
    callAjaxEx('searchMsv.do','msvList',null,null,params);
    return false;
}
function msvMrirForm(msvId) {
    var url="msvMrirForm.do";
    if (msvId!=null) {
        url=url+"?msvId="+msvId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tabChild1');        
        msvForm(msvId);
        msvId=null;
        url=null;
    });
    return false;
}

function msvStoreChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var stoId = cbx.options.item(idx).value;
    var mrirId = document.forms['msvForm'].mrirId;
    if (mrirId!=null) {
        mrirId = mrirId.value;
        callAjax("msvMaterialList.do?mrirId=" + mrirId+"&stoId="+stoId,'msvMaterialList',null,null)
        mrirId = null;
    }
    stoId = null;
    return false;
}

function msvForm(msvId,mrirId,divParent,mrirCbm) {
    var url="msvForm.do";
    if (msvId!=null) {
        url=url+"?msvId="+msvId;
    } else {
        url += "?type=0";
        if (mrirId!=null) {
            url=url+"&mrirId="+mrirId;
        }
    }
    if (divParent==null) {
        divParent = 'tabChild1';
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,divParent);
        if(mrirCbm!=null) whichUseChangedMsv(mrirCbm,null);
        msvId=null;
        url=null;
        divParent = null;
        var price=document.forms['msvForm'].price;
        var quantity=document.forms['msvForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < price.length; i++) {
                    tryNumberFormat(price[i]);
                    tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(price);
                tryNumberFormat(quantity);
            }
        }
        price=null;
        quantity=null;
    });
    return false;
}

function selectMrir4Msv(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    msvForm(null,value,null,cbx);
    value = null;
    return false;

}

function whichUseChangedMsv(list,msvId){
    if(list.selectedIndex==-1) return false;
    var mrirId=null;
        mrirId=document.forms['msvForm'].mrirId;
    mrirId = mrirId.options[mrirId.selectedIndex].value;
    getWhichUseMsv(mrirId,list.options[list.selectedIndex].value,msvId);
    list=null;
    mrirId=null;
    return false;
}
function getWhichUseMsv(mrirId,whichUse,msvId){
    var url="whichUseMsvList.do?whichUse="+whichUse;
    if(mrirId!=null) url+="&mrirId="+mrirId;
    if(msvId!=null) url+="&msvId="+msvId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'whichUseSpan');
        //msvRfmChanged(document.forms['msvForm'].mrirId);
    });
}

function calculateMsvRow(id) {
    var quantityField = document.getElementById("quantity_" + id);
    var priceField = document.getElementById("price_" + id);
    var totalField = document.getElementById("total_" + id);
    if (quantityField==null ||
        priceField==null ||
        totalField==null)
        return false;
    totalField.value = parseFloat(quantityField.value) * parseFloat(priceField.value);
    tryNumberFormat(totalField);
}

function removeMsvRow(id) {
    var tableId = 'msvMaterial';
    var rowId = "msv_row_" + id;
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;
    if (rowCount==3) {
        alert('Table cannot empty');
        return false;
    }
    for(var i=0; i<rowCount; i++) {
        var row = table.rows[i];
        //var chkbox = row.cells[0].childNodes[0];
        //if(null != chkbox && true == chkbox.checked) {
        if(row.id == rowId) {
            table.deleteRow(i);
            rowCount--;
            i--;
        }
    }
    return false;
}

function saveMsv1() {
    if(scriptFunction=="saveMsv1()") return false;
    if (!isGreaterThan(document.forms['msvForm'].msvId,0)) {
        var cbxMrir = document.forms['msvForm'].mrirId;
        if (cbxMrir.selectedIndex==0) {
            alert("Vui l\u00F2ng ch\u1ECDn MRIR");
            cbxMrir.focus();
            cbxMrir = null;
            return false;
        }
        cbxMrir = null;
    }
    var msvNumber = document.forms['msvForm'].msvNumber;
    var msvId = document.forms['msvForm'].msvId.value;
    if(msvNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u");
        msvNumber.focus();
        msvNumber=null;
        return false;
    }

    msvNumber=null;
    scriptFunction="saveMsv1()";
    callAjaxCheckError("saveMsv.do",null,document.forms['msvForm'],function(data) {
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //loadMsvList();
        msvForm(msvId);
    });
    return false;
}

function loadEmsvTab(funcHandle) {
    callAjax('emsvTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        displayTabs("emsvTabs","tabChild",emsvTabHandle);
        if (funcHandle==null) {
            loadEmsvList();
        } else {
            funcHandle();
        }
    });
}

function emsvTabHandle(child) {
    if (child.id=='tabChild1') {
        loadEmsvList();
    } else if (child.id=='tabChild2') {
        loadEdmvList();
    }
}
function loadEmsvList(params) {
    callAjaxEx('emsvPanel.do','tabChild1','searchEmsv.do','emsvList',params);
    return false;
}
function loadEmsvListSort(params) {
    callAjaxEx('searchEmsv.do','emsvList',null,null,params);
    return false;
}
function loadEdmvList(params) {
    callAjaxEx('edmvPanel.do','tabChild2','searchEdmv.do','edmvList',params);
    return false;
}
function loadEdmvListSort(params) {
    callAjaxEx('searchEdmv.do','edmvList',null,null,params);
    return false;
}
function searchMsv(){
    var checkflag = true;
    var form=document.forms['searchSimpleMsv'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchMsv.do","msvList",form,null);
    form=null;
    return false;
}

function delMsvs() {
    var checkflag = false;
    var msvId = document.forms['msvListForm'].msvId;
    if(msvId==null) return false;
    if (msvId.length!=null) {
        for (i = 0; i < msvId.length; i++) {
            if (msvId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = msvId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError("delMsv.do","msvList",document.forms['msvListForm'],null);
    msvId=null;
    return false;
}

// js xu ly phieu nhap tra kho
function loadMrvList(params) {
    callAjaxEx('mrvPanel.do','tabChild3','searchMrv.do','mrvList',params);
    return false;
}
function loadMrvListSort(params) {
    callAjaxEx('searchMrv.do','mrvList',null,null,params);
    return false;
}
function mrvMrirForm(mrvId) {
    var url="mrvMrirForm.do";
    if (mrvId!=null) {
        url=url+"?mrvId="+mrvId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tabChild3');
        mrvForm(mrvId);
        mrvId=null;
        url=null;
    });
    return false;
}

function mrvForm(mrvId,mrirId,divParent) {
    var url="msvForm.do";
    if (mrvId!=null) {
        url=url+"?msvId="+mrvId;
    } else {
        url += "?type=1";
        if (mrirId!=null) {
            url=url+"&mrirId="+mrirId;
        }
    }
    if (divParent==null) {
        divParent = 'tabChild3';
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,divParent);
        mrvId=null;
        url=null;
        divParent = null;
    });
    return false;
}

function delMrvs() {
    var checkflag = false;
    var msvId = document.forms['mrvListForm'].msvId;
    if(msvId==null) return false;
    if (msvId.length!=null) {
        for (i = 0; i < msvId.length; i++) {
            if (msvId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = msvId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError("delMrv.do","mrvList",document.forms['mrvListForm'],null);
    msvId=null;
    return false;
}

function selectMrir4Mrv(cbx,idx) {
    if(idx < 0) return false;
    var value = cbx.options.item(idx).value;
    mrvForm(null,value);
    value = null;
    return false;

}

function selectMrir4Dmv(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    dmvForm(null,value);
    value = null;
    return false;

}

function saveMrv() {
    if(scriptFunction=="saveMrv()") return false;
    if (!isGreaterThan(document.forms['mrvForm'].msvId,0)) {
        var cbxMrir = document.forms['mrvForm'].mrirId;
        if (cbxMrir.selectedIndex==0) {
            alert("Vui l\u00F2ng ch\u1ECDn MRIR");
            cbxMrir.focus();
            cbxMrir = null;
            return false;
        }
        cbxMrir = null;
    }
    var msvNumber = document.forms['mrvForm'].msvNumber;
    if(msvNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u");
        msvNumber.focus();
        msvNumber=null;
        return false;
    }
    msvNumber=null;
    scriptFunction="saveMrv()";
    callAjaxCheckError("saveMrv.do",null,document.forms['mrvForm'],function(data) {
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //loadMrvList();
    },'mrvErrorMessageDiv');
    return false;
}

function mrvOrgChange(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("mrvCbxEmpList.do?orgId=" + value,'mrvEmpListCbx',null,null)
    return false;

}

//xu ly phieu nhap xuat thang hang hoa cong t y
function loadDmvList(params) {
    callAjaxEx('dmvPanel.do','tabChild2','searchDmv.do','dmvList',params);
    return false;
}
function loadDmvListSort(params) {
    callAjaxEx('searchDmv.do','dmvList',null,null,params);
    return false;
}
function dmvDnForm(dmvId) {
    var url="dmvDnForm.do";
    if (dmvId!=null) {
        url=url+"?dmvId="+dmvId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tabChild2');
        dmvForm(dmvId);
        dmvId=null;
        url=null;
    });
    return false;
}

function dmvForm(msvId,mrirId) {
    var url="msvForm.do";
    if (msvId!=null) {
        url=url+"?msvId="+msvId;
    } else {
        url = url + "?type=2"
        if (mrirId!=null) {
            url = url + "&mrirId=" + mrirId;
        }
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tabChild2');
        msvId=null;
        url=null;
        mrirId = null;
    });
    return false;
}

function dmvDnChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("cbxRequestList.do?dnId=" + value,null,null,function(data) {
        setAjaxData(data,'dmvRequestListCbx');
        dmvForm();
    });
    value = null;
    return false;
}

function dmvReqChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var dnField = document.forms['dmvForm'].dnId;
    var dnId = dnField.options.item(dnField.selectedIndex).value;
    var reqId = cbx.options.item(idx).value;
    dmvForm(null,dnId,reqId);
    dnField = null;
    dnId = null;
    reqId = null;
    return false;
}

function saveDmv() {
    if(scriptFunction=="saveDmv()") return false;
    if (!isGreaterThan(document.forms['dmvForm'].msvId,0)) {
        var cbxMrir = document.forms['dmvForm'].mrirId;
        if (cbxMrir.selectedIndex==0) {
            alert("Vui l\u00F2ng ch\u1ECDn MRIR");
            cbxMrir.focus();
            cbxMrir = null;
            return false;
        }
        cbxMrir = null;
    }
    var msvNumber = document.forms['dmvForm'].msvNumber;
    if(msvNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u");
        msvNumber.focus();
        msvNumber=null;
        return false;
    }
    msvNumber=null;
    var cbxReceiveEmpId = document.forms['dmvForm'].receiveEmpId;
    if (cbxReceiveEmpId.selectedIndex==0) {
        alert("Vui l\u00F2ng ch\u1ECDn Ng\u01B0\u1EDDi nh\u1EADn");
        cbxReceiveEmpId.focus();
        cbxReceiveEmpId = null;
        return false;
    }

    if(isBlank(document.forms['dmvForm'].dmvOrder,"Vui l\u00F2ng nh\u1EADp L\u1EC7nh nh\u1EADp xu\u1EA5t th\u1EB3ng")) {
        return false;
    }
    cbxReceiveEmpId = null;
    scriptFunction="saveDmv()";
    callAjaxCheckError("saveDmv.do",null,document.forms['dmvForm'],function(data) {
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //loadDmvList();
    },'dmvErrorMessageDiv');
    return false;
}

function dmvRemoveMatFromTable() {
    //    var tableId = 'dmvMaterial';
    //    var table = document.getElementById(tableId);
    //    var rowCount = table.rows.length;
    //    if (rowCount==3) {
    //        alert('Table cannot empty');
    //        return false;
    //    }
    //    var rows = document.getElementsByName("");
    //    var rowId = "dmv_row_" + id;
    //    for(var i=0; i<rowCount; i++) {
    //        var row = table.rows[i];
    //        //var chkbox = row.cells[0].childNodes[0];
    //        //if(null != chkbox && true == chkbox.checked) {
    //        if(row.id == rowId) {
    //            table.deleteRow(i);
    //            rowCount--;
    //            i--;
    //        }
    //    }
    return false;
}

function dmvAddMatToTable() {
    var dmvForm = document.forms['dmvForm'];
    var cbxMaterialList = dmvForm.cbxMaterialList;
    if (cbxMaterialList!=null) {
        var detId= cbxMaterialList.options[cbxMaterialList.selectedIndex].value;
        if (detId!=null) {
            var arrDetId = dmvForm.detId;
            if (arrDetId!=null) {
                if (arrDetId.length!=null) {
                    for (i=0;i<arrDetId.length;i++) {
                        if(detId==arrDetId[i].value){
                            alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                            return false;
                        }
                    }
                } else {
                    if (detId==arrDetId.value) {
                        alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                        return false;
                    }
                }
            }
        //insert vao
        }
    }

    return false;
}


function emsvEmrirForm(emsvId) {
    var url="emsvEmrirForm.do";
    if (emsvId!=null) {
        url=url+"?emsvId="+emsvId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tabChild1');
        emsvForm(emsvId);
        emsvId=null;
        url=null;
    });
    return false;
}

function emsvForm(emsvId,emrirId) {
    var url="emsvForm.do";
    if (emsvId!=null) {
        url=url+"?emsvId="+emsvId;
    } else {
        if (emrirId!=null) {
            url=url+"?emrirId="+emrirId;
        }
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'emsvDetail');
        emsvId=null;
        url=null;
    });
    return false;
}

function searchEmsv(){
    var checkflag = true;
    var form=document.forms['searchSimpleEmsv'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchEmsv.do","emsvList",form,null);
    form=null;
    return false;
}

function selectEmrir4Emsv(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    emsvForm(null,value);
    value = null;
    return false;
}

function saveEmsv() {
    if(scriptFunction=="saveEmsv()") return false;
    if (!isGreaterThan(document.forms['emsvForm'].emsvId,0)) {
        var cbxMrir = document.forms['emsvForm'].emrirId;
        if (cbxMrir.selectedIndex==0) {
            alert("Vui l\u00F2ng ch\u1ECDn MRIR");
            cbxMrir.focus();
            cbxMrir = null;
            return false;
        }
        cbxMrir = null;
    }
    var emsvNumber = document.forms['emsvForm'].emsvNumber;
    if(emsvNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u");
        emsvNumber.focus();
        emsvNumber=null;
        return false;
    }
    emsvNumber=null;
    scriptFunction="saveEmsv()";
    callAjaxCheckError("saveEmsv.do",null,document.forms['emsvForm'],function(data) {
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //loadEmsvList();
    });
    return false;
}

//xu ly phieu nhap xuat thang hang hoa cong t y

function edmvEdnForm(edmvId) {
    var url="edmvEdnForm.do";
    if (edmvId!=null) {
        url=url+"?edmvId="+edmvId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tabChild2');
        edmvForm(edmvId);
        edmvId=null;
        url=null;
    });
    return false;
}

function edmvForm(edmvId,ednId) {
    var url="edmvForm.do";
    if (edmvId!=null) {
        url=url+"?edmvId="+edmvId;
    } else {
        if (ednId!=null) {
            url = url + "?ednId=" + ednId;
        }
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'edmvDetail');
        edmvId=null;
        url=null;
    });
    return false;
}

function edmvEdnChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var ednId = cbx.options.item(idx).value;
    edmvForm(null,ednId);
    ednId = null;
    return false;
}

function saveEdmv() {
    if(scriptFunction=="saveEdmv()") return false;
    if (!isGreaterThan(document.forms['edmvForm'].edmvId,0)) {
        var cbxDn = document.forms['edmvForm'].ednId;
        if (cbxDn.selectedIndex==0) {
            alert("Vui l\u00F2ng ch\u1ECDn Th\u00F4ng b\u00E1o giao h\u00E0ng");
            cbxDn.focus();
            cbxDn = null;
            return false;
        }
        cbxDn = null;
    }
    var receiveEmpId = document.forms['edmvForm'].receiveEmpId;
    if (receiveEmpId.selectedIndex==0) {
        alert("Vui l\u00F2ng ch\u1ECDn Ng\u01B0\u1EDDi nh\u1EADn");
        receiveEmpId.focus();
        receiveEmpId = null;
        return false;
    }

    if(isBlank(document.forms['edmvForm'].edmvNumber,"Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u")){
        return false;
    }
    if(isBlank(document.forms['edmvForm'].dmvOrder,"Vui l\u00F2ng nh\u1EADp L\u1EC7nh nh\u1EADp xu\u1EA5t th\u1EB3ng")) {
        return false;
    }

    receiveEmpId = null;
    scriptFunction="saveEdmv()";
    callAjaxCheckError("saveEdmv.do",null,document.forms['edmvForm'],function(data) {
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //loadEdmvList();
    },'edmvErrorMessageDiv');
    return false;
}

function edmvOrgChange(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("edmvCbxEmpList.do?orgId=" + value,'edmvEmpListCbx',null,null)
    return false;

}

function dmvCreateMrir()
{
    var dmvForm = document.forms['dmvForm'];
    if (isGreaterThan(dmvForm.mrirId,0)) {
        window.location='mrirForm.do?mrirId=' + dmvForm.mrirId.value;
        dmvForm = null;
        return false;
    }

    if (isBlank(dmvForm.mrirNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 MRIR')) {
        dmvForm = null;
        return false;
    }
    //call function create MRIR
    callAjaxCheckError("createMrirFromDmv.do",null,dmvForm,function(data) {
        window.location='mrirForm.do?mrirId=' + data;
    },'dmvErrorMessageDiv');
    dmvForm = null;
    return false;
}


function dmvCreateMsv()
{
    var dmvForm = document.forms['dmvForm'];
    if (isGreaterThan(dmvForm.msvId,0)) {
        dijit.byId('tabChild1').isLoaded='true';
        dijit.byId('msvTabs').selectChild('tabChild1');
        msvMrirForm(dmvForm.msvId.value);

        dmvForm = null;
        return false;
    }

    if (isGreaterThan(dmvForm.mrirId,0)==false) {
        alert('Vui l\u00F2ng t\u1EA1o Phi\u1EBFu MRIR tr\u01B0\u1EDBc!');
        dmvForm = null;
        return false;
    }

    if (isBlank(dmvForm.msvNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 Phi\u1EBFu xu\u1EA5t kho')) {
        dmvForm = null;
        return false;
    }

    //call unction create MSV
    callAjaxCheckError("createMsvFromDmv.do",null,dmvForm,function(data) {
        dijit.byId('tabChild1').isLoaded='true';
        dijit.byId('msvTabs').selectChild('tabChild1');
        msvMrirForm(data);
    },'dmvErrorMessageDiv');
    dmvForm = null;
    return false;
}

function dmvCreateRfm()
{
    var dmvForm = document.forms['dmvForm'];
    if (isGreaterThan(dmvForm.rfmId,0)) {
        //ham nay la ham load form RFM
        addRfm(dmvForm.rfmId.value,0);
        dmvForm = null;
        return false;
    }

    if (isGreaterThan(dmvForm.msvId,0)==false) {
        alert('Vui l\u00F2ng t\u1EA1o Phi\u1EBFu NK tr\u01B0\u1EDBc!');
        dmvForm = null;
        return false;
    }

    if (isBlank(dmvForm.rfmNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 Phi\u1EBFu y\u00EAu c\u1EA7u xu\u1EA5t kho')) {
        dmvForm = null;
        return false;
    }

    //call unction create RFM
    callAjaxCheckError("createRfmFromDmv.do",null,dmvForm,function(data) {
        //ham nay la ham load form RFM
        addRfm(data,0);
    },'dmvErrorMessageDiv');
    dmvForm = null;
    return false;
}

function dmvCreateMiv()
{
    var dmvForm = document.forms['dmvForm'];
    if (isGreaterThan(dmvForm.mivId,0)) {
        mivForm(dmvForm.mivId.value,1);
        dmvForm = null;
        return false;
    }

    if (isGreaterThan(dmvForm.rfmId,0)==false) {
        alert('Vui l\u00F2ng t\u1EA1o Phi\u1EBFu y\u00EAu c\u1EA7u xu\u1EA5t kho tr\u01B0\u1EDBc!');
        dmvForm = null;
        return false;
    }
    if (isBlank(dmvForm.mivNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 Phi\u1EBFu xu\u1EA5t kho')) {
        dmvForm = null;
        return false;
    }
    //call unction create MIV
    callAjaxCheckError("createMivFromDmv.do",null,dmvForm,function(data) {
        //ham nay la ham load form RFM
        mivForm(data,1);
    },'dmvErrorMessageDiv');
    dmvForm = null;
    return false;
}

function printMsvForm(){
    var msvId=document.forms['msvForm'].msvId;
    if(msvId!=null) callServer('msvFormPrint.do?msvId='+msvId.value);
    msvId=null;
    return false;
}

function printMrvForm(){
    var msvId=document.forms['mrvForm'].msvId;
    if(msvId!=null) callServer('mrvFormPrint.do?msvId='+msvId.value);
    msvId=null;
    return false;
}

function printDmvForm(){
    var msvId=document.forms['dmvForm'].msvId;
    if(msvId!=null) callServer('dmvFormPrint.do?msvId='+msvId.value);
    msvId=null;
    return false;
}

function printEmsvForm(){
    var emsvId=document.forms['emsvForm'].emsvId;
    if(emsvId!=null) callServer('emsvFormPrint.do?emsvId='+emsvId.value);
    emsvId=null;
    return false;
}

function printEdmvForm(){
    var edmvId=document.forms['edmvForm'].edmvId;
    if(edmvId!=null) callServer('edmvFormPrint.do?edmvId='+edmvId.value);
    edmvId=null;
    return false;
}

function mrirFormFromXML(mrirId)
{
    getMrirTabs(function(){
        loadMrir(mrirId);
        dijit.byId('tab1').isLoaded='true';
        dijit.byId('mrirTabs').selectChild('tab1');
    });
}

function emrirFormFromXML(emrirId)
{
    getEmrirTabs(function(){
        loadEmrir(emrirId);
        dijit.byId('tab1').isLoaded='true';
        dijit.byId('emrirTabs').selectChild('tab1');
    });
}

function msvFormFromXML(msvId)
{
    loadMsvTab(function(){
        msvForm(msvId);
        dijit.byId('tabChild1').isLoaded='true';
        dijit.byId('msvTabs').selectChild('tabChild1');
    });
}

function dmvFormFromXML(msvId)
{
    loadMsvTab(function(){
        dmvForm(msvId);
        dijit.byId('tabChild2').isLoaded='true';
        dijit.byId('msvTabs').selectChild('tabChild2');
    });
}

function mrvFormFromXML(msvId)
{
    loadMsvTab(function(){
        mrvForm(msvId);
        dijit.byId('tabChild3').isLoaded='true';
        dijit.byId('msvTabs').selectChild('tabChild3');
    });
}

function emsvFormFromXML(emsvId)
{
    loadEmsvTab(function(){
        emsvEmrirForm(emsvId);
        dijit.byId('tabChild1').isLoaded='true';
        dijit.byId('emsvTabs').selectChild('tabChild1');
    });
}

function edmvFormFromXML(edmvId)
{
    loadEmsvTab(function(){
        edmvEdnForm(edmvId);
        dijit.byId('tabChild2').isLoaded='true';
        dijit.byId('emsvTabs').selectChild('tabChild2');
    });
}

function searchDmv(){
    var checkflag = true;
    var form=document.forms['searchSimpleDmv'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchDmv.do","dmvList",form,null);
    form=null;
    return false;
}

function searchMrv(){
    var checkflag = true;
    var form=document.forms['searchSimpleMrv'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchMrv.do","mrvList",form,null);
    form=null;
    return false;
}

function edmvCreateMrir()
{
    var dmvForm = document.forms['edmvForm'];
    if (isGreaterThan(dmvForm.emrirId,0)) {
        window.location='emrirForm.do?emrirId=' + dmvForm.emrirId.value;
        dmvForm = null;
        return false;
    }

    if (isBlank(dmvForm.emrirNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 MRIR')) {
        dmvForm = null;
        return false;
    }
    //call function create MRIR
    callAjaxCheckError("createEmrirFromEdmv.do",null,dmvForm,function(data) {
        window.location='emrirForm.do?emrirId=' + data;
    },'edmvErrorMessageDiv');
    dmvForm = null;
    return false;
}


function edmvCreateMsv()
{
    var dmvForm = document.forms['edmvForm'];
    if (isGreaterThan(dmvForm.emsvId,0)) {
        dijit.byId('tabChild1').isLoaded='true';
        dijit.byId('emsvTabs').selectChild('tabChild1');
        emsvEmrirForm(dmvForm.emsvId.value);

        dmvForm = null;
        return false;
    }

    if (isGreaterThan(dmvForm.emrirId,0)==false) {
        alert('Vui l\u00F2ng t\u1EA1o Phi\u1EBFu MRIR tr\u01B0\u1EDBc!');
        dmvForm = null;
        return false;
    }

    if (isBlank(dmvForm.emsvNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 Phi\u1EBFu xu\u1EA5t kho')) {
        dmvForm = null;
        return false;
    }

    //call unction create MSV
    callAjaxCheckError("createEmsvFromEdmv.do",null,dmvForm,function(data) {
        dijit.byId('tabChild1').isLoaded='true';
        dijit.byId('emsvTabs').selectChild('tabChild1');
        emsvEmrirForm(data);
    },'edmvErrorMessageDiv');
    dmvForm = null;
    return false;
}

function searchEdmv(){
    var checkflag = true;
    var form=document.forms['searchSimpleEdmv'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchEdmv.do","edmvList",form,null);
    form=null;
    return false;
}

function edmvCreateRfm()
{
    var dmvForm = document.forms['edmvForm'];
    if (isGreaterThan(dmvForm.erfmId,0)) {
        //ham nay la ham load form RFM
        addRfm(dmvForm.erfmId.value,1);
        dmvForm = null;
        return false;
    }

    if (isGreaterThan(dmvForm.emsvId,0)==false) {
        alert('Vui l\u00F2ng t\u1EA1o Phi\u1EBFu NK tr\u01B0\u1EDBc!');
        dmvForm = null;
        return false;
    }

    if (isBlank(dmvForm.erfmNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 Phi\u1EBFu y\u00EAu c\u1EA7u xu\u1EA5t kho')) {
        dmvForm = null;
        return false;
    }

    //call unction create RFM
    callAjaxCheckError("createErfmFromEdmv.do",null,dmvForm,function(data) {
        //ham nay la ham load form RFM
        addRfm(data,1);
    },'edmvErrorMessageDiv');
    dmvForm = null;
    return false;
}

function edmvCreateMiv()
{
    var dmvForm = document.forms['edmvForm'];
    if (isGreaterThan(dmvForm.emivId,0)) {
        mivForm(dmvForm.emivId.value,2);
        dmvForm = null;
        return false;
    }

    if (isGreaterThan(dmvForm.erfmId,0)==false) {
        alert('Vui l\u00F2ng t\u1EA1o Phi\u1EBFu y\u00EAu c\u1EA7u xu\u1EA5t kho tr\u01B0\u1EDBc!');
        dmvForm = null;
        return false;
    }
    if (isBlank(dmvForm.emivNumber,'Vui l\u00F2ng nh\u1EADp s\u1ED1 Phi\u1EBFu xu\u1EA5t kho')) {
        dmvForm = null;
        return false;
    }
    //call unction create MIV
    callAjaxCheckError("createEmivFromEdmv.do",null,dmvForm,function(data) {
        //ham nay la ham load form RFM
        mivForm(data,2);
    },'edmvErrorMessageDiv');
    dmvForm = null;
    return false;
}


//Kien
function loadMrirList(params){
    callAjaxEx('mrirList.do','ajaxContent','searchMrir.do','mrirList',params);
    return false;
}

function loadMrirList1(params){
    callAjaxEx('searchMrir.do','mrirList',null,null,params);
    //callAjax("searchMrir.do","mrirList",form,null);
    return false;
}

function getMrirListData(data){
    setAjaxData(data,'ajaxContent');
    loadMrirs();
    return false;
}

function loadMrirs(){
    callAjax("mrirs.do","mrirList",null,null);
    return false;
}

function emrirDnChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("emrirForm.do?ednId=" + value,'ajaxContent',null,null)
    value = null;
    return false;
}

function mrirDnChanged(cbx,idx) {
    if(idx < 0)
        return false;
    cbxPro = document.forms['mrirForm'].proId;
    var proId = null;
    if(cbxPro!=null) {
        proId = cbxPro.options[cbxPro.selectedIndex].value;
        cbxPro = null;
    }
    var value = cbx.options.item(idx).value;
    var kind = document.forms['mrirForm'].kind;
    if (kind!=null) kind = kind.value;
    var url = "mrirForm.do?dnId=" + value+"&kind="+kind;
    if (proId!=null) {
        url += "&proId="+proId;
    }
    callAjax(url,'ajaxContent',null,null);
    kind = null;
    value = null;
    url = null;
    proId = null;
    return false;
}

function mrirProChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    var kind = document.forms['mrirForm'].kind;
    if (kind!=null) kind = kind.value;
    callAjax("mrirForm.do?proId=" + value+"&kind="+kind,'ajaxContent',null,null)
    kind = null;
    value = null;
    return false;
}

function mrirReqChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var url="materialsByReqId.do?";
    var reqId = cbx.options.item(idx).value;
    url += "reqId=" + reqId;
    reqId=null;
    cbxDn = document.forms['mrirForm'].dnId;
    if(cbxDn!=null) {
        dnId = cbxDn.options[cbxDn.selectedIndex].value;
        url += "&dnId=" + dnId;
        dnId = null;
        cbxDn=null;
    }
    cbxMaterialKind = document.forms['mrirForm'].materialKind;
    if (cbxMaterialKind!=null) {
        materialKind = cbxMaterialKind.options[cbxMaterialKind.selectedIndex].value;
        url += "&materialKind=" + materialKind;
        materialKind = null;
        cbxMaterialKind = null;
    }
    callAjax(url,'listMaterialByReqIdDiv',null,null);    
    url = null;
    return false;
}

function mrirMaterialKindChanged() {    
    cbxReq = document.forms['mrirForm'].reqId;
    if (cbxReq==null || cbxReq.selectedIndex==0)
        return false;
    var url="materialsByReqId.do?";
    reqId = cbxReq.options[cbxReq.selectedIndex].value;
    url += "reqId=" + reqId;
    cbxReq = null;
    reqId=null;
    cbxDn = document.forms['mrirForm'].dnId;
    if(cbxDn!=null) {
        dnId = cbxDn.options[cbxDn.selectedIndex].value;
        url += "&dnId=" + dnId;
        dnId = null;
        cbxDn=null;
    }
    cbxMaterialKind = document.forms['mrirForm'].materialKind;
    if (cbxMaterialKind!=null) {
        materialKind = cbxMaterialKind.options[cbxMaterialKind.selectedIndex].value;
        url += "&materialKind=" + materialKind;
        materialKind = null;
        cbxMaterialKind = null;
    }
    callAjax(url,'listMaterialByReqIdDiv',null,null);    
    url = null;
    return false;
}

function osDReqChanged(cbx,idx) {
    if(idx < 0)
        return false;
    var dnId = document.forms['osDForm'].dnId;
    if(dnId!=null) {
        dnId = dnId.value;
    } else {
        return false;
    }
    var reqId = cbx.options.item(idx).value;
    var url="materialsOsDByReqId.do?reqId=" + reqId + "&dnId=" + dnId;
    callAjax(url,'osDListMaterialByReqDiv',null,null);
    reqId=null;
    dnId = null;
    url = null;
    return false;
}

function editMrirComment(id)
{
    var commentField = document.getElementById("comment_" + id);
    var matNameField = document.getElementById("mat_name_" + id);
    if (commentField!=null) {
        callAjax("mrirMaterialForm.do?detId="+id+"&comment="+commentField.value,null,null,function(data) {
            showPopupForm(data);
            document.forms['mrirMaterialForm'].comment.value=commentField.value;
            document.forms['mrirMaterialForm'].matName.value=matNameField.value;
            commentField = null;
        });
    }
}

function saveMrirMaterial()
{
    var detId=document.forms['mrirMaterialForm'].detId;
    var comment=document.forms['mrirMaterialForm'].comment;
    var commentField = document.getElementById("comment_" + detId.value);
    if (commentField!=null) {
        commentField.value = comment.value;
    }
    var preTag = document.getElementById("pre_" + detId.value);
    if (preTag!=null) {
        preTag.innerHTML = comment.value;
    }
    hidePopupForm();
    detId = null;
    comment = null;
    handle = null;
    return false;
}

function loadMCProjectStoreMrirReportForm(){
    callAjax('mcProjectStoreMrirReportForm.do','ajaxContent',null,null);
    return false;
}

function mrirForm(mrirId, kind){
    if(mrirId!=null) {
        getMrirTabs(function(){
            loadMrir(mrirId);
        });
    } else {
        var url="mrirForm.do";
        //if(mrirId!=null) url=url+"?mrirId="+mrirId;
        if (kind!=null) {
            url=url+"?kind="+kind;
        }
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
        /*
            if(mrirId==null){
                var currentTime = new Date();
                var date=currentTime.getDate();
                var month=currentTime.getMonth() + 1;
                if(date<10) date='0'+date;
                if(month<10) month='0'+month;
                document.getElementById('createdDateMrir').value=date+'/'+month+'/'+currentTime.getFullYear();
            }

            list1=document.forms['mrirForm'].dnId;
            if(list1!=null) {
                dnIdChangedMrir(list1);
            }
            list1=null;
            setMaterialMrir(mrirId);
            */
        });
    }
    return false;
}

function getMrirTabs(handle){
    callAjax('mrirTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addMrirTabs();
        handle();
    });
}

function addMrirTabs(){
    displayTabs("mrirTabs","mrirTabChild",mrirTabHandle);
}

function mrirTabHandle(child){
    if(child.isLoaded=='true') return true;
    var mrirId=getMrirId();
    if(mrirId=='') return true;
    if(child.id=='tab1'){
        loadMrir(mrirId);
    }else if(child.id=='tab2'){
        list1=document.forms['mrirForm'].dnId;
        var dnId = 0;
        if(list1!=null) {
            dnId = list1.options[list1.selectedIndex].value;
        }
        list1=null;

        if (dnId > 0) {
            loadOsD(mrirId);
        } else {
            alert("Kh\u00F4ng th\u1EC3 t\u1EA1o b\u00E1o c\u00E1o Os&D tr\u01B0\u1EDDng h\u1EE3p n\u00E0y.");
        }
        dnId = null;
    }
    child.isLoaded='true';
    child=null;
}

function getMrirId(){
    var mrir=document.getElementById('mrirIdHidden');
    var mrirId='';
    if(mrir!=null){
        mrirId=mrir.value;
        mrir=null;
    }
    return mrirId;
}

function loadMrir(mrirId){
    callAjax('mrirForm.do?mrirId='+mrirId,null,null,function(data){
        setAjaxData(data,'tab1');
        showhide2('mrirFormTitle',true);
        setMrirId(document.forms['mrirForm'].mrirId.value);
    /*
        list=document.forms['mrirForm'].dnId;
        if(list!=null) {
            dnIdChangedMrir(list);
        }
        list=null;
        setMaterialMrir(mrirId);
        */
    });
}

function printMCProjectStoreMrirReport() {
    var mcReportProject=document.forms['mcReportForm'].mcReportProject;
    var mcStore=document.forms['mcReportForm'].mcStore;
    var status=document.forms['mcReportForm'].status;
    if(mcReportProject.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
        mcReportProject.focus();
        mcReportProject=null;
        return false;
    }else if(mcReportProject.selectedIndex!=0){
            if(mcStore.selectedIndex==-1){
            alert('Vui l\u00F2ng ch\u1ECDn kho');
            mcStore.focus();
            mcStore=null;
            return false;
        }
    }
    var url='mcProjectStoreMrirReportPrint.do?mcReportProject='+mcReportProject.value;
    url+='&mcStore='+mcStore.value;
    url+='&status='+status.value;
    url+='&osdFromDate='+document.forms['mcReportForm'].osdFromDate.value;
    url+='&osdEndDate='+document.forms['mcReportForm'].osdEndDate.value;
    callServer(url);
    mcReportProject=null;
    mcStore=null;
    mcReportMaterialKind=null;
}

function setMrirId(mrirId){
    var mrir=document.getElementById('mrirIdHidden');
    if(mrir!=null){
        mrir.value=mrirId;
        mrir=null;
    }
}

function getMrirDetail(mrirId) {
    url='mrirDetailList.do';
    if(mrirId!=null) url=url+"?mrirId="+mrirId;
    callAjax(url,'listMrirDetailDataDiv',null,null);
    return false;
}

function saveMrir() {
    if(scriptFunction=="saveMrir()") return false;
    var mrirNumber = document.forms['mrirForm'].mrirNumber;
    var mrirId = document.forms['mrirForm'].mrirId.value;
    if(mrirNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 MRIR");
        mrirNumber.focus();
        mrirNumber=null;
        return false;
    }
    mrirNumber=null;
    //callAjaxCheckError("saveMrir.do",null,document.forms['mrirForm'],getMrirListData,'mrirErrorMessageDiv');
    scriptFunction="saveMrir()";
    callAjaxCheckError("saveMrir.do",null,document.forms['mrirForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        mrirForm(mrirId);
    });
    return false;
}

function searchMrir(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchMrir.do","mrirList",form,null);
    form=null;
    return false;
}
function delMrirs(){
    var checkflag = false;
    var mrirId = document.forms['mrirsForm'].mrirId;
    if(mrirId==null) return false;
    if (mrirId.length!=null) {
        for (i = 0; i < mrirId.length; i++) {
            if (mrirId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = mrirId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError("delMrir.do","mrirList",document.forms['mrirsForm'],null);
    mrirId=null;
    return false;
}
function searchAdvMrirForm(){
    callAjax('searchAdvMrirForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvMrir(){
    callAjax('searchAdvMrir.do',null,document.forms['searchMrirForm'],getSearchAdvMrirData);
    hidePopupForm();
    return false;
}

function getSearchAdvMrirData(data){
    setAjaxData(data,'mrirList');
}

function loadMrirDetailList(params){
    callAjaxEx('mrirDetailList.do','ajaxContent','mrirDetails.do','mrirDetailList',params);
    return false;
}

function getMrirDetailListData(data){
    setAjaxData(data,'ajaxContent');
    loadMrirDetails();
    return false;
}

function loadMrirDetails(){
    callAjax("mrirDetails.do","mrirDetailList",null,null);
    return false;
}

//function whichUseChangedMrir(list){
//    if(list.selectedIndex==-1) return false;
//    var mrirId=null;
//    if(document.forms['mrirForm']!=null) mrirId=document.forms['mrirForm'].mrirId;
//    getWhichUseMrir(mrirId.value,list.options[list.selectedIndex].value);
//    list=null;
//    mrirId=null;
//    return false;
//}
//
//function getWhichUseMrir(mrirId,whichUse){
//    var url="whichUseMrirList.do?whichUse="+whichUse;
//    if(mrirId!=null) url+="&mrirId="+mrirId;
//    callAjax(url,null,null,function(data){
//        setAjaxData(data,'whichUseSpan');
//    });
//}

function dnIdChangedMrir(list) {
    if(list.selectedIndex==-1) return false;
    var mrirId=document.forms['mrirForm'].mrirId;
    var dnId = list.options[list.selectedIndex].value;
    getReqVenMrir(mrirId.value, dnId);
    if (dnId == 0) {
        var matList = document.forms['mrirForm'].materialList;
        if (matList != null) {
            showhide2(matList, true);
        }
        matList = null;
    }
    mrirId=null;
    dnId=null;
}

function getReqVenMrir(mrirId, dnId) {
    var url="reqVenMrirList.do?mrirId=" + mrirId + "&dnId=" + dnId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'mrirRequestVendorDiv');
        list2=document.forms['mrirForm'].reqId;
        if(list2!=null) {
            reqIdChangedMrir(list2);
        }
        list2=null;
    });
}

function reqIdChangedMrir(list) {
    if(list.selectedIndex==-1) return false;
    var reqId = list.options[list.selectedIndex].value;
    var dnId = null;

    list1=document.forms['mrirForm'].dnId;
    if(list1!=null) {
        dnId = list1.options[list1.selectedIndex].value;
    }
    list1=null;

    getMaterialsByReqId(dnId, reqId);
    reqId=null;
    dnId = null;
}

function getMaterialsByReqId(dnId, reqId) {
    var url="materialsByReqId.do?dnId=" + dnId + "&reqId=" + reqId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listMaterialByReqIdDiv');
    });

    var mrirId = document.forms['mrirForm'].mrirId;
    if (mrirId.value == 0) {
        url="materialsByReqIds.do?dnId=" + dnId + "&reqId=" + reqId;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'listMrirDetailDataDiv');
        });
    }
    mrirId = null;
    url = null;
}

function printMrir() {
    var mrirId=document.forms['mrirForm'].mrirId;
    if(mrirId!=null) callServer('mrirPrint.do?mrirId='+mrirId.value);
    mrirId=null;
    return false;
}

function mrirPrintComments() {
    var mrirId=document.forms['mrirForm'].mrirId;
    if(mrirId!=null) callServer('mrirPrint.do?mrirId='+mrirId.value + "&comment=1");
    mrirId=null;
    return false;
}

function delMrirDetails(){
    var checkflag = false;
    var detId = document.forms['mrirForm'].mrirMaterialChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) {
        //        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
        //            callAjax('delMrirDetail.do',null,document.forms['mrirForm'],function(data){
        //                setAjaxData(data,'listMrirDetailDataDiv');
        //            });
        //        }
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delMrirDetail.do',null,document.forms['mrirForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('materialTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}

function mrirDelMaterial(){
    //kiem tra xem da dc chon chua
    var detId = document.forms['mrirForm'].chkDetId;
    if (detId==null)
        return false;

    if(!confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        return false;

    var mrirId = document.forms['mrirForm'].mrirId;
    if (mrirId.value=0) {
        var tbl = document.getElementById('mrirMaterial');
        var parentNode;
        if(detId.length!=null){
            for (i=detId.length-1;i>=0;i--) {
                if(detId[i].checked==true){
                    parentNode=detId[i].parentNode;
                    parentNode=parentNode.parentNode;
                    tbl.deleteRow(parentNode.rowIndex);
                }
            }
        }  else if(detId.checked==true) {
            tbl.deleteRow(1);
        }
    } else {
        callAjax("mrirDelMaterial.do",null,document.forms['mrirForm'],function(data) {
            if (data=='noselect') {
                alert('Please select material in table');
            } else {
                setAjaxData(data,'listMrirDetailDataDiv');
            }
        });
    }

    return false;
}

function emrirDelMaterial(){
    //kiem tra xem da dc chon chua
    var detId = document.forms['emrirForm'].chkDetId;
    if (detId==null)
        return false;

    if(!confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        return false;

    var mrirId = document.forms['emrirForm'].emrirId;
    if (mrirId.value==0) {
        var tbl = document.getElementById('emrirMaterial');
        var parentNode;
        if(detId.length!=null){
            for (i=detId.length-1;i>=0;i--) {
                if(detId[i].checked==true){
                    parentNode=detId[i].parentNode;
                    parentNode=parentNode.parentNode;
                    tbl.deleteRow(parentNode.rowIndex);
                }
            }
        }  else if(detId.checked==true) {
            tbl.deleteRow(1);
        }
    } else {
        callAjax("emrirDelMaterial.do",null,document.forms['emrirForm'],function(data) {
            if (data=='noselect') {
                alert('Please select material in table');
            } else {
                setAjaxData(data,'listEmrirDetailDataDiv');
            }
        });
    }

    return false;
}

function mrirAddAllMaterial(){
    var dnlist=document.forms['mrirForm'].dnId;
    var cbxMaterialOfReq=document.forms['mrirForm'].cbxMaterialOfReq;
    if (dnlist!=null) {
        dnId=dnlist.options[dnlist.selectedIndex].value;
        if (dnId>0) {
            //var cbxReqId = document.forms['mrirForm'].reqId;
            //if (cbxReqId!=null) 
            {
                //var reqId = cbxReqId.options[cbxReqId.selectedIndex].value;
                if (cbxMaterialOfReq!=null) {
                     callAjaxCheckError("mrirAddAllMaterial.do",null,document.forms['mrirForm'],function(data) {
                        setAjaxData(data,'listMrirDetailDataDiv');
                    },null);
                }
            }
        }
    }
    return false;
}

function mrirAddMaterial(){
    var mrv = false;
    var dnlist=document.forms['mrirForm'].dnId;
    if (dnlist!=null) {
        dnId=dnlist.options[dnlist.selectedIndex].value;
        if (dnId>0) {
            var cbxReqId = document.forms['mrirForm'].reqId;
            if (cbxReqId!=null) {
                reqId = cbxReqId.options[cbxReqId.selectedIndex].value;
                if (reqId>0) {
                    var cbxMaterialOfReq = document.forms['mrirForm'].cbxMaterialOfReq;
                    dnDetId = cbxMaterialOfReq.options[cbxMaterialOfReq.selectedIndex].value;
                    if (dnDetId>0) {
                        callAjaxCheckError("mrirAddMaterial.do",null,document.forms['mrirForm'],function(data) {
                            setAjaxData(data,'listMrirDetailDataDiv');
                        },null);
                    }
                } else {
                    alert("Vui l\u00F2ng ch\u1ECDn Phi\u1EBFu \u0111\u1EC1 xu\u1EA5t");
                    cbxReqId.focus();
                }
            } else {
                callAjaxCheckError("mrirAddMaterial.do",null,document.forms['mrirForm'],function(data) {
                    setAjaxData(data,'listMrirDetailDataDiv');
                },null);
            }
        } else {
            mrv = true;
        }
    } else {
        mrv = true;
    }
    if (mrv==true) {
        selectMrirMaterial('setSelectedMrirMaterial');
    }
    return false;
}

function emrirAddMaterial(){
    var dnlist=document.forms['emrirForm'].ednId;
    if (dnlist!=null) {
        dnId=dnlist.options[dnlist.selectedIndex].value;
        if (dnId>0) {
            callAjaxCheckError("emrirAddMaterial.do",null,document.forms['emrirForm'],function(data) {
                setAjaxData(data,'listEmrirDetailDataDiv');
            },null);
        }
    }
    return false;
}

function addMrirDetail(){
    var dnlist=document.forms['mrirForm'].dnId;
    if(dnlist==null) return;
    var dnId=dnlist.options[dnlist.selectedIndex].value;
    dnlist=null;
    if (dnId > 0) {
        var list=document.forms['mrirForm'].materialList;
        if(list==null) return;
        var id=list.options[list.selectedIndex].value;
        list=null;
        var selId=document.forms['mrirForm'].material;
        if(selId!=null){
            if(selId.length!=null){
                for (i=0;i<selId.length;i++) {
                    if(id==selId[i].value){
                        alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                        return false;
                    }
                }
            }else if(id==selId.value){
                alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                return false;
            }
        }
        selId=null;
        var mrirId=getMrirId();
        var list1=document.forms['mrirForm'].dnId;
        if(list1==null) return;
        var dnId=list1.options[list1.selectedIndex].value;
        list1=null;

        var list2=document.forms['mrirForm'].reqId;
        if(list2==null) return;
        var reqId=list2.options[list2.selectedIndex].value;
        list2=null;

        callAjax("getMaterialForMrir.do?matId=" + id + "&mrirId=" + mrirId + "&dnId=" + dnId + "&reqId=" + reqId,null,null,function(data){
            setAjaxData(data,'mrirDetailHideDiv');
            var matTable=document.getElementById('materialDetailTbl');
            var detTable=document.getElementById('materialTbl');
            if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
                matTable=null;
                detTable=null;
                return;
            }
            for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
                detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
            }
            matTable=null;
            detTable=null;
        });
    } else {
        selectMrirMaterial('setSelectedMrirMaterial');
    }

    return false;
}

function removeMrirMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('mrirDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}

function selectMrirMaterial(handle){
    if(handle==null) return false;
    callAjax('chooseMrirMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchMrirMaterialRequest();
    });
    return false;
}

function setMrirMaterialSelected(){
    var matId = document.forms['materialMrirRequestForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialMrirRequestForm'].matNameHidden;
    //    var matCodeHidden = document.forms['materialMrirRequestForm'].matCodeHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                //                cell = row.insertCell(2);
                //                textNode = document.createTextNode(matCodeHidden[i].value);
                //                if (textNode.length==4) textNode="";
                //                cell.appendChild(textNode);
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            //            cell = row.insertCell(2);
            //            textNode = document.createTextNode(matCodeHidden.value);
            //            cell.appendChild(textNode);
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    //    matCodeHidden=null;
    tbl=null;
}

function chooseMrirMaterialSelected(){
    var selId=document.forms['materialMrirRequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}

function searchMrirMaterialRequest(params){
    var form=document.forms['selectMrirMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchMrirMaterialRequest.do?"+params,null,form,function(data){
        setAjaxData(data,'materialMrirRequestList');
        var matId = document.forms['materialMrirRequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialMrirRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}

function delMrirMaterialRequest(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['materialMrirRequestForm'].matId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}

function setSelectedMrirMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['mrirForm'].matId;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("mrirMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'listMrirDetailDataDiv');
    //        var matTable=document.getElementById('materialTbl');
    //        var detTable=document.getElementById('materialDetailTbl');
    //        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
    //            matTable=null;
    //            detTable=null;
    //            return;
    //        }
    //        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
    //            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
    //        }
    //        matTable=null;
    //        detTable=null;
    });
}

function setMaterialMrir(mrirId) {
    var url="getMaterialMrir.do";
    if(mrirId!=null) url+="?mrirId="+mrirId;

    callAjax(url,'listMrirDetailDataDiv',null,null);
    return false;
}

// Emrir
function loadEmrirList(params){
    callAjaxEx('emrirList.do','ajaxContent','searchEmrir.do','emrirList',params);
    return false;
}
function loadEmrirListSort(params){
    callAjaxEx('searchEmrir.do','emrirList',null,null,params);
    return false;
}
function getEmrirListData(data){
    setAjaxData(data,'ajaxContent');
    loadEmrirs();
    return false;
}

function loadEmrirs(){
    callAjax("emrirs.do","emrirList",null,null);
    return false;
}

function emrirForm(emrirId){
    if(emrirId!=null) {
        getEmrirTabs(function(){
            loadEmrir(emrirId);
        });
    } else {
        var url="emrirForm.do";
        if(emrirId!=null) url=url+"?emrirId="+emrirId;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            /*
            if(emrirId==null){
                var currentTime = new Date();
                var date=currentTime.getDate();
                var month=currentTime.getMonth() + 1;
                if(date<10) date='0'+date;
                if(month<10) month='0'+month;
                document.getElementById('createdDateEmrir').value=date+'/'+month+'/'+currentTime.getFullYear();
            }

            list1=document.forms['emrirForm'].ednId;
            if(list1!=null) {
                ednIdChangedEmrir(list1);
            }
            list1=null;
            setMaterialEmrir(emrirId);
            */
        });
    }
    return false;
}

function loadEmrir(emrirId){
    callAjax('emrirForm.do?emrirId='+emrirId,null,null,function(data){
        setAjaxData(data,'tab1');
        showhide2('emrirFormTitle',true);
        setEmrirId(emrirId);
    });
}

function getEmrirTabs(handle){
    callAjax('emrirTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addEmrirTabs();
        handle();
    });
}

function addEmrirTabs(){
    displayTabs("emrirTabs","emrirTabChild",emrirTabHandle);
}

function emrirTabHandle(child) {
    if(child.isLoaded=='true') {
        return true;
    }
    var emrirId=getEmrirId();
    if(emrirId=='') {
        return true;
    }
    if(child.id=='tab1') {
        loadEmrir(emrirId);
    }else if(child.id=='tab2') {
        var list1=document.forms['emrirForm'].ednId;
        var ednId = 0;
        if(list1!=null) {
            ednId = list1.options[list1.selectedIndex].value;
        }
        list1=null;
        if (ednId > 0) {
            loadEosD(emrirId);
        } else {
            alert("Kh\u00F4ng th\u1EC3 t\u1EA1o b\u00E1o c\u00E1o Os&D tr\u01B0\u1EDDng h\u1EE3p n\u00E0y.");
        }
        ednId = null;
    }
    child.isLoaded='true';
    child=null;
}

function getEmrirId(){
    var emrir=document.getElementById('emrirIdHidden');

    var emrirId='';
    if(emrir!=null){
        emrirId=emrir.value;
        emrir=null;
    }
    return emrirId;
}

function setEmrirId(emrirId){
    var emrir=document.getElementById('emrirIdHidden');
    if(emrir!=null){
        emrir.value=emrirId;
        emrir=null;
    }
}

function getEmrirDetail(emrirId) {
    url='emrirDetailList.do';
    if(emrirId!=null) url=url+"?emrirId="+emrirId;
    callAjax(url,'listEmrirDetailDataDiv',null,null);
    return false;
}

function saveEmrir() {
    if(scriptFunction=="saveEmrir()") return false;
    var emrirNumber = document.forms['emrirForm'].emrirNumber;
    if(emrirNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 MRIR");
        emrirNumber.focus();
        emrirNumber=null;
        return false;
    }
    emrirNumber=null;
    if (!isGreaterThan(document.forms['emrirForm'].emrirId,0)) {
        var ednId = document.forms['emrirForm'].ednId;
        if(ednId.selectedIndex == 0){
            alert("Vui l\u00F2ng ch\u1ECDn s\u1ED1 phi\u1EBFu giao h\u00E0ng");
            ednId=null;
            return false;
        }
        ednId=null;
    }
    //callAjaxCheckError("saveEmrir.do",null,document.forms['emrirForm'],getEmrirListData,'emrirErrorMessageDiv');
    scriptFunction="saveEmrir()";
    callAjaxCheckError("saveEmrir.do",null,document.forms['emrirForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}

function delEmrirs(){
    var checkflag = false;
    var emrirId = document.forms['emrirsForm'].emrirId;
    if(emrirId==null) return false;
    if (emrirId.length!=null) {
        for (i = 0; i < emrirId.length; i++) {
            if (emrirId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = emrirId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError("delEmrir.do","emrirList",document.forms['emrirsForm'],null);
    emrirId=null;
    return false;
}

function searchEmrir(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchEmrir.do","emrirList",form,null);
    form=null;
    return false;
}

function searchAdvEmrirForm(){
    callAjax('searchAdvEmrirForm.do',null,null,showPopupForm);
    return false;
}

function searchAdvEmrir(){
    callAjax('searchAdvEmrir.do',null,document.forms['searchEmrirForm'],getSearchAdvEmrirData);
    hidePopupForm();
    return false;
}

function getSearchAdvEmrirData(data){
    setAjaxData(data,'emrirList');
}

function loadEmrirDetailList(params){
    callAjaxEx('emrirDetailList.do','ajaxContent','emrirDetails.do','emrirDetailList',params);
    return false;
}

function getEmrirDetailListData(data){
    setAjaxData(data,'ajaxContent');
    loadEmrirDetails();
    return false;
}

function loadEmrirDetails(){
    callAjax("emrirDetails.do","emrirDetailList",null,null);
    return false;
}

function setMaterialEmrir(emrirId) {
    var url="getMaterialEmrir.do";
    if(emrirId!=null) url+="?emrirId="+emrirId;

    callAjax(url,'listEmrirDetailDataDiv',null,null);
    return false;
}

function ednIdChangedEmrir(list) {
    if(list.selectedIndex==-1) return false;
    var emrirId=document.forms['emrirForm'].emrirId;
    var ednId = list.options[list.selectedIndex].value;

    if (ednId > 0) {
        getEconNumber(ednId);
    }

    getMaterialsByDnId(ednId);

    emrirId=null;
    ednId=null;
}

function getEconNumber(ednId) {
    var url="econNumberByDnId.do?ednId=" + ednId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'econNumberDiv');
    });
    url = null;
}

function getMaterialsByDnId(ednId) {
    var url="materialsByDnId.do?ednId=" + ednId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listMaterialByDnIdDiv');
    });

    var emrirId = document.forms['emrirForm'].emrirId;
    if (emrirId.value == 0) {
        url="materialsByDnIds.do?ednId=" + ednId;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'listEmrirDetailDataDiv');
        });
    }
    emrirId = null;
    url = null;
}

function printEmrir() {
    var emrirId=document.forms['emrirForm'].emrirId;
    if(emrirId!=null) callServer('emrirPrint.do?emrirId='+emrirId.value);
    emrirId=null;
}

function delEmrirDetails(){
    var checkflag = false;
    var detId = document.forms['emrirForm'].emrirMaterialChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delEmrirDetail.do',null,document.forms['emrirForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('materialTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}

function addEmrirDetail(){
    var ednlist=document.forms['emrirForm'].ednId;
    if(ednlist==null) return;
    var ednId=ednlist.options[ednlist.selectedIndex].value;
    ednlist=null;

    if (ednId > 0) {
        var list=document.forms['emrirForm'].materialList;
        if(list==null) return;
        var id=list.options[list.selectedIndex].value;
        list=null;
        var selId=document.forms['emrirForm'].material;
        if(selId!=null){
            if(selId.length!=null){
                for (i=0;i<selId.length;i++) {
                    if(id==selId[i].value){
                        alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                        return false;
                    }
                }
            }else if(id==selId.value){
                alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                return false;
            }
        }
        selId=null;

        var emrirId=getEmrirId();
        var list1=document.forms['emrirForm'].ednId;
        if(list1==null) return;
        var ednId=list1.options[list1.selectedIndex].value;
        list1=null;

        callAjax("getMaterialForEmrir.do?ematId=" + id + "&emrirId=" + emrirId + "&ednId=" + ednId,null,null,function(data){
            setAjaxData(data,'emrirDetailHideDiv');
            var matTable=document.getElementById('materialDetailTbl');
            var detTable=document.getElementById('materialTbl');
            if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
                matTable=null;
                detTable=null;
                return;
            }
            for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
                detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
            }
            matTable=null;
            detTable=null;
        });
    } else {
    //        selectEmrirMaterial('setSelectedEmrirMaterial');
    }

    return false;
}

function removeEmrirMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('emrirDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}

function selectEmrirMaterial(handle){
    if(handle==null) return false;
    callAjax('chooseEmrirMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEmrirMaterialRequest();
    });
    return false;
}

function setEmrirMaterialSelected(){
    var matId = document.forms['materialEmrirRequestForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialEmrirRequestForm'].matNameHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);

                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {



            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    tbl=null;
}

function chooseEmrirMaterialSelected(){
    var selId=document.forms['materialEmrirRequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}

function searchEmrirMaterialRequest(params){
    var form=document.forms['selectEmrirMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchEmrirMaterialRequest.do?"+params,null,form,function(data){
        setAjaxData(data,'materialEmrirRequestList');
        var matId = document.forms['materialEmrirRequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialEmrirRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}

function delEmrirMaterialRequest(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['materialEmrirRequestForm'].matId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}

function setSelectedEmrirMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['emrirForm'].matId;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("emrirMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'listEmrirDetailDataDiv');
    });
}

// Os&D
function osDForm(osdId){
    var url="osDForm.do";
    if(osdId!=null) url=url+"?osdId="+osdId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(osdId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateOsD').value=date+'/'+month+'/'+currentTime.getFullYear();
        }
        setMaterialOsD(osdId);
    });
    return false;
}

function loadOsD(mrirId){
    callAjax('osDForm.do?mrirId='+mrirId,null,null,function(data) {
        setAjaxData(data,'tab2');
        showhide2('osDFormTitle',true);
    //var osdId = document.forms['osDForm'].osdId.value;
    //setMaterialOsD(mrirId, osdId);
    });
}

function setMaterialOsD(mrirId, osdId) {
    var url="getMaterialOsD.do";
    if(osdId!=null) url+="?mrirId=" + mrirId + "&osdId=" + osdId;
    callAjax(url,'osDListDetailDataDiv',null,null);
    return false;
}

function saveOsD() {
    if(scriptFunction=="saveOsD()") return false;
    var osdNumber = document.forms['osDForm'].osdNumber;
    if(osdNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 b\u00E1o c\u00E1o.");
        osdNumber.focus();
        osdNumber=null;
        return false;
    }
    osdNumber=null;
    var nonConform = document.forms['osDForm'].nonConform;
    var checked=0;
    for(var i=0;i<nonConform.length;i++){
        if(nonConform[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0111i\u1EC3m kh\u00F4ng ph\u00F9 h\u1EE3p.");
        return false;
    }
    nonConform=null;
    scriptFunction="saveOsD()";
    callAjaxCheckError("saveOsD.do",null,document.forms['osDForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        loadOsD(getMrirId());
        //if(data!="") {
        //    loadOsD(data);
        //}
    },'osDErrorMessageDiv');
    return false;
}

function getOsDListData(data){
    setAjaxData(data,'ajaxContent');
    //    var mrirId = document.forms['osDForm'].mrirId;
    //    alert(mrirId);
    //    loadOsD(mrirId);
    return false;
}

function delOsDs(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delOsD.do',null,document.forms['osDForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else{
            var mrirId = getMrirId();
            loadOsD(mrirId);
        }
    });
    osdId=null;
    return false;
}

function getOsDMaterialList(osdId) {
    url='osDMaterialList.do';
    if(osdId!=null) url=url+"?osdId="+osdId;    
    callAjax(url,'osDListDetailDataDiv',null,null);
    return false;
}

function loadOsDDetailList(params){
    callAjaxEx('osDDetailList.do','ajaxContent','osDDetails.do','osDDetailList',params);
    return false;
}

function delOsDDetails(){
    var checkflag = false;
    var detId = document.forms['osDForm'].osDMaterialChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) {
        //        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjax('delOsDDetail.do',null,document.forms['osDForm'],function(data){
        //            setAjaxData(data,'listOsDDetailDataDiv');
        //        });

        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delOsDDetail.do',null,document.forms['osDForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('osDMaterialTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}

function osDDelMaterial(){
    //kiem tra xem da dc chon chua
    var detId = document.forms['osDForm'].chkDetId;
    if (detId==null)
        return false;

    if(!confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        return false;

    if (!isGreaterThan(document.forms['osDForm'].osdId,0)) {
        var tbl = document.getElementById('osDMaterial');
        var parentNode;
        if(detId.length!=null){
            for (i=detId.length-1;i>=0;i--) {
                if(detId[i].checked==true){
                    parentNode=detId[i].parentNode;
                    parentNode=parentNode.parentNode;
                    tbl.deleteRow(parentNode.rowIndex);
                }
            }
        }  else if(detId.checked==true) {
            tbl.deleteRow(1);
        }
    } else {
        callAjax("osDDelMaterial.do",null,document.forms['osDForm'],function(data) {
            if (data=='noselect') {
                alert('Please select material in table');
            } else {
                setAjaxData(data,'osDListDetailDataDiv');
            }
        });
    }

    return false;
}

function osDCreateMrir(){
        var mrirId = document.forms['mrirForm'].mrirId.value;
        callAjaxCheckError("saveMrir.do?mrirIds="+mrirId,null,null,function(data){
        //alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        mrirForm();
    });
    

    return false;
}

function eosDDelMaterial(){
    //kiem tra xem da dc chon chua
    var detId = document.forms['eosDForm'].chkDetId;
    if (detId==null)
        return false;

    if(!confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        return false;

    if (!isGreaterThan(document.forms['eosDForm'].eosdId,0)) {
        var tbl = document.getElementById('eosDMaterial');
        var parentNode;
        if(detId.length!=null){
            for (i=detId.length-1;i>=0;i--) {
                if(detId[i].checked==true){
                    parentNode=detId[i].parentNode;
                    parentNode=parentNode.parentNode;
                    tbl.deleteRow(parentNode.rowIndex);
                }
            }
        }  else if(detId.checked==true) {
            tbl.deleteRow(1);
        }
    } else {
        callAjax("eosDDelMaterial.do",null,document.forms['eosDForm'],function(data) {
            if (data=='noselect') {
                alert('Please select material in table');
            } else {
                setAjaxData(data,'eosDListDetailDataDiv');
            }
        });
    }

    return false;
}

function osDMatForm(detId) {
    callAjax('osDMaterialForm.do?detId='+detId,null,null,showPopupForm);
    return false;
}

function printOsDMatList() {
    var osdId=document.forms['osDForm'].osdId;
    if(osdId!=null) callServer('printOsDMaterials.do?osdId='+osdId.value);
    osdId=null;
    return false;
}

function saveOsDMaterial() {
    if(scriptFunction=="saveOsDMaterial()") return false;
    scriptFunction="saveOsDMaterial()";
    callAjaxCheckError("addOsDMaterial.do",null,document.forms['osDMaterialForm'],function(data) {
        scriptFunction="";
        //setAjaxData(data,'osDListDetailDataDiv');
        hidePopupForm();
        getOsDMaterialList(data);
    },'osDMaterialErrorMessageDiv');
    return false;
}

function osDAddMaterial() {    
    var dnlist=document.forms['osDForm'].dnId;
    if (dnlist!=null) {
        dnId=dnlist.value;
        if (dnId>0) {
            var cbxReqId = document.forms['osDForm'].reqId;
            if (cbxReqId!=null) {
                reqId = cbxReqId.options[cbxReqId.selectedIndex].value;
                if (reqId>0) {
                    var cbxMaterialOfReq = document.forms['osDForm'].cbxMaterialOfReq;
                    reqDetailId = cbxMaterialOfReq.options[cbxMaterialOfReq.selectedIndex].value;
                    if (reqDetailId>0) {
                        callAjaxCheckError("osDAddMaterial.do",null,document.forms['osDForm'],function(data) {
                            setAjaxData(data,'osDListDetailDataDiv');
                        },'osDErrorMessageDiv');
                    }
                } else {
                    alert("Vui l\u00F2ng ch\u1ECDn Phi\u1EBFu \u0111\u1EC1 xu\u1EA5t");
                    cbxReqId.focus();
                }
            } else {
                callAjaxCheckError("osDAddMaterial.do",null,document.forms['osDForm'],function(data) {
                    setAjaxData(data,'osDListDetailDataDiv');
                },'osDErrorMessageDiv');
            }
        }
    }    
    return false;
}

function eosDAddMaterial(){
    var dnlist=document.forms['eosDForm'].ednId;
    if (dnlist!=null) {
        dnId=dnlist.value;
        if (dnId>0) {
            callAjaxCheckError("eosDAddMaterial.do",null,document.forms['eosDForm'],function(data) {
                setAjaxData(data,'eosDListDetailDataDiv');
            },'eosDErrorMessageDiv');
        }
    }
    return false;
}

function addOsDDetail(){
    var list=document.forms['osDForm'].materialList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    list=null;
    var selId=document.forms['osDForm'].material;
    if(selId!=null){
        if(selId.length!=null){
            for (i=0;i<selId.length;i++) {
                if(id==selId[i].value){
                    alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                    return false;
                }
            }
        }else if(id==selId.value){
            alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
    }
    selId=null;
    var mrirId=getMrirId();
    if(mrirId=='') return true;
    callAjax("getMaterialForOsD.do?matId="+id+"&mrirId="+mrirId,null,null,function(data){
        setAjaxData(data,'osDDetailHideDiv');
        var matTable=document.getElementById('osDMaterialDetailTbl');
        var detTable=document.getElementById('osDMaterialTbl');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
    return false;
}

function printOsD(osdId) {
    var osdId=document.forms['osDForm'].osdId;
    if(osdId!=null) callServer('osDPrint.do?osdId='+osdId.value);
    osdId=null;
}

function selectMaterialOsD(handle,title,detId,osdId,matId){
    if(handle==null) return false;
    popupName=title;
    callAjax('chooseMaterialOsDForm.do?detId='+detId+'&osdId='+osdId+'&matId='+matId,null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        document.forms['selectMaterialOsDForm'].searchid.value = 2;
        if(matId!=null){
            document.forms['selectMaterialOsDForm'].searchvalue.value = document.forms['selectMaterialOsDForm'].matName.value;
        }
        searchMaterialOsD();
    });
    return false;
}


function newMaterialOsD(handle,id,title,detId,osdId,matId){
    popupName=title;
    var mrirId = document.forms['osDForm'].mrirId.value;
    if(handle==null){
        callAjax('materialForm.do?matId='+id,null,null,function(data){
            showPopupForm(data);
        });
    }
    else{
        callAjax('addMaterialOsDForm.do?detId='+detId+'&osdId='+osdId+'&mrirId='+mrirId+'&matId='+matId,null,null,function(data){
            showPopupForm(data);
            document.getElementById('callbackFunc').value=handle;
        });
    }
    return false;
}

function searchMaterialOsD(params){
    var form=document.forms['selectMaterialOsDForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchMaterialOsD.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialOsDList');
        var matId = document.forms['materialOsDForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialOsDSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}

function setMaterialOsDSelected(){
    var matId = document.forms['materialOsDForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialOsDForm'].matNameHidden;
    var matCodeHidden = document.forms['materialOsDForm'].matCodeHidden;
    var tbl=document.getElementById('materialOsDSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {            
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'radio';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
                
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'radio';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
    chooseMaterialOsDSelected();
}

function chooseMaterialOsDSelected(){
    var selId=document.forms['materialOsDSelectedForm'].materialSelectedChk;
    var detId=document.forms['materialOsDForm'].detId.value;
    var mrirId=document.forms['osDForm'].mrirId.value;
    var osdId=document.forms['osDForm'].osdId.value;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids=selId[i].value;
        }
    }else ids=selId.value;
    callAjaxCheckError("saveOsDMaterial.do?matId="+ids+"&detId="+detId,null,null,function(data){
        hidePopupForm();
        getOsDMaterialList(osdId);
    });
    hidePopupForm();
}

function saveMaterialOsD(detId) {
    if(scriptFunction=="saveMaterialOsD(detId)") return false;
    var nameVn = document.forms['addMaterialOsD'].nameVn;
    var nameEn = document.forms['addMaterialOsD'].nameEn;
    var mrirId=document.forms['addMaterialOsD'].mrirId.value;
    var osdId=document.forms['addMaterialOsD'].osdId.value;
    var uniId = document.forms['addMaterialOsD'].uniId;
    if (nameVn.value.length==0 && nameEn.value.length==0){
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn=null;
        return false;
    }    
    if(uniId.selectedIndex==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId=null;
        return false;
    }
    scriptFunction="saveMaterialOsD(detId)";
    callAjaxCheckError("addMaterialOsD.do?detId="+detId,null,document.forms['addMaterialOsD'],function(data){        
        scriptFunction="";
        hidePopupForm();
        //getOsDMaterialList(osdId);
        loadOsD(getMrirId());
    });
    hidePopupForm();
    return false;
}

function setSelectedMaterialOsD(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['osDForm'].matId;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
                else{
                    isExisted=true;
                    alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                    break;
                }
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("listRequestMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'requestMaterialHideDiv');
        var matTable=document.getElementById('requestMaterialTable');
        var detTable=document.getElementById('requestDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}

// Eos&D
function eosDForm(eosdId){
    var url="eosDForm.do";
    if(eosdId!=null) url=url+"?eosdId="+eosdId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(eosdId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateEosD').value=date+'/'+month+'/'+currentTime.getFullYear();
        }
        setMaterialEosD(eosdId);
    });
    return false;
}

function loadEosD(emrirId){
    callAjax('eosDForm.do?emrirId='+emrirId,null,null,function(data) {
        setAjaxData(data,'tab2');
        showhide2('eosDFormTitle',true);
        //var eosdId = document.forms['eosDForm'].eosdId.value;
        //setMaterialEosD(emrirId, eosdId);
        eosdId = null;
    });
}

function setMaterialEosD(emrirId, eosdId) {
    var url="getMaterialEosD.do";
    if(eosdId!=null) url+="?emrirId=" + emrirId + "&eosdId=" + eosdId;
    callAjax(url,'listEosDDetailDataDiv',null,null);
    return false;
}

function saveEosD() {
    if(scriptFunction=="saveEosD()") return false;
    var eosdNumber = document.forms['eosDForm'].eosdNumber;
    if(eosdNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 b\u00E1o c\u00E1o.");
        eosdNumber.focus();
        eosdNumber=null;
        return false;
    }
    eosdNumber=null;

    var nonConform = document.forms['eosDForm'].nonConform;
    var checked=0;
    for(var i=0;i<nonConform.length;i++){
        if(nonConform[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0111i\u1EC3m kh\u00F4ng ph\u00F9 h\u1EE3p.");
        return false;
    }
    nonConform=null;
    scriptFunction="saveEosD()";
    callAjaxCheckError("saveEosD.do",null,document.forms['eosDForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //if(data!="") {
        //    loadEosD(data);
        //}
    },'eosDErrorMessageDiv');
    return false;
}

function getEosDListData(data){
    setAjaxData(data,'ajaxContent');
    //    var mrirId = document.forms['eosDForm'].mrirId;
    //    alert(mrirId);
    //    loadEosD(mrirId);
    return false;
}

function delEosDs(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjax('delEosD.do',null,document.forms['eosDForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else{
            var mrirId = getMrirId();
            loadEosD(mrirId);
        }
    });
    eosdId=null;
    return false;
}

function getEosDDetail(eosdId) {
    url='eosDDetailList.do';
    if(eosdId!=null) url=url+"?eosdId="+eosdId;
    callAjax(url,'listEosDDetailDataDiv',null,null);
    return false;
}

function loadEosDDetailList(params){
    callAjaxEx('eosDDetailList.do','ajaxContent','eosDDetails.do','eosDDetailList',params);
    return false;
}

function delEosDDetails(){
    var checkflag = false;
    var detId = document.forms['eosDForm'].eosDMaterialChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjax('delEosDDetail.do',null,document.forms['eosDForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('eosDMaterialTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}

function addEosDDetail(){
    var list=document.forms['eosDForm'].materialList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    list=null;
    var selId=document.forms['eosDForm'].material;
    if(selId!=null){
        if(selId.length!=null){
            for (i=0;i<selId.length;i++) {
                if(id==selId[i].value){
                    alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
                    return false;
                }
            }
        }else if(id==selId.value){
            alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
    }
    selId=null;

    var emrirId=getEmrirId();
    if(emrirId=='') return true;
    callAjax("getMaterialForEosD.do?ematId="+id+"&emrirId="+emrirId,null,null,function(data){
        setAjaxData(data,'eosDDetailHideDiv');
        var matTable=document.getElementById('eosDMaterialDetailTbl');
        var detTable=document.getElementById('eosDMaterialTbl');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
    return false;
}

function printEosD(eosdId) {
    var eosdId=document.forms['eosDForm'].eosdId;
    if(eosdId!=null) callServer('eosDPrint.do?eosdId='+eosdId.value);
    eosdId=null;
}

function inventoryForm(invId){
    var url="inventoryForm.do";
    if(invId!=null) url=url+"?invId="+invId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(invId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('invDateInventory').value=date+'/'+month+'/'+currentTime.getFullYear();

            list=document.forms['inventoryForm'].stoId;
            if(list!=null) {
                stoIdChangedInventory(list);
            }
            list=null;
        } else {
            setMaterialInventory(invId);
        }
    });
    return false;
}

function saveInventory() {
    if(scriptFunction=="saveInventory()") return false;
    scriptFunction="saveInventory()";
    callAjaxCheckError("saveInventory.do",null,document.forms['inventoryForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //if(data!="") {
        //    loadInventoryList();
        //}
    });
    return false;
}

function delInventorys(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        callAjaxCheckError('delInventory.do',null,document.forms['inventorysForm'],function(data){
            var index=data.indexOf('error:');
            if(index==0) {
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
            } else {
                loadInventoryList();
            }
        });
    return false;
}

function setMaterialInventory(invId) {
    var url="getMaterialInventory.do";
    if(invId!=null) url+="?invId="+invId;
    callAjax(url,'listInventoryDetailDataDiv',null,null);
    return false;
}

function loadInventoryList(params){
    callAjaxEx('inventoryList.do','ajaxContent','searchInventory.do','inventoryList',params);
    return false;
}
function loadInventoryListSort(params){
    callAjaxEx('searchInventory.do','inventoryList',null,null,params);
    return false;
}
function searchInventory(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchInventory.do","inventoryList",form,null);
    form=null;
    return false;
}

function searchAdvInventoryForm(){
    //    callAjax('searchAdvInventoryForm.do',null,null,showPopupForm);
    return false;
}

function searchAdvInventory(){
    //    callAjax('searchAdvInventory.do',null,document.forms['searchInventoryForm'],getSearchAdvInventoryData);
    //    hidePopupForm();
    return false;
}

function getSearchAdvInventoryData(data){
//    setAjaxData(data,'inventoryList');
}

function stoIdChangedInventory(list) {
    if(list.selectedIndex==-1) return false;
    var stoId = list.options[list.selectedIndex].value;
    getMaterialsByStoId(stoId);
    stoId=null;
}

function getMaterialsByStoId(stoId) {
    var url="materialsByStoId.do?stoId=" + stoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listInventoryDetailDataDiv');
    });
}

// Material Carry On
function mcForm(mcId){
    var url="mcForm.do";
    if(mcId!=null) url=url+"?mcId="+mcId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(mcId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('carryOnDateMc').value=date+'/'+month+'/'+currentTime.getFullYear();
        } else {
            list=document.forms['mcForm'].orgId;
            orgIdChangedMc(list);
            list=null;
        }
        setEquipmentMc(mcId);
    });
    return false;
}

function setEquipmentMc(mcId) {
    var url="getEquipmentMc.do";
    if(mcId!=null) url+="?mcId="+mcId;
    callAjax(url,'listMcDetailDataDiv',null,null);
    return false;
}

function orgIdChangedMc(list) {
    if(list.selectedIndex==-1) return false;
    var orgId = list.options[list.selectedIndex].value;
    if (orgId > 0) {
        getMcoIdByOrgId(orgId);
    }
    orgId=null;
}

function getMcoIdByOrgId(orgId) {
    var url="mcoByOrgId.do?orgId=" + orgId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listMcoByOrgIdDiv');
    });
    url = null;
}


function saveMc() {
    if(scriptFunction=="saveMc()") return false;
    var mcNumber = document.forms['mcForm'].mcNumber;
    if(mcNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 phi\u1EBFu mang v\u00E0o");
        mcNumber.focus();
        mcNumber=null;
        return false;
    }
    mcNumber=null;

    var result = document.forms['mcForm'].result;
    var checked=0;
    for(var i=0;i<result.length;i++){
        if(result[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn k\u1EBFt qu\u1EA3 ki\u1EC3m tra.");
        return false;
    }
    result=null;
    scriptFunction="saveMc()";
    callAjaxCheckError("saveMc.do",null,document.forms['mcForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        mcForm(0)
        //if(data!="") {
        //    loadMcList();
        //}
    });
    return false;
}

function delMcs(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        callAjaxCheckError('delMc.do',null,document.forms['mcsForm'],function(data){
            var index=data.indexOf('error:');
            if(index==0) {
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : " + data.substring(6));
            } else {
                loadMcList();
            }
        });
    return false;
}

function loadMcList(params){
    callAjaxEx('mcList.do','ajaxContent','searchMc.do','mcList',params);
    return false;
}
function loadMcListSort(params){
    callAjaxEx('searchMc.do','mcList',null,null,params);
    return false;
}
function searchMc(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchMc.do","mcList",form,null);
    form=null;
    return false;
}

function searchAdvMcForm(){
    callAjax('searchAdvMcForm.do',null,null,showPopupForm);
    return false;
}

function searchAdvMc(){
    callAjax('searchAdvMc.do',null,document.forms['searchMcForm'],getSearchAdvMcData);
    hidePopupForm();
    return false;
}

function getSearchAdvMcData(data){
    setAjaxData(data,'mcList');
}

function printMc() {
    var mcId = document.forms['mcForm'].mcId.value;
    callServer('mcPrint.do?mcId=' + mcId);
    mcId=null;
    return false;
}

function printMcs() {
    callServer('mcsPrint.do?');
    return false;
}

function mcoIdChangedMc(list) {
    if(list.selectedIndex==-1) return false;
    var mcoId = list.options[list.selectedIndex].value;
    if (mcoId > 0) {
        getEquipmentMc(mcoId);
    }
    mcoId=null;
}

function getEquipmentMc(mcoId) {
    var url="equipmentByMcoId.do?mcoId=" + mcoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listEquipmentByMcoIdDiv');
    });
    url = null;
}

function delMcDetails(){
    var checkflag = false;
    var detId = document.forms['mcForm'].mcEquipmentChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = detId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delMcDetail.do',null,document.forms['mcForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('equipmentTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    } else {
        detId=null;
    }
    return false;
}

function addMcDetail(){
    var list1=document.forms['mcForm'].mcoId;
    if(list1==null) return;
    var mcoId=list1.options[list1.selectedIndex].value;
    list1=null;

    var list=document.forms['mcForm'].equipmentList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    list=null;

    var selId=document.forms['mcForm'].equipment;
    var selmcoId=document.forms['mcForm'].mcoIdHidden;
    if(selId!=null){
        if(selId.length!=null){
            for (i=0;i<selId.length;i++) {
                //                alert(id + "," + selId[i].value + "," + selmcoId[i].value + "," + mcoId);
                if((id==selId[i].value) && (selmcoId[i].value==mcoId)){
                    alert('VT/TTBDC \u0111\u00E3 t\u1ED3n t\u1EA1i');
                    return false;
                }
            }
        }else if((id==selId.value) && (selmcoId.value==mcoId)){
            //            alert(id + "," + selId.value + "," + selmcoId.value + "," + mcoId);
            alert('VT/TTBDC \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
    }
    selId=null;
    selmcoId=null

    var mcId=document.forms['mcForm'].mcId.value;
    callAjax("getEquipmentForMc.do?equId=" + id + "&mcId=" + mcId + "&mcoId=" + mcoId,null,null,function(data){
        setAjaxData(data,'mcDetailHideDiv');
        var equTable=document.getElementById('equipmentDetailTbl');
        var detTable=document.getElementById('equipmentTbl');
        if(equTable.tBodies[0]==null || detTable.tBodies[0]==null){
            equTable=null;
            detTable=null;
            return;
        }
        for( var i=equTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(equTable.tBodies[0].rows[i]);
        }
        equTable=null;
        detTable=null;
    });

    mcId=null;
    mcoId=null;
    return false;
}

function removeMcEquipment(){
    var selId=document.getElementsByName('equId');
    if(selId==null) return false;
    var tbl=document.getElementById('mcDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}

// Material Carry Out
function mcoForm(mcoId){
    var url="mcoForm.do";
    if(mcoId!=null) url=url+"?mcoId="+mcoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(mcoId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('carryOutDateMco').value=date+'/'+month+'/'+currentTime.getFullYear();
        }
        setEquipmentMco(mcoId);
    });
    return false;
}

function setEquipmentMco(mcoId) {
    var url="getEquipmentMco.do";
    if(mcoId!=null) url+="?mcoId="+mcoId;
    callAjax(url,'listMcoDetailDataDiv',null,null);
    return false;
}

function saveMco() {
    if(scriptFunction=="saveMco()") return false;
    var mcoNumber = document.forms['mcoForm'].mcoNumber;
    if(mcoNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 phi\u1EBFu mang ra");
        mcoNumber.focus();
        mcoNumber=null;
        return false;
    }
    mcoNumber=null;

    var result = document.forms['mcoForm'].result;
    var checked=0;
    for(var i=0;i<result.length;i++){
        if(result[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn k\u1EBFt qu\u1EA3 ki\u1EC3m tra.");
        return false;
    }
    result=null;
    scriptFunction="saveMco()";
    callAjaxCheckError("saveMco.do",null,document.forms['mcoForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        mcoForm(0)
        //if(data!="") {
        //    loadMcoList();
        //}
    });
    return false;
}

function delMcos(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        callAjaxCheckError('delMco.do',null,document.forms['mcosForm'],function(data){
            var index=data.indexOf('error:');
            if(index==0) {
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : " + data.substring(6));
            } else {
                loadMcoList();
            }
        });
    return false;
}

function loadMcoList(params){
    callAjaxEx('mcoList.do','ajaxContent','searchMco.do','mcoList',params);
    return false;
}
function loadMcoListSort(params){
    callAjaxEx('searchMco.do','mcoList',null,null,params);
    return false;
}
function searchMco(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchMco.do","mcoList",form,null);
    form=null;
    return false;
}

function searchAdvMcoForm(){
    callAjax('searchAdvMcoForm.do',null,null,showPopupForm);
    return false;
}

function searchAdvMco(){
    callAjax('searchAdvMco.do',null,document.forms['searchMcoForm'],getSearchAdvMcoData);
    hidePopupForm();
    return false;
}

function getSearchAdvMcoData(data){
    setAjaxData(data,'mcoList');
}

function printMco() {
    var mcoId = document.forms['mcoForm'].mcoId.value;
    callServer('mcoPrint.do?mcoId=' + mcoId);
    mcoId=null;
    return false;
}

function printMcos() {
    callServer('mcosPrint.do?');
    return false;
}

function delMcoDetails(){
    var checkflag = false;
    var detId = document.forms['mcoForm'].mcoEquipmentChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = detId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delMcoDetail.do',null,document.forms['mcoForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('equipmentTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    } else {
        detId=null;
    }
    return false;
}

function addMcoDetail(){
    selectMcoEquipment('setSelectedMcoEquipment');
    return false;
}

function removeMcoEquipment(){
    var selId=document.getElementsByName('equId');
    if(selId==null) return false;
    var tbl=document.getElementById('mcoDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}

function selectMcoEquipment(handle){
    if(handle==null) return false;
    callAjax('chooseMcoEquipmentForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchMcoEquipment();
    });
    return false;
}

function setMcoEquipmentSelected(){
    var equId = document.forms['equipmentMcoForm'].equId;
    if(equId==null) return false;
    var nameVnHidden = document.forms['equipmentMcoForm'].nameVnHidden;
    var usedCodeHidden = document.forms['equipmentMcoForm'].usedCodeHidden;
    var tbl=document.getElementById('equipmentSelectedTbl');
    var lastRow = tbl.rows.length;
    if (equId.length!=null) {
        for (i = 0; i < equId.length; i++) {
            if (equId[i].checked == true && equId[i].disabled==false) {
                equId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'equipmentSelectedChk';
                el.id = "equipmentSelectedChk";
                el.value = equId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                textNode = document.createTextNode(usedCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                var textNode = document.createTextNode(nameVnHidden[i].value);
                cell.appendChild(textNode);


                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    } else {
        if (equId.checked == true && equId.disabled==false) {
            equId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'equipmentSelectedChk';
            el.id="equipmentSelectedChk";
            el.value=equId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            textNode = document.createTextNode(usedCodeHidden[i].value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            var textNode = document.createTextNode(nameVnHidden.value);
            cell.appendChild(textNode);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    equId=null;
    nameVnHidden=null;
    usedCodeHidden=null;
    tbl=null;
}

function chooseMcoEquipmentSelected(){
    var selId=document.forms['equipmentMcoSelectedForm'].equipmentSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}

function searchMcoEquipment(params){
    var form=document.forms['selectMcoEquipmentForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    callAjax("searchMcoEquipment.do?"+params,null,form,function(data){
        setAjaxData(data,'equipmentMcoList');
        var equId = document.forms['equipmentMcoForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentMcoSelectedForm'].equipmentSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}

function delMcoEquipment(){
    var selId=document.getElementsByName('equipmentSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('equipmentSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var equId = document.forms['equipmentMcoForm'].equId;
    if(equId==null) return false;
    if (equId.length!=null){
        for (i = 0; i < equId.length; i++) {
            if (ids.indexOf(','+equId[i].value+',')>-1){
                equId[i].disabled=false;
                equId[i].checked=false;
            }
        }
        equId=null;
    }else if (ids.indexOf(','+equId.value+',')>-1){
        equId.disabled=false;
        equId.checked=false;
    }
    equId=null;
}

function setSelectedMcoEquipment(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['mcoForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("mcoEquipment.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'listMcoDetailDataDiv');
    });
}

function setEquipmentMco(mcoId) {
    var url="getEquipmentMco.do";
    if(mcoId!=null) url+="?mcoId="+mcoId;

    callAjax(url,'listMcoDetailDataDiv',null,null);
    return false;
}

// Ematerial Carry On
function emcForm(emcId){
    var url="emcForm.do";
    if(emcId!=null) url=url+"?emcId="+emcId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(emcId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('carryOnDateEmc').value=date+'/'+month+'/'+currentTime.getFullYear();
        }
        setEquipmentEmc(emcId);
    });
    return false;
}

function setEquipmentEmc(emcId) {
    var url="getEquipmentEmc.do";
    if(emcId!=null) url+="?emcId="+emcId;
    callAjax(url,'listEmcDetailDataDiv',null,null);
    return false;
}

function saveEmc() {
    if(scriptFunction=="saveEmc()") return false;
    var department = document.forms['emcForm'].department;
    if(department.value==''){
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn b\u1ED9 ph\u1EADn mang v\u00E0o");
        department.focus();
        department=null;
        return false;
    }
    department=null;

    var emcNumber = document.forms['emcForm'].emcNumber;
    if(emcNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 phi\u1EBFu mang v\u00E0o");
        emcNumber.focus();
        emcNumber=null;
        return false;
    }
    emcNumber=null;

    var result = document.forms['emcForm'].result;
    var checked=0;
    for(var i=0;i<result.length;i++){
        if(result[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn k\u1EBFt qu\u1EA3 ki\u1EC3m tra.");
        return false;
    }
    result=null;
    scriptFunction="saveEmc()";
    callAjaxCheckError("saveEmc.do",null,document.forms['emcForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        emcForm(0)
        //if(data!="") {
        //    loadEmcList();
        //}
    });
    return false;
}

function delEmcs(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        callAjaxCheckError('delEmc.do',null,document.forms['emcsForm'],function(data){
            var index=data.indexOf('error:');
            if(index==0) {
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : " + data.substring(6));
            } else {
                loadEmcList();
            }
        });
    return false;
}

function loadEmcList(params){
    callAjaxEx('emcList.do','ajaxContent','searchEmc.do','emcList',params);
    return false;
}
function loadEmcListSort(params){
    callAjaxEx('searchEmc.do','emcList',null,null,params);
    return false;
}
function searchEmc(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        form.searchvalue.value="";
    }
    if (checkflag == true) callAjax("searchEmc.do","emcList",form,null);
    form=null;
    return false;
}

function searchAdvEmcForm(){
    callAjax('searchAdvEmcForm.do',null,null,showPopupForm);
    return false;
}

function searchAdvEmc(){
    callAjax('searchAdvEmc.do',null,document.forms['searchEmcForm'],getSearchAdvEmcData);
    hidePopupForm();
    return false;
}

function getSearchAdvEmcData(data){
    setAjaxData(data,'emcList');
}

function printEmc() {
    var emcId = document.forms['emcForm'].emcId.value;
    callServer('emcPrint.do?emcId=' + emcId);
    emcId=null;
    return false;
}

function printEmcs() {
    callServer('emcsPrint.do?');
    return false;
}

function delEmcDetails(){
    var checkflag = false;
    var detId = document.forms['emcForm'].emcEquipmentChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = detId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delEmcDetail.do',null,document.forms['emcForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('equipmentTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    } else {
        detId=null;
    }
    return false;
}

function addEmcDetail(){
    var emcId=document.forms['emcForm'].emcId.value;
    var equipment=document.forms['emcForm'].equipName;
    if(equipment.value==''){
        alert("Vui l\u00F2ng nh\u1EADp VT/TTBDC");
        equipment.focus();
        emcId=null;
        equipment=null;
        return false;
    }

    var selId=document.forms['emcForm'].equipment;
    if(selId!=null){
        if(selId.length!=null){
            for (i=0;i<selId.length;i++) {
                if(equipment.value==selId[i].value){
                    alert('VT/TTBDC \u0111\u00E3 t\u1ED3n t\u1EA1i');
                    return false;
                }
            }
        }else if(equipment.value==selId.value){
            alert('VT/TTBDC \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
    }
    selId=null;

    callAjax("getEquipmentForEmc.do?equipment=" + equipment.value + "&emcId=" + emcId,null,null,function(data){
        setAjaxData(data,'emcDetailHideDiv');
        var equTable=document.getElementById('equipmentDetailTbl');
        var detTable=document.getElementById('equipmentTbl');
        if(equTable.tBodies[0]==null || detTable.tBodies[0]==null){
            equTable=null;
            detTable=null;
            return;
        }
        for( var i=equTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(equTable.tBodies[0].rows[i]);
        }
        equTable=null;
        detTable=null;
    });
    emcId=null;
    equipment=null;
    return false;
}

// Ematerial Carry Out
function emcoForm(emcoId){
    var url="emcoForm.do";
    if(emcoId!=null) url=url+"?emcoId="+emcoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(emcoId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('carryOutDateEmco').value=date+'/'+month+'/'+currentTime.getFullYear();
        } else {
            list=document.forms['emcoForm'].department;
            departmentChangedEmco(list);
            list=null;
        }
        setEquipmentEmco(emcoId);
    });
    return false;
}

function setEquipmentEmco(emcoId) {
    var url="getEquipmentEmco.do";
    if(emcoId!=null) url+="?emcoId="+emcoId;
    callAjax(url,'listEmcoDetailDataDiv',null,null);
    return false;
}

function departmentChangedEmco(list) {
    if(list.selectedIndex==-1) return false;
    var dept = list.options[list.selectedIndex].value;
    getEmcIdByDept(dept);
    dept=null;
}

function getEmcIdByDept(dept) {
    var url="emcByDept.do?department=" + dept;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listEmcByDeptDiv');
    //        var list = document.forms['emcoForm'].emcId;
    //        emcIdChangedEmco(list);
    //        list=null;
    });
    url = null;
}

function saveEmco() {
    if(scriptFunction=="saveEmco()") return false;
    var department = document.forms['emcoForm'].department;
    if(department.value==''){
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn b\u1ED9 ph\u1EADn mang ra");
        department.focus();
        department=null;
        return false;
    }
    department=null;

    var emcoNumber = document.forms['emcoForm'].emcoNumber;
    if(emcoNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 phi\u1EBFu mang ra");
        emcoNumber.focus();
        emcoNumber=null;
        return false;
    }
    emcoNumber=null;

    var result = document.forms['emcoForm'].result;
    var checked=0;
    for(var i=0;i<result.length;i++){
        if(result[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn k\u1EBFt qu\u1EA3 ki\u1EC3m tra.");
        return false;
    }
    result=null;
    scriptFunction="saveEmco()";
    callAjaxCheckError("saveEmco.do",null,document.forms['emcoForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        emcoForm(0)
        //if(data!="") {
        //    loadEmcoList();
        //}
    });
    return false;
}

function delEmcos(){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
        callAjaxCheckError('delEmco.do',null,document.forms['emcosForm'],function(data){
            var index=data.indexOf('error:');
            if(index==0) {
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : " + data.substring(6));
            } else {
                loadEmcoList();
            }
        });
    return false;
}

function loadEmcoList(params){
    callAjaxEx('emcoList.do','ajaxContent','searchEmco.do','emcoList',params);
    return false;
}
function loadEmcoListSort(params){
    callAjaxEx('searchEmco.do','emcoList',null,null,params);
    return false;
}
function searchEmco(){
    var checkflag = true;
    var form=document.forms[0];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchEmco.do","emcoList",form,null);
    form=null;
    return false;
}

function searchAdvEmcoForm(){
    callAjax('searchAdvEmcoForm.do',null,null,showPopupForm);
    return false;
}

function searchAdvEmco(){
    callAjax('searchAdvEmco.do',null,document.forms['searchEmcoForm'],getSearchAdvEmcoData);
    hidePopupForm();
    return false;
}

function getSearchAdvEmcoData(data){
    setAjaxData(data,'emcoList');
}

function printEmco() {
    var emcoId = document.forms['emcoForm'].emcoId.value;
    callServer('emcoPrint.do?emcoId=' + emcoId);
    emcoId=null;
    return false;
}

function printEmcos() {
    callServer('emcosPrint.do?');
    return false;
}

function emcIdChangedEmco(list) {
    if(list.selectedIndex==-1) return false;
    var emcId = list.options[list.selectedIndex].value;
    if (emcId > 0) {
        getEquipmentEmco(emcId);
    }
    emcId=null;
}

function getEquipmentEmco(emcId) {
    var url="equipmentByEmcoId.do?emcId=" + emcId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listEquipmentByEmcoIdDiv');
    });
    url = null;
}

function delEmcoDetails(){
    var checkflag = false;
    var detId = document.forms['emcoForm'].emcoEquipmentChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = detId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delEmcoDetail.do',null,document.forms['emcoForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('equipmentTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    } else {
        detId=null;
    }
    return false;
}

function addEmcoDetail(){
    var list1=document.forms['emcoForm'].emcId;
    if(list1==null) return;
    var emcId=list1.options[list1.selectedIndex].value;
    list1=null;

    var list=document.forms['emcoForm'].equipmentList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    list=null;

    var selId=document.forms['emcoForm'].equipment;
    var selemcId=document.forms['emcoForm'].emcIdHidden;

    if(selId!=null){
        if(selId.length!=null){
            for (i=0;i<selId.length;i++) {
                if((id==selId[i].value) && (selemcId[i].value==emcId)){
                    alert('VT/TTBDC \u0111\u00E3 t\u1ED3n t\u1EA1i');
                    return false;
                }
            }
        } else if((id==selId.value) && (selemcId.value==emcId)){
            alert('VT/TTBDC \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
    }
    selId=null;
    selemcId=null;

    var emcoId=document.forms['emcoForm'].emcoId;
    callAjax("getEquipmentForEmco.do?equipment=" + id + "&emcId=" + emcId + "&emcoId=" + emcoId.value,null,null,function(data){
        setAjaxData(data,'emcoDetailHideDiv');
        var equTable=document.getElementById('equipmentDetailTbl');
        var detTable=document.getElementById('equipmentTbl');
        if(equTable.tBodies[0]==null || detTable.tBodies[0]==null){
            equTable=null;
            detTable=null;
            return;
        }
        for( var i=equTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(equTable.tBodies[0].rows[i]);
        }
        equTable=null;
        detTable=null;
    });

    emcoId=null;
    emcId=null;
    return false;
}

function removeEmcoEquipment(){
    var selId=document.getElementsByName('equId');
    if(selId==null) return false;
    var tbl=document.getElementById('emcoDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}

//Loc
//Rfm
function loadRfmList(params){
    callAjaxEx('rfmList.do?kind=0','ajaxContent','searchRfm.do?kind=0','rfmList',params);
    return false;
}
function loadRfmList1(params){
    callAjaxEx('rfmList.do?kind=1','ajaxContent','searchRfm.do?kind=1','rfmList',params);
    return false;
}
function getRfmListData(data){
    setAjaxData(data,'ajaxContent');
    loadRfms();
    return false;
}
function loadRfms(params){
    //callAjax("rfms.do","rfmList",null,null);
    //callAjaxExChild("searchRfm.do?kind=0","rfmList",params);
    callAjaxEx('searchRfm.do?kind=0','rfmList',null,null,params);
    return false;
}
function addRfm(rfmId,kind){
    var url="rfmForm.do";
    if(rfmId!=null) url=url+"?rfmId="+rfmId+"&kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
    //loadRequestInMsv(document.forms['rfmForm'].stoId.value);
    });
    return false;
}
function rfmForm(rfmId){
    var url="rfmForm.do";
    var kind = document.forms['rfmsForm'].kind.value;
    if(rfmId!=null) url=url+"?rfmId="+rfmId;
    if(kind!=null) url=url+"?kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        //loadRequestInMsv(document.forms['rfmForm'].stoId.value);
        if(rfmId!=null){
            callAjax('listRfmDetail.do?rfmId='+rfmId,null,null,function(data){
                setAjaxData(data,'listRfmMaterialDataDiv');
            });
        }
    });
    return false;
}
function rfmVendorChanged(list){
    if(list.selectedIndex==-1) return false;
    var url="rfmVendorList.do?venId="+list.options[list.selectedIndex].value;
    callAjax(url,'rfmContractSpan',null,null);
    list=null;
    removeDeliveryMaterial();
    return false;
}
function reqChanged(list){
    //if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    loadMaterialInMsv(list.options[list.selectedIndex].value);
    list=null;
    return false;
}
function loadMaterialInMsv(reqId){
    var url='materialListForRfm.do?reqId='+reqId;
    callAjax(url,'listMaterialDataSpan',null,null);
}
function stoChanged(list){
    //if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    //loadRequestInMsv(list.options[list.selectedIndex].value);
    list=null;
    var detId = document.forms['rfmForm'].detId;
    if(detId==null) return false;
    var tbl=document.getElementById('rfmDetailTable');
    if(tbl.rows.length!=null){
        for (i=tbl.rows.length-1;i>=1;i--) {
            tbl.deleteRow(i);
        }
    }else tbl.deleteRow(1);
    tbl=null;
    detId=null;
    return false;
}
function rfmProChanged(list){
    if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    callAjax("getRfmNumber.do?stoId="+list.options[list.selectedIndex].value,"rfmNumberTd",null,null);
    return false;
}
function loadRequestInMsv(stoId){
    var url='requestListForRfm.do?stoId='+stoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listRequestDataSpan');
        if (document.getElementById('reqId')!=null) loadMaterialInMsv(document.getElementById('reqId').value);
    });
}
function removeRfmMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('rfmDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}
function selectRfmMaterial(handle){
    if(handle==null) return false;
    var stoId=document.forms['rfmForm'].stoId;
    if(stoId.selectedIndex==0){
        alert("Vui l\u00F2ng nh\u1EADp C\u1EA5p t\u1EA1i kho!");
        stoId.focus();
        stoId=null;
        return false;
    }
    callAjax('chooseRfmMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchRfmMaterialRequest();
    });
    return false;
}
function chooseRfmMaterialSelected(){
    var selId=document.forms['materialRfmRequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}
function searchRfmMaterialRequest(params){
    var form=document.forms['selectRfmMaterialForm'];
    var kind=document.forms['rfmForm'].kind.value;
    var stoId=document.forms['rfmForm'].stoId.value;
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchRfmMaterialRequest.do?"+params+"&kind="+kind+"&stoId="+stoId,null,form,function(data){
        setAjaxData(data,'materialRfmRequestList');
        var matId = document.forms['materialRfmRequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialRfmRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}
function setRfmMaterialSelected(){
    var matId = document.forms['materialRfmRequestForm'].msrId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialRfmRequestForm'].matNameHidden;
    var matCodeHidden = document.forms['materialRfmRequestForm'].matCodeHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                if (matCodeHidden[i].value=="null") textNode = document.createTextNode("");
                else textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}
function delRfmMaterialRequest(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['materialRfmRequestForm'].matId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}

function setSelectedRfmMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['rfmForm'].matId;
    var stoId=document.forms['rfmForm'].stoId.value;
    var kind=document.forms['rfmForm'].kind.value;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("rfmMaterial.do?matIds="+ids+"&kind="+kind+"&stoId="+stoId,null,null,function(data){
        setAjaxData(data,'rfmMaterialHideDiv');
        var matTable=document.getElementById('rfmMaterialTable');


        var detTable=document.getElementById('rfmDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function saveRfm(){
    if(scriptFunction=="saveRfm()") return false;
    var rfmNumber = document.forms['rfmForm'].rfmNumber;
    var stoId = document.forms['rfmForm'].stoId;
    var orgId = document.forms['rfmForm'].orgId;
    var proId = document.forms['rfmForm'].proId;
    var kind = document.forms['rfmForm'].kind;
    if(rfmNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 y\u00EAu c\u1EA7u xu\u1EA5t kho!");
        rfmNumber.focus();
        rfmNumber=null;
        return false;
    }
    if(orgId.selectedIndex==0 && proId.selectedIndex==0){
        alert("Vui l\u00F2ng nh\u1EADp \u0110\u01A1n v\u1ECB nh\u1EADn!");
        orgId.focus();
        orgId=null;
        return false;
    }
    if(stoId.selectedIndex==-1){
        alert("Vui l\u00F2ng nh\u1EADp C\u1EA5p t\u1EA1i kho!");
        stoId.focus();
        stoId=null;
        return false;
    }
    rfmNumber=null;
    //callAjaxCheckError("saveRfm.do",null,document.forms['rfmForm'],loadRfmList);
    scriptFunction="saveRfm()";
    callAjaxCheckError("saveRfm.do",null,document.forms['rfmForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        var rfmId = document.forms['rfmForm'].rfmId;
        var kind = document.forms['rfmForm'].kind;
        if(rfmId!=null) rfmId=rfmId.value;
        else rfmId=0;
        if(kind!=null) kind=kind.value;
        else kind=0;
        addRfm(rfmId,kind);
    });
    return false;
}
function saveRfm1(){
    if(scriptFunction=="saveRfm1()") return false;
    var rfmNumber = document.forms['rfmForm'].rfmNumber;
    var stoId = document.forms['rfmForm'].stoId;
    var orgId = document.forms['rfmForm'].orgId;
    var proId = document.forms['rfmForm'].proId;
    var kind = document.forms['rfmForm'].kind;
    if(rfmNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 y\u00EAu c\u1EA7u xu\u1EA5t kho!");
        rfmNumber.focus();
        rfmNumber=null;
        return false;
    }
    if(orgId.selectedIndex==0 && proId.selectedIndex==0){
        alert("Vui l\u00F2ng nh\u1EADp \u0110\u01A1n v\u1ECB nh\u1EADn!");
        orgId.focus();
        orgId=null;
        return false;
    }
    if(stoId.selectedIndex==0){
        alert("Vui l\u00F2ng nh\u1EADp C\u1EA5p t\u1EA1i kho!");
        stoId.focus();
        stoId=null;
        return false;
    }
    rfmNumber=null;
    //callAjaxCheckError("saveRfm.do",null,document.forms['rfmForm'],loadRfmList1);
    scriptFunction="saveRfm1()";
    callAjaxCheckError("saveRfm.do",null,document.forms['rfmForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        addRfm(0,kind)
    });
    return false;
}
function searchRfm(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRfm.do?kind=0","rfmList",form,null);
    form=null;
    return false;
}
function searchRfm1(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRfm.do?kind=1","rfmList",form,null);
    form=null;
    return false;
}
function searchAdvRfmForm(){
    callAjax('searchAdvRfmForm.do?kind=0',null,null,function(data){
        //setAjaxData(data,'ajaxContent');
        showPopupForm(data);
    //loadRequestInMsv(document.forms['searchRfmForm'].stoId.value);
    });

    return false;
}
function searchAdvRfmForm1(){
    callAjax('searchAdvRfmForm.do?kind=1',null,null,function(data){
        //setAjaxData(data,'ajaxContent');
        showPopupForm(data);
    //loadRequestInMsv(document.forms['searchRfmForm'].stoId.value);
    });

    return false;
}
function searchAdvRfm(){
    callAjax('searchAdvRfm.do',null,document.forms['searchRfmForm'],getSearchAdvRfmData);
    hidePopupForm();
    return false;
}
function getSearchAdvRfmData(data){
    setAjaxData(data,'rfmList');
}
function createRfm(){
    var form=document.forms['materialInContractPrincipleForm'];
    var conId=form.conId;
    var ids = '0';
    var conIds='0';
    var matId = form.matId;
    var manyCon=false;
    var con='';
    if(matId==null || conId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                conIds+=','+conId[i].value;
                if(manyCon==false && con!='' && con!=conId[i].value) manyCon=true;
                else con=conId[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        conIds+=','+conId.value;
    }
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u1EC1 ngh\u1ECB giao h\u00E0ng ph\u1EA3i \u0111\u01B0\u1EE3c l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        callAjax("rfmForm.do?conId="+con,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            var venId=document.forms['rfmForm'].venId
            callAjax("rfmVendorList.do?venId="+venId.value,'rfmContractSpan',null,null);
            venId=null;
            callAjax('rfmMaterial.do?conId='+conIds.substring(2)+'&matId='+ids.substring(2),null,null,function(data){
                setAjaxData(data,'rfmMaterialHideDiv');
                var matTable=document.getElementById('rfmMaterialTable');
                var detTable=document.getElementById('rfmDetailTable');
                if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
                    matTable=null;
                    detTable=null;
                    return;
                }
                for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
                    detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
                }
                matTable=null;
                detTable=null;
            });
        });
    }
    matId=null;
    conId=null;
    form=null;
    return false;
}
function delRfms(){
    var checkflag = false;
    var rfmId = document.forms['rfmsForm'].rfmId;
    if(rfmId==null) return false;
    if (rfmId.length!=null) {
        for (i = 0; i < rfmId.length; i++) {
            if (rfmId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = rfmId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRfm.do','rfmList',document.forms['rfmsForm'],null);
    rfmId=null;
    return false;
}
function delRfmDetails(){
    var checkflag = false;
    var detId = document.forms['rfmForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRfmDetail.do',null,document.forms['rfmForm'],function(data){
        var tbl=document.getElementById('rfmDetailTable');
        var parentNode;
        if(detId.length!=null){
            for (i=detId.length-1;i>=0;i--) {
                if(detId[i].checked==true){
                    parentNode=detId[i].parentNode;
                    parentNode=parentNode.parentNode;
                    parentNode=parentNode.parentNode;
                    tbl.deleteRow(parentNode.rowIndex);
                }
            }
        }else if(detId.checked==true) tbl.deleteRow(1);
        parentNode=null;
        tbl=null;
        detId=null;
    });
    //removeRfmMaterial();
    return false;
}
function loadMaterialListInStore(){
    callAjax('materialInStorePanel.do',null,null,getMaterialInStoreData);
    return false;
}
function loadMaterialListInStore1(){
    callAjax('materialInStorePanel1.do',null,null,getMaterialInStoreData);
    return false;
}
function getMaterialInStoreData(data){
    setAjaxData(data,'ajaxContent');
    loadMaterialInStore();
    return false;
}
function loadMaterialInStore(params){
    var kind=document.forms['materialInStoreForm'].kind.value;
    if(kind=='0') callAjaxExChild("materialInStore.do?kind="+kind,"materialInStoreList",params);
    else callAjaxExChild("materialInStore1.do?kind="+kind,"materialInStoreList",params);
    return false;
}
function createRfm0(){
    var form=document.forms['materialInStoreForm'];
    var matId = form.msrId;
    var matIdTemp = document.forms['materialRfmRequestSelectedForm'].materialSelectedChk;
    var stoIdTemp = document.forms['materialRfmRequestSelectedForm'].stoId;
//    var storeName=form.storeNameHidden;
    var stoId=form.stoId;
    if (matId.length!=null) {
    var store='';
    var isbreak=0;
    for (i = 0; i < matId.length; i++) {
        if (matId[i].checked == true && store.length != null){
        if(store==''){
            if(store!=stoId[i].value){
                store=stoId[i].value;                
            }
        }
        else{
            if(store!=stoId[i].value){
                isbreak=1;
                break;
            }
        }
        }
    }
    if ( stoId != null)
    for (i = 0; i < stoIdTemp.length; i++) {        
        if(store==''){
            if(store!=stoIdTemp[i].value){
                store=stoIdTemp[i].value;
            }
        }
        else{
            if(store!=stoIdTemp[i].value){
                isbreak=1;
                break;
            }
        }        
    }
        if(isbreak==1){
            alert('Kh\u00F4ng th\u1EC3 l\u1EADp YCXK t\u1EEB nhi\u1EC1u h\u01A1n 1 kho. Vui l\u00F2ng ch\u1ECDn l\u1EA1i');
            return false;
        }
    }
    //alert(stoId.length)
    var ids = '0';
    var stoIds = '';
    if(matId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true)
            {
                ids+=','+matId[i].value;
                stoIds=stoId[i].value;
            }
        }
//        for (i = 0; i < matId.length; i++)
//            for (j = (i+1); j < matId.length; j++) {
//                //alert(storeName[i].value+storeName[i-1].value);
//                if(storeName[i].value != storeName[j].value && matId[i].checked == true && matId[j].checked == true) {
//                    ids='0';
//                    stoIds = '0';
//                    alert("Kh\u00F4ng th\u1EC3 ch\u1ECDn VTTB t\u1EEB nhi\u1EC1u kho kh\u00E1c nhau!");
//                    break;
//                }
//            }
    } else {
        if (matId.checked == true){
        ids+=','+matId.value;
        stoIds=stoId.value;
        }
    }
    
    if ( matIdTemp != null){
    if (matIdTemp.length!=null) {
        for (i = 0; i < matIdTemp.length; i++) {
                ids+=','+matIdTemp[i].value;
                stoIds=stoIdTemp[i].value;
        }
//        for (i = 0; i < matId.length; i++)
//            for (j = (i+1); j < matId.length; j++) {
//                //alert(storeName[i].value+storeName[i-1].value);
//                if(storeName[i].value != storeName[j].value && matId[i].checked == true && matId[j].checked == true) {
//                    ids='0';
//                    stoIds = '0';
//                    alert("Kh\u00F4ng th\u1EC3 ch\u1ECDn VTTB t\u1EEB nhi\u1EC1u kho kh\u00E1c nhau!");
//                    break;
//                }
//            }
    } else {
        ids+=','+matIdTemp.value;
        stoIds=stoIdTemp.value;
    }}
    if (ids!='0'){
        callAjax('rfmForm.do?kind=0&stoId='+stoIds,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            document.forms['rfmForm'].stoId.readonly="readonly";
            url='listRfmDetail.do?kind=0';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listRfmMaterialDataDiv');
                setSelectedRfmMaterial(ids.substring(2));
            });
        });
    }
    matId=null;
    form=null;
    return false;
}
function createRfm1(){
    var form=document.forms['materialInStoreForm'];
    var ids = '0';
    var matId = form.matId;
    if(matId==null ) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
            }
        }
    } else if(matId.checked == true){

        ids+=','+matId.value;
    }
    if (ids!='0'){
        callAjax('rfmForm.do?kind=1',null,null,function(data){
            setAjaxData(data,'ajaxContent');
            url='listRfmDetail.do?kind=1';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listRfmMaterialDataDiv');
                setSelectedRfmMaterial(ids.substring(2));
            });
        });
    }
    matId=null;
    form=null;
    return false;
}
function searchRfmMaterialInStore(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRfmMaterialInStore.do?kind=0","materialInStoreList",form,null);
    form=null;
    return false;
}
function searchRfmMaterialInStore1(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRfmMaterialInStore1.do?kind=1","materialInStoreList",form,null);
    form=null;
    return false;
}
function setRfmMaterialInStoreSelected(){
    var matId = document.forms['materialInStoreForm'].msrId;
    if(matId==null) return false;
    var stoId = document.forms['materialInStoreForm'].stoId;
    var matNameHidden = document.forms['materialInStoreForm'].matNameHidden;
    var matCodeHidden = document.forms['materialInStoreForm'].matCodeHidden;
    var unit = document.forms['materialInStoreForm'].matUnitHidden;
    var reserveQuantity = document.forms['materialInStoreForm'].reserveQuantityHidden;
    var availableQuantity = document.forms['materialInStoreForm'].availableQuantityHidden;
    var actualQuantity = document.forms['materialInStoreForm'].actualQuantityHidden;
    var requestNumber = document.forms['materialInStoreForm'].requestNumber;
    var storeName = document.forms['materialInStoreForm'].storeName;
    
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                if (matCodeHidden[i].value=="null") textNode = document.createTextNode("");
                else textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(3);
                textNode = document.createTextNode(unit[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(4);
                textNode = document.createTextNode(reserveQuantity[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(5);
                textNode = document.createTextNode(availableQuantity[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(6);
                textNode = document.createTextNode(actualQuantity[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(7);
                textNode = document.createTextNode(requestNumber[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(8);
                textNode = document.createTextNode(storeName[i].value);
                cell.appendChild(textNode);

                var cell = row.insertCell(9);
                var el = document.createElement('input');
                el.type = 'hidden';
                el.name = 'stoId';
                el.id="stoId";
                el.value=stoId[i].value;
                cell.appendChild(el);

                var cell = row.insertCell(10);
                var el = document.createElement('input');
                el.type = 'hidden';
                el.name = 'storeNameHidden';
                el.id="storeNameHidden";
                el.value=storeName[i].value;
                cell.appendChild(el);

                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(3);
            textNode = document.createTextNode(unit.value);
            cell.appendChild(textNode);

            cell = row.insertCell(4);
            textNode = document.createTextNode(reserveQuantity.value);
            cell.appendChild(textNode);

            cell = row.insertCell(5);
            textNode = document.createTextNode(availableQuantity.value);
            cell.appendChild(textNode);

            cell = row.insertCell(6);
            textNode = document.createTextNode(actualQuantity.value);
            cell.appendChild(textNode);

            cell = row.insertCell(7);
            textNode = document.createTextNode(requestNumber.value);
            cell.appendChild(textNode);

            cell = row.insertCell(8);
            textNode = document.createTextNode(storeName.value);
            cell.appendChild(textNode);

            var cell = row.insertCell(9);
                var el = document.createElement('input');
                el.type = 'hidden';
                el.name = 'stoId';
                el.id="stoId";
                el.value=stoId.value;
                cell.appendChild(el);
                
             var cell = row.insertCell(10);
                var el = document.createElement('input');
                el.type = 'hidden';
                el.name = 'storeNameHidden';
                el.id="storeNameHidden";
                el.value=storeName.value;
                cell.appendChild(el);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}

function delRfmMaterialInstore(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['materialInStoreForm'].msrId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}

function rfmCheckQuantity(detId){
    var detquantity=document.getElementById("detquantity"+detId).value;
    var qt=document.getElementById("detqt"+detId).value;
    var qt1=document.getElementById("detqt1"+detId).value;
    if (detquantity/1<1) {
        document.getElementById("detquantity"+detId).value=qt/1+qt1/1;
        alert("SL nh\u1EADp v\u00E0o ph\u1EA3i l\u1EDBn h\u01A1n 0!");
    }
    if (detquantity/1>(qt/1+qt1/1)) {
        document.getElementById("detquantity"+detId).value=qt/1+qt1/1;
        alert("SL nh\u1EADp v\u00E0o ph\u1EA3i nh\u1ECF h\u01A1n SL c\u00F3 th\u1EC3 d\u00F9ng! SL <= "+document.getElementById("detquantity"+detId).value);
    }
    return false;
}
function printRfm(){
    var rfmId=document.forms['rfmForm'].rfmId;
    if(rfmId!=null) callServer('rfmPrint.do?rfmId='+rfmId.value+"&kind=0");
    rfmId=null;
}
function printRfm1(){
    var rfmId=document.forms['rfmForm'].rfmId;
    if(rfmId!=null) callServer('rfmPrint.do?rfmId='+rfmId.value+"&kind=1");
    rfmId=null;
}
function printMaterialInStore(){
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            return false;
        }
    }else form.searchvalue.value="";
    var value = encodeURIComponent(form.searchvalue.value);
    var extraValue = encodeURIComponent(form.extraSearchValue.value);
    var id=form.searchid.value;
    var values=form.searchvalues.value;
    form=null;
    callServer('materialInStorePrint.do?searchid='+id+'&value='+value+'&values='+values+'&extraValue='+extraValue);
//callServer('materialInStorePrint.do');
}

//Equipment
function getEquipmentTabs(handle){
    callAjax('equipmentTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addEquipmentTabs();
        handle();
    });
}
function getEquipmentTabs2(handle){
    callAjax('equipmentTabs2.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addEquipmentTabs();
        handle();
    });
}
function addEquipmentTabs(){
    displayTabs("equipmentTabs","tabChild",equipmentTabHandle);
}
function equipmentTabHandle(child){
    //    if(child.isLoaded=='true') return true;
    var equId=getEquipmentId();
    var kind=getEquipmentKind();
    if(equId=='') return true;
    if (child.id=='tabChild1') {
        loadEquipment(equId,kind);
    } else if (child.id=='tabChild4') {
        loadTransferProcess();
    } else if (child.id=='tabChild5') {
        loadRepairNotPlan();
    }
    child.isLoaded='true';
    child=null;
}

function setEquipmentId(equId){
    var equ=document.getElementById('equIdHidden');
    if(equ!=null){
        equ.value=equId;
        equ=null;
    }
}
function setEquipmentName(equipmentName){
    var equ=document.getElementById('equipmentNameHidden');
    if(equ!=null){
        equ.value=equipmentName;
        equ=null;
    }
}
function setUsedCode(usedCode){
    var equ=document.getElementById('usedCodeHidden');
    if(equ!=null){
        equ.value=usedCode;
        equ=null;
    }
}
function setColorCode(colorCode){
    var equ=document.getElementById('colorCodeHidden');
    if(equ!=null){
        equ.value=colorCode;
        equ=null;
    }
}
function setManageCode(manageCode){
    var equ=document.getElementById('manageCodeHidden');
    if(equ!=null){
        equ.value=manageCode;
        equ=null;
    }
}
function getEquipmentId(){
    var equ=document.getElementById('equIdHidden');
    var equId='';
    if(equ!=null){
        equId=equ.value;
        equ=null;
    }
    return equId;
}
function setKind(kind){
    document.getElementById('kindHidden').value=kind;    
}
function getEquipmentKind(){
    var equ=document.getElementById('kindHidden');
    var kind='';
    if(equ!=null){
        kind=equ.value;
        equ=null;
    }
    return kind;
}

function loadEquipmentList(params){
    //callAjax('equipmentList.do',null,null,getEquipmentListData);
    callAjaxEx('equipmentList.do?kind=2','ajaxContent','searchEquipment.do?kind=2','equipmentList',params);
    //callAjaxEx('equipmentPanel.do','tabChild1','equipmentList.do','equipmentList',params);
    return false;
}
function loadEquipmentListSort(params){
    callAjaxEx('searchEquipment.do?kind=2','equipmentList',null,null,params);
    return false;
}
function loadEquipmentList2(params){
    //callAjax('equipmentList.do',null,null,getEquipmentListData);
    callAjaxEx('equipmentList.do?kind=3','ajaxContent','searchEquipment.do?kind=3','equipmentList',params);
    //callAjaxEx('equipmentPanel.do','tabChild1','equipmentList.do','equipmentList',params);
    return false;
}
function getEquipmentListData(data){
    setAjaxData(data,'ajaxContent');
    loadEquipments();
    return false;
}
function loadEquipments(){
    callAjax("equipments.do","equipmentList",null,null);
    return false;
}

function loadEquipment(equId,kind){
    callAjax('equipmentForm.do?kind='+kind+'&equId='+equId,null,null,function(data){
        setAjaxData(data,'tabChild1');
        setEquipmentId(equId);
        var equipmentName = document.forms['addEquipment'].equipmentName.value;
        var usedCode = document.forms['addEquipment'].usedCode.value;
        var colorCode = document.forms['addEquipment'].colorCode.value;
        var manageCode = document.forms['addEquipment'].manageCode.value;
        setEquipmentName(equipmentName);
        setUsedCode(usedCode);
        setColorCode(colorCode);
        setManageCode(manageCode);
        setKind(kind);        
        callAjax("repairplans.do?equId="+equId,"repairplanList",null,null);
        callAjax("verifiedplans.do?equId="+equId,"verifiedplanList",null,null);
        if(equId!=null) {
            loadAttachFileSystem(20,equId,'divAttachFileSystem');
        }
    });
}
function addEquipment(equId){
    var kind=document.forms['equipmentsForm'].kind.value;
    var url="equipmentForm.do?kind="+kind;
    if(equId!=null) url=url+"&equId="+equId;
    //callAjax(url,"ajaxContent",null,null);
    if(equId!=null) getEquipmentTabs(function(){
        loadEquipment(equId,kind);
    });
    else callAjax('equipmentForm.do?kind='+kind,null,null,function(data){
        setAjaxData(data,'ajaxContent');
    });
    return false;
}

function saveEquipment() {
//    var manageCode = document.forms['addEquipment'].manageCode;
//    var testNumber = document.forms['addEquipment'].testNumber;
    var usedCode = document.forms['addEquipment'].usedCode;
    var kind = document.forms['addEquipment'].kind;
//    var colorCode = document.forms['addEquipment'].colorCode;
//    if (manageCode.value.length==0){
 ///       alert("Nh\u1EADp v\u00E0o M\u00E3 qu\u1EA3n l\u00FD!");
 //       manageCode.focus();
  //      manageCode=null;
 //       return false;
 //   }
 //   if (testNumber.value.length==0){
  //      alert("Nh\u1EADp v\u00E0o S\u1ED1 bi\u00EAn b\u1EA3n ki\u1EC3m tra, nghi\u1EC7m thu!");
  //      testNumber.focus();
   //     testNumber=null;
    //    return false;
  //  }
    if (usedCode.value.length==0){
        alert("Nh\u1EADp v\u00E0o M\u00E3 s\u1EED d\u1EE5ng!");
        usedCode.focus();
        usedCode=null;


        return false;
    }
 //   if (colorCode.value.length==0){
//        alert("Nh\u1EADp v\u00E0o M\u00E3 m\u00E0u!");
//        colorCode.focus();
//        colorCode=null;
//        return false;
//    }
    if (kind.value ==1){
    //callAjaxCheckError("addEquipment.do",null,document.forms['addEquipment'],loadEquipmentList);
    callAjaxCheckError("addEquipment.do",null,document.forms['addEquipment'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });
    }
    if (kind.value ==2){
    //callAjaxCheckError("addEquipment.do",null,document.forms['addEquipment'],loadEquipmentList2);
    callAjaxCheckError("addEquipment.do",null,document.forms['addEquipment'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });
    }
    return false;
}
function searchEquipment(kind){
    var checkflag = true;
    if (document.forms['searchEquipment'].searchid.value!=0) {
        if (document.forms['searchEquipment'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchEquipment'].searchvalue.value="";
    }
    if (checkflag == true) {
        if (kind==2){
        callAjax("searchEquipment.do?kind="+kind,"equipmentList",document.forms['searchEquipment'],null);
        }
        if (kind==3){
        callAjax("searchEquipment2.do?kind="+kind,"equipmentList",document.forms['searchEquipment'],null);
        }
    }
    return false;
}
function delEquipments(){
    var checkflag = false;
    var equId = document.forms['equipmentsForm'].equId;
    if (equId.length!=null) {
        for (i = 0; i < equId.length; i++) {
            if (equId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = equId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delEquipment.do','equipmentList',document.forms['equipmentsForm'],null);
        }
    }
    equId=null;
    return false;
}
function searchAdvEquipmentForm(kind){   
    callAjax('searchAdvEquipmentForm.do?kind='+kind,null,null,showPopupForm);    
    return false;
}
function searchAdvEquipment(){
    var kind = document.forms['searchEquipmentForm'].kind.value;
    if (kind==1){
    callAjax('searchAdvEquipment.do',null,document.forms['searchEquipmentForm'],getSearchAdvEquipmentData);
    }
    if (kind==2){
    callAjax('searchAdvEquipment2.do',null,document.forms['searchEquipmentForm'],getSearchAdvEquipmentData);
    }
    hidePopupForm();
    return false;
}
function getSearchAdvEquipmentData(data){
    setAjaxData(data,'equipmentList');
}
function printEquipment() {
    var checkflag = false;
    var rpId = document.forms['addEquipment'].rpId;
    var arrId = "";
    if (rpId.length!=null) {
        for (i = 0; i < rpId.length; i++) {
            if (rpId[i].checked == true) {
                checkflag = true;
                if (arrId.length>0){
                    arrId = arrId + "," +rpId[i].value;
                }else{
                    arrId = rpId[i].value;
                }
                
                //break;
            }
        }
    } else {

        checkflag = rpId.checked;
    }
    if (checkflag == true) {
        //if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callServer('printEquipment.do?rpId='+arrId);
        //}
    }
    rpId=null;
    return false;
}

//RepairPlan
function loadRepairPlanList(params){
    //callAjax('repairplanList.do',null,null,getRepairPlanListData);
    callAjaxEx('repairplanList.do','tabChild2','searchRepairPlan.do','repairplanList',params);
    return false;
}
function loadRepairPlanListSort(params){
    callAjaxEx('searchRepairPlan.do','repairplanList',null,null,params);
    return false;
}
function getRepairPlanListData(data){
    setAjaxData(data,'tabChild2');
    loadRepairPlan();
    return false;
}
function loadRepairPlans(){
    callAjax("repairplans.do","repairplanList",null,null);
    return false;
}
function loadRepairPlan(equId,params){
    var equId=document.getElementById('equIdHidden').value;
    callAjaxEx('repairplanList.do','tabChild2','repairplans.do?equId='+equId,'repairplanList',params);
    return false;
}
function copyRepairPlan(rpId){
    var copyNumber=document.forms['addEquipment'].copyNumber.value;
    var checkflag = false;
    var rpId = document.forms['addEquipment'].rpId;
    var arrId = "";
    if (rpId.length!=null) {
        for (i = 0; i < rpId.length; i++) {
            if (rpId[i].checked == true) {
                checkflag = true;
                if (arrId.length>0){
                    arrId = arrId + "," +rpId[i].value;
                }else{
                    arrId = rpId[i].value;
                }

                //break;
            }
        }
    } else {

        checkflag = rpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n sao chép nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('copyRepairPlan.do?copyNumber='+copyNumber,'repairplanList',document.forms['addEquipment'],null);
        }
    }
    rpId=null;
    return false;
}
function addRepairPlan(rpId){
    var equId=document.getElementById('equIdHidden').value;
    var equipmentName = document.getElementById('equipmentNameHidden').value;
    var url="repairplanForm.do";
    if(rpId!=null) url=url+"?rpId="+rpId+"&equId="+equId;
    callAjax(url,null,null,function(data){
        showPopupForm(data);
        document.forms['repairPlansForm'].equipmentName.value = equipmentName;
    });
    return false;
}
function saveRepairPlan() {
    if(scriptFunction=="saveRepairPlan()") return false;
    var equId=document.getElementById('equIdHidden').value;
    var timeTrue = document.forms['repairPlansForm'].timeTrue;
    var repairPart = document.forms['repairPlansForm'].repairPart;
    var repairKind = document.forms['repairPlansForm'].repairKind;
    var status = document.forms['repairPlansForm'].status;
    var orgId = document.forms['repairPlansForm'].orgId;
    var performPart = document.forms['repairPlansForm'].performPart;
    if (repairPart.value.length==0){
        alert("Nh\u1EADp v\u00E0o H\u1EA1ng m\u1EE5c BDSC!");
        repairPart.focus();
        repairPart=null;
        return false;
    }
    if (repairKind.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o Lo\u1EA1i BDSC!");
        repairKind.focus();
        repairKind=null;
        return false;
    }
    if (status.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o T\u00ECnh tr\u1EA1ng BDSC!");
        status.focus();
        status=null;
        return false;
    }
    if (orgId.selectedIndex==0 && performPart.value.length==0){
        alert("Nh\u1EADp v\u00E0o B\u1ED9 ph\u1EADn s\u1EEDa ch\u1EEFa!");
        orgId.focus();
        orgId=null;
        return false;
    }
    //callAjaxCheckError("addRepairPlan.do?equId="+equId,null,document.forms['repairPlansForm'],getRepairPlanListData);
    scriptFunction="saveRepairPlan()";
    callAjaxCheckError("addRepairPlan.do?equId="+equId,null,document.forms['repairPlansForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        callAjax("repairplans.do?equId="+equId,"repairplanList",null,null);
    });
    hidePopupForm();
    return false;
}
function searchRepairPlan(){
    var checkflag = true;
    var equId=document.getElementById('equIdHidden').value;
    if (document.forms['searchRepairPlan'].searchid.value!=0) {
        if (document.forms['searchRepairPlan'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchRepairPlan'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchRepairPlan.do?equId="+equId,"repairplanList",document.forms['searchRepairPlan'],null);
    }
    return false;
}
function delRepairPlans(){
    var checkflag = false;
    var rpId = document.forms['repairplansForm'].rpId;
    var equId=document.getElementById('equIdHidden').value;
    if (rpId.length!=null) {
        for (i = 0; i < rpId.length; i++) {
            if (rpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {

        checkflag = rpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delRepairPlan.do?equId='+equId,'repairplanList',document.forms['repairplansForm'],null);
        }
    }
    rpId=null;
    return false;
}
function searchAdvRepairPlanForm(){
    callAjax('searchAdvRepairPlanForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvRepairPlan(){
    var equId=document.getElementById('equIdHidden').value;
    callAjax('searchAdvRepairPlan.do?equId='+equId,null,document.forms['searchRepairPlanForm'],getSearchAdvRepairPlanData);
    hidePopupForm();
    return false;
}
function getSearchAdvRepairPlanData(data){
    setAjaxData(data,'repairplanList');
}
function removeRepairMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('repairMaterialTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}
function selectRepairMaterial(handle){
    var equId=document.forms['repairPlansForm'].equId.value
    var rpId=document.forms['repairPlansForm'].rpId.value
    document.forms['addEquipment'].timeTrue.value=document.forms['repairPlansForm'].timeTrue.value
    if(handle==null) return false;
    callAjax('chooseRepairMaterialForm.do',null,null,function(data){
        showPopupFormById("popupDialog",data);
        document.getElementById('callbackFunc').value=handle;
        document.forms['repairMaterialForm'].equId.value=equId;
        document.forms['repairMaterialForm'].rpId.value=rpId;
        searchRepairMaterial();
    });
    return false;
}
function chooseRepairMaterialSelected(){    
    var selId=document.forms['repairMaterialSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");    
    hidePopupForm();
    document.forms['repairPlansForm'].timeTrue.value=document.forms['addEquipment'].timeTrue.value
}
function searchRepairMaterial(params){
    var form=document.forms['selectRepairMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchRepairMaterial.do?"+params,null,form,function(data){
        setAjaxData(data,'repairMaterialList');
        var matId = document.forms['repairMaterialForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['repairMaterialSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}
function setRepairMaterialSelected(){
    var matId = document.forms['repairMaterialForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['repairMaterialForm'].matNameHidden;
    var matCodeHidden = document.forms['repairMaterialForm'].matCodeHidden;
    var unitHidden = document.forms['repairMaterialForm'].unitHidden;
    var quantityHidden = document.forms['repairMaterialForm'].quantityHidden;
    var tbl=document.getElementById('repairMaterialSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                if (matCodeHidden[i].value=="null") textNode = document.createTextNode("");
                else textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(3);
                if (unitHidden[i].value=="null") textNode = document.createTextNode("");
                else textNode = document.createTextNode(unitHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(4);
                var textNode = document.createTextNode(quantityHidden[i].value);
                cell.appendChild(textNode);

                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(3);
            if (unitHidden[i].value=="null") textNode = document.createTextNode("");
            else textNode = document.createTextNode(unitHidden[i].value);
            cell.appendChild(textNode);

            cell = row.insertCell(4);
            var textNode = document.createTextNode(quantityHidden[i].value);
            cell.appendChild(textNode);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}
function delRepairMaterials(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('repairMaterialSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['repairMaterialForm'].matId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}
function setSelectedRepairMaterial(ids){
    addRepairPlan(document.forms['repairMaterialForm'].rpId.value);
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms[0].matId;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("repairMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'repairMaterialHideDiv');
        var matTable=document.getElementById('repairMaterialsTable');
        var detTable=document.getElementById('repairMaterialTable');

        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}

//VerifiedPlan
function loadVerifiedPlanList(params){
    //callAjax('verifiedplanList.do',null,null,getVerifiedPlanListData);
    callAjaxEx('verifiedplanList.do','ajaxContent','searchVerifiedPlan.do','verifiedplanList',params);
    return false;
}
function loadVerifiedPlanListSort(params){
    callAjaxEx('searchVerifiedPlan.do','verifiedplanList',null,null,params);
    return false;
}
function getVerifiedPlanListData(data){
    setAjaxData(data,'tabChild3');
    loadVerifiedPlan();
    return false;
}
function loadVerifiedPlans(){
    callAjax("verifiedplans.do","verifiedplanList",null,null);
    return false;
}
function loadVerifiedPlan(equId,params){
    var equId=document.getElementById('equIdHidden').value;
    callAjaxEx('verifiedplanList.do','tabChild3','verifiedplans.do?equId='+equId,'verifiedplanList',params);
    return false;
}
function addVerifiedPlan(vpId){
    var equId=document.getElementById('equIdHidden').value;
    var equipmentName = document.getElementById('equipmentNameHidden').value;
    var url="verifiedplanForm.do";
    if(vpId!=null) url=url+"?vpId="+vpId+"&equId="+equId;
    callAjax(url,null,null,function(data){
        showPopupForm(data);
        document.forms['verifiedPlanForm'].equipmentName.value = equipmentName;
    });
    return false;
}
function saveVerifiedPlan() {
    if(scriptFunction=="saveVerifiedPlan()") return false;
    var equId=document.getElementById('equIdHidden').value;
    var timePlan = document.forms['verifiedPlanForm'].timePlan;
    var timeTrue = document.forms['verifiedPlanForm'].timeTrue;
    var status = document.forms['verifiedPlanForm'].status;
    var orgId = document.forms['verifiedPlanForm'].orgId;
    var performPart = document.forms['verifiedPlanForm'].performPart;
    if (timePlan.value.length==0){
        alert("Nh\u1EADp v\u00E0o Th\u1EDDi gian KDHC theo k\u1EBF ho\u1EA1ch!");
        timePlan.focus();
        timePlan=null;
        return false;
    }
    if (status.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o T\u00ECnh tr\u1EA1ng KDHC!");
        status.focus();
        status=null;
        return false;
    }
    if (orgId.selectedIndex==0 && performPart.value.length==0){
        alert("Nh\u1EADp v\u00E0o B\u1ED9 ph\u1EADn s\u1EEDa ch\u1EEFa!");
        orgId.focus();
        orgId=null;
        return false;
    }
    //callAjaxCheckError("addVerifiedPlan.do?equId="+equId,null,document.forms['verifiedPlanForm'],getVerifiedPlanListData);
    scriptFunction="saveVerifiedPlan()";
    callAjaxCheckError("addVerifiedPlan.do?equId="+equId,null,document.forms['verifiedPlanForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    hidePopupForm();
    return false;
}
function searchVerifiedPlan(){
    var checkflag = true;
    var equId=document.getElementById('equIdHidden').value;
    if (document.forms['searchVerifiedPlan'].searchid.value!=0) {
        if (document.forms['searchVerifiedPlan'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchVerifiedPlan'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchVerifiedPlan.do?equId="+equId,"verifiedplanList",document.forms['searchVerifiedPlan'],null);
    }
    return false;
}
function delVerifiedPlans(){
    var checkflag = false;
    var vpId = document.forms['verifiedplansForm'].vpId;
    var equId=document.getElementById('equIdHidden').value;
    if (vpId.length!=null) {
        for (i = 0; i < vpId.length; i++) {
            if (vpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = vpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delVerifiedPlan.do?equId='+equId,'verifiedplanList',document.forms['verifiedplansForm'],null);
        }
    }
    vpId=null;
    return false;
}
function searchAdvVerifiedPlanForm(){
    callAjax('searchAdvVerifiedPlanForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvVerifiedPlan(){
    var equId=document.getElementById('equIdHidden').value;
    callAjax('searchAdvVerifiedPlan.do?equId='+equId,null,document.forms['searchVerifiedPlanForm'],getSearchAdvVerifiedPlanData);
    hidePopupForm();
    return false;
}
function getSearchAdvVerifiedPlanData(data){
    setAjaxData(data,'verifiedplanList');
}

//TransferProcess
function loadTransferProcessList(params){
    //callAjax('transferprocessList.do',null,null,getTransferProcessListData);
    callAjaxEx('transferprocessList.do','ajaxContent','searchTransferProcess.do','transferprocessList',params);
    return false;
}
function loadTransferProcessListSort(params){
    callAjaxEx('searchTransferProcess.do','transferprocessList',null,null,params);
    return false;
}
function getTransferProcessListData(data){
    setAjaxData(data,'tabChild4');
    loadTransferProcess();
    return false;
}
function loadTransferProcesss(){
    callAjax("transferprocesss.do","transferprocessList",null,null);
    return false;
}
function loadTransferProcess(equId,params){
    var equId=document.getElementById('equIdHidden').value;
    callAjaxEx('transferprocessList.do','tabChild4','transferprocesss.do?equId='+equId,'transferprocessList',params);
    return false;
}
function addTransferProcess(tpId){
    var equId=document.getElementById('equIdHidden').value;
    var equipmentName = document.getElementById('equipmentNameHidden').value;
    var usedCode = document.getElementById('usedCodeHidden').value;
    var colorCode = document.getElementById('colorCodeHidden').value;
    var url="transferprocessForm.do";
    if(tpId!=null) url=url+"?tpId="+tpId+"&equId="+equId;
    callAjax(url,'tabChild4',null,function(data){
        setAjaxData(data,'tabChild4');
        document.forms['addTransferProcess'].equipmentName.value = equipmentName;
        document.forms['addTransferProcess'].usedCode.value = usedCode;
        document.forms['addTransferProcess'].colorCode.value = colorCode;
    });
    return false;
}
function saveTransferProcess() {
    if(scriptFunction=="saveTransferProcess()") return false;
    var equId=document.getElementById('equIdHidden').value;
    var receiveOrg = document.forms['addTransferProcess'].receiveOrg;
    var receiveEmp = document.forms['addTransferProcess'].receiveEmp;
    var project = document.forms['addTransferProcess'].project;
    var receiveDate = document.forms['addTransferProcess'].receiveDate;
    var returnDate = document.forms['addTransferProcess'].returnDate;
    if (receiveOrg.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o \u0110\u01A1n v\u1ECB nh\u1EADn!");
        receiveOrg.focus();
        receiveOrg=null;
        return false;
    }
    if (receiveEmp.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o Ng\u01B0\u1EDDi nh\u1EADn!");
        receiveEmp.focus();
        receiveEmp=null;
        return false;
    }
    if (project.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o Ph\u1EE5c v\u1EE5 d\u1EF1 \u00E1n!");
        project.focus();
        project=null;
        return false;
    }
    if (receiveDate.value.length==0){
        alert("Nh\u1EADp v\u00E0o Ng\u00E0y nh\u1EADn!");

        receiveDate.focus();
        receiveDate=null;
        return false;
    }
    if (returnDate.value.length==0){
        alert("Nh\u1EADp v\u00E0o Ng\u00E0y tr\u1EA3!");
        returnDate.focus();
        returnDate=null;
        return false;
    }
    //callAjaxCheckError("addTransferProcess.do?equId="+equId,null,document.forms['addTransferProcess'],getTransferProcessListData);
    scriptFunction="saveTransferProcess()";
    callAjaxCheckError("addTransferProcess.do?equId="+equId,null,document.forms['addTransferProcess'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}
function searchTransferProcess(){
    var checkflag = true;
    var equId=document.getElementById('equIdHidden').value;

    if (document.forms['searchTransferProcess'].searchid.value!=0) {
        if (document.forms['searchTransferProcess'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchTransferProcess'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchTransferProcess.do?equId="+equId,"transferprocessList",document.forms['searchTransferProcess'],null);
    }
    return false;
}
function delTransferProcesss(){
    var checkflag = false;
    var tpId = document.forms['transferprocesssForm'].tpId;
    var equId=document.getElementById('equIdHidden').value;
    if (tpId.length!=null) {
        for (i = 0; i < tpId.length; i++) {
            if (tpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = tpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delTransferProcess.do?equId='+equId,'transferprocessList',document.forms['transferprocesssForm'],null);
        }
    }
    tpId=null;
    return false;
}
function searchAdvTransferProcessForm(){
    callAjax('searchAdvTransferProcessForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvTransferProcess(){
    var equId=document.getElementById('equIdHidden').value;
    callAjax('searchAdvTransferProcess.do?equId='+equId,null,document.forms['searchTransferProcessForm'],getSearchAdvTransferProcessData);
    hidePopupForm();
    return false;
}
function getSearchAdvTransferProcessData(data){
    setAjaxData(data,'transferprocessList');
}
function orgChangeTransferProcess(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("cbxEmpListTransferProcess.do?orgId=" + value,'empListCbxTransferProcess',null,null)
    return false;

}

//RepairNotPlan
function loadRepairNotPlanList(params){
    //callAjax('repairnotplanList.do',null,null,getRepairNotPlanListData);
    callAjaxEx('repairnotplanList.do','ajaxContent','searchRepairNotPlan.do','repairnotplanList',params);
    return false;
}
function loadRepairNotPlanListSort(params){
    callAjaxEx('searchRepairNotPlan.do','repairnotplanList',null,null,params);
    return false;
}
function getRepairNotPlanListData(data){
    setAjaxData(data,'tabChild5');
    loadRepairNotPlan();
    return false;
}
function loadRepairNotPlans(){
    callAjax("repairnotplans.do","repairnotplanList",null,null);
    return false;
}
function loadRepairNotPlan(equId,params){
    var equId=document.getElementById('equIdHidden').value;
    callAjaxEx('repairnotplanList.do','tabChild5','repairnotplans.do?equId='+equId,'repairnotplanList',params);
    return false;
}
function addRepairNotPlan(rnpId){
    var equId=document.getElementById('equIdHidden').value;
    var equipmentName = document.getElementById('equipmentNameHidden').value;
    var usedCode = document.getElementById('usedCodeHidden').value;
    var manageCode = document.getElementById('manageCodeHidden').value;
    var url="repairnotplanForm.do";
    if(rnpId!=null) url=url+"?rnpId="+rnpId+"&equId="+equId;
    callAjax(url,'tabChild5',null,function(data){
        setAjaxData(data,'tabChild5');
        document.forms['repairNotPlanForm'].equipmentName.value = equipmentName;
        document.forms['repairNotPlanForm'].usedCode.value = usedCode;
        document.forms['repairNotPlanForm'].manageCode.value = manageCode;
    });
    return false;
}
function saveRepairNotPlan() {
    if(scriptFunction=="saveRepairNotPlan()") return false;
    var equId=document.getElementById('equIdHidden').value;
    var orgUsed = document.forms['repairNotPlanForm'].orgUsed;
    if (orgUsed.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o B\u1ED9 ph\u1EADn s\u1EED d\u1EE5ng!");
        orgUsed.focus();
        orgUsed=null;
        return false;
    }
    
    //callAjaxCheckError("addRepairNotPlan.do?equId="+equId,null,document.forms['repairNotPlanForm'],getRepairNotPlanListData);
    scriptFunction="saveRepairNotPlan()";
    callAjaxCheckError("addRepairNotPlan.do?equId="+equId,null,document.forms['repairNotPlanForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}
function searchRepairNotPlan(){
    var checkflag = true;
    var equId=document.getElementById('equIdHidden').value;

    if (document.forms['searchRepairNotPlan'].searchid.value!=0) {
        if (document.forms['searchRepairNotPlan'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchRepairNotPlan'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchRepairNotPlan.do?equId="+equId,"repairnotplanList",document.forms['searchRepairNotPlan'],null);
    }
    return false;
}
function delRepairNotPlans(){
    var checkflag = false;
    var rnpId = document.forms['repairnotplansForm'].rnpId;
    var equId=document.getElementById('equIdHidden').value;
    if (rnpId.length!=null) {
        for (i = 0; i < rnpId.length; i++) {
            if (rnpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = rnpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delRepairNotPlan.do?equId='+equId,'repairnotplanList',document.forms['repairnotplansForm'],null);
        }
    }
    rnpId=null;
    return false;
}
function searchAdvRepairNotPlanForm(){
    callAjax('searchAdvRepairNotPlanForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvRepairNotPlan(){
    var equId=document.getElementById('equIdHidden').value;
    callAjax('searchAdvRepairNotPlan.do?equId='+equId,null,document.forms['searchRepairNotPlanForm'],getSearchAdvRepairNotPlanData);
    hidePopupForm();
    return false;
}
function getSearchAdvRepairNotPlanData(data){
    setAjaxData(data,'repairnotplanList');
}
function orgChangeRepairNotPlan(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("cbxEmpListRepairNotPlan.do?orgId=" + value,'empListCbxRepairNotPlan',null,null)
    return false;

}

//Asset
function getAssetTabs(handle){
    callAjax('assetTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addAssetTabs();
        handle();
    });
}
function addAssetTabs(){
    displayTabs("assetTabs","tabChild",assetTabHandle);
}
function assetTabHandle(child){
    //    if(child.isLoaded=='true') return true;
    var assId=getAssetId();
    if(assId=='') return true;
    if (child.id=='tabChild1') {
        loadAsset(assId);
    } else if (child.id=='tabChild2') {
        loadAssetRepairPlan();
    } else if (child.id=='tabChild3') {
        loadAssetVerifiedPlan();
    } else if (child.id=='tabChild4') {
        loadAssetTransferProcess();
    }
    child.isLoaded='true';
    child=null;
}

function setAssetId(assId){
    var equ=document.getElementById('assIdHidden');
    if(equ!=null){
        equ.value=assId;
        equ=null;
    }
}
function setAssetName(assetName){
    var equ=document.getElementById('assetNameHidden');
    if(equ!=null){
        equ.value=assetName;
        equ=null;
    }
}
function getAssetId(){
    var equ=document.getElementById('assIdHidden');
    var assId='';
    if(equ!=null){
        assId=equ.value;
        equ=null;
    }
    return assId;
}

function loadAssetList(params){
    //callAjax('assetList.do',null,null,getAssetListData);
    callAjaxEx('assetList.do','ajaxContent','assets.do','assetList',params);
    //callAjaxEx('assetPanel.do','tabChild1','assetList.do','assetList',params);
    return false;
}
function getAssetListData(data){
    setAjaxData(data,'ajaxContent');
    loadAssets();
    return false;
}
function loadAssets(){
    callAjax("assets.do","assetList",null,null);
    return false;
}

function loadAsset(assId){
    callAjax('assetForm.do?assId='+assId,null,null,function(data){
        setAjaxData(data,'tabChild1');
        setAssetId(assId);
        var assetName = document.forms['addAsset'].assetName.value;
        var usedCode = document.forms['addAsset'].usedCode.value;
        var colorCode = document.forms['addAsset'].colorCode.value;
        setAssetName(assetName);
        setUsedCode(usedCode);
        setColorCode(colorCode);
    });
}

function addAsset(assId){
    var url="assetForm.do";
    if(assId!=null) url=url+"?assId="+assId;
    //callAjax(url,"ajaxContent",null,null);
    if(assId!=null) getAssetTabs(function(){
        loadAsset(assId);
    });
    else callAjax('assetForm.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
    });
    return false;
}

function saveAsset() {
    var decisionNumber = document.forms['addAsset'].decisionNumber;
    var manageCode = document.forms['addAsset'].manageCode;
    var assetName = document.forms['addAsset'].assetName;
    var requestNumber = document.forms['addAsset'].requestNumber;
    var contractNumber = document.forms['addAsset'].contractNumber;
    var testNumber = document.forms['addAsset'].testNumber;
    var usedCode = document.forms['addAsset'].usedCode;
    var colorCode = document.forms['addAsset'].colorCode;
    if (decisionNumber.value.length==0){
        alert("Nh\u1EADp v\u00E0o S\u1ED1 quy\u1EBFt \u0111\u1ECBnh b\u00E0n giao t\u00E0i s\u1EA3n!");
        decisionNumber.focus();
        decisionNumber=null;
        return false;
    }
    if (manageCode.value.length==0){
        alert("Nh\u1EADp v\u00E0o M\u00E3 qu\u1EA3n l\u00FD!");
        manageCode.focus();
        manageCode=null;
        return false;
    }
    if (assetName.value.length==0){
        alert("Nh\u1EADp v\u00E0o T\u00EAn TSC\u0110HH!");
        assetName.focus();
        assetName=null;
        return false;
    }
    if (requestNumber.value.length==0){
        alert("Nh\u1EADp v\u00E0o S\u1ED1 \u0110XYC!");
        requestNumber.focus();
        requestNumber=null;
        return false;
    }
    if (contractNumber.value.length==0){
        alert("Nh\u1EADp v\u00E0o S\u1ED1 H\u1EE3p \u0111\u1ED3ng!");
        contractNumber.focus();
        contractNumber=null;
        return false;
    }
    if (testNumber.value.length==0){
        alert("Nh\u1EADp v\u00E0o S\u1ED1 bi\u00EAn b\u1EA3n ki\u1EC3m tra, nghi\u1EC7m thu!");
        testNumber.focus();
        testNumber=null;
        return false;
    }
    if (usedCode.value.length==0){
        alert("Nh\u1EADp v\u00E0o M\u00E3 s\u1EED d\u1EE5ng!");
        usedCode.focus();
        usedCode=null;
        return false;
    }
    if (colorCode.value.length==0){
        alert("Nh\u1EADp v\u00E0o M\u00E3 m\u00E0u!");
        colorCode.focus();
        colorCode=null;
        return false;
    }
    //callAjaxCheckError("addAsset.do",null,document.forms['addAsset'],getAssetListData);
    callAjaxCheckError("addAsset.do",null,document.forms['addAsset'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });
    return false;
}
function searchAsset(){
    var checkflag = true;
    if (document.forms['searchAsset'].searchid.value!=0) {
        if (document.forms['searchAsset'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchAsset'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchAsset.do","assetList",document.forms['searchAsset'],null);
    }
    return false;
}
function delAssets(){
    var checkflag = false;
    var assId = document.forms['assetsForm'].assId;
    if (assId.length!=null) {
        for (i = 0; i < assId.length; i++) {
            if (assId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = assId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delAsset.do','assetList',document.forms['assetsForm'],null);
        }
    }
    assId=null;
    return false;
}
function searchAdvAssetForm(){
    callAjax('searchAdvAssetForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvAsset(){
    callAjax('searchAdvAsset.do',null,document.forms['searchAssetForm'],getSearchAdvAssetData);
    hidePopupForm();
    return false;
}
function getSearchAdvAssetData(data){
    setAjaxData(data,'assetList');
}

//AssetRepairPlan
function loadAssetRepairPlanList(params){
    //callAjax('repairplanList.do',null,null,getAssetRepairPlanListData);
    callAjaxEx('assetrepairplanList.do','tabChild2','assetrepairplans.do','repairplanList',params);
    return false;
}
function getAssetRepairPlanListData(data){
    setAjaxData(data,'tabChild2');
    loadAssetRepairPlan();
    return false;
}
function loadAssetRepairPlans(){
    callAjax("assetrepairplans.do","repairplanList",null,null);
    return false;
}
function loadAssetRepairPlan(assId,params){
    var assId=document.getElementById('assIdHidden').value;
    callAjaxEx('assetrepairplanList.do','tabChild2','assetrepairplans.do?assId='+assId,'repairplanList',params);
    return false;
}
function addAssetRepairPlan(rpId){
    var assId=document.getElementById('assIdHidden').value;
    var assetName = document.getElementById('assetNameHidden').value;
    var url="assetrepairplanForm.do";
    if(rpId!=null) url=url+"?rpId="+rpId+"&assId="+assId;
    callAjax(url,'tabChild2',null,function(data){
        setAjaxData(data,'tabChild2');
        document.forms['addAssetRepairPlan'].assetName.value = assetName;
    });
    return false;
}
function saveAssetRepairPlan() {
    var assId=document.getElementById('assIdHidden').value;
    var timeTrue = document.forms['addAssetRepairPlan'].timeTrue;
    var repairPart = document.forms['addAssetRepairPlan'].repairPart;
    var repairKind = document.forms['addAssetRepairPlan'].repairKind;
    var status = document.forms['addAssetRepairPlan'].status;
    var orgId = document.forms['addAssetRepairPlan'].orgId;
    var performPart = document.forms['addAssetRepairPlan'].performPart;
    if (timeTrue.value.length==0){
        alert("Nh\u1EADp v\u00E0o Th\u1EDDi gian BDSC th\u1EF1c t\u1EBF!");
        timeTrue.focus();
        timeTrue=null;
        return false;
    }
    if (repairPart.value.length==0){
        alert("Nh\u1EADp v\u00E0o H\u1EA1ng m\u1EE5c BDSC!");
        repairPart.focus();
        repairPart=null;
        return false;
    }
    if (repairKind.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o Lo\u1EA1i BDSC!");
        repairKind.focus();
        repairKind=null;
        return false;
    }
    if (status.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o T\u00ECnh tr\u1EA1ng BDSC!");
        status.focus();
        status=null;
        return false;
    }
    if (orgId.selectedIndex==0 && performPart.value.length==0){
        alert("Nh\u1EADp v\u00E0o B\u1ED9 ph\u1EADn s\u1EEDa ch\u1EEFa!");
        orgId.focus();
        orgId=null;
        return false;
    }
    //callAjaxCheckError("addAssetRepairPlan.do?assId="+assId,null,document.forms['addAssetRepairPlan'],getAssetRepairPlanListData);
    callAjaxCheckError("addAssetRepairPlan.do?assId="+assId,null,document.forms['addAssetRepairPlan'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });

    return false;
}
function searchAssetRepairPlan(){
    var checkflag = true;
    var assId=document.getElementById('assIdHidden').value;
    if (document.forms['searchAssetRepairPlan'].searchid.value!=0) {
        if (document.forms['searchAssetRepairPlan'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchAssetRepairPlan'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchAssetRepairPlan.do?assId="+assId,"repairplanList",document.forms['searchAssetRepairPlan'],null);
    }
    return false;
}
function delAssetRepairPlans(){
    var checkflag = false;
    var rpId = document.forms['repairplansForm'].rpId;
    var assId=document.getElementById('assIdHidden').value;
    if (rpId.length!=null) {
        for (i = 0; i < rpId.length; i++) {
            if (rpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = rpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delAssetRepairPlan.do?assId='+assId,'repairplanList',document.forms['repairplansForm'],null);
        }
    }
    rpId=null;
    return false;
}
function searchAdvAssetRepairPlanForm(){
    callAjax('searchAdvAssetRepairPlanForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvAssetRepairPlan(){
    var assId=document.getElementById('assIdHidden').value;
    callAjax('searchAdvAssetRepairPlan.do?assId='+assId,null,document.forms['searchAssetRepairPlanForm'],getSearchAdvAssetRepairPlanData);
    hidePopupForm();
    return false;
}
function getSearchAdvAssetRepairPlanData(data){
    setAjaxData(data,'repairplanList');
}

//AssetVerifiedPlan
function loadAssetVerifiedPlanList(params){
    //callAjax('verifiedplanList.do',null,null,getAssetVerifiedPlanListData);
    callAjaxEx('assetverifiedplanList.do','ajaxContent','assetverifiedplans.do','verifiedplanList',params);
    return false;
}
function getAssetVerifiedPlanListData(data){
    setAjaxData(data,'tabChild3');
    loadAssetVerifiedPlan();
    return false;
}
function loadAssetVerifiedPlans(){
    callAjax("assetverifiedplans.do","verifiedplanList",null,null);
    return false;
}
function loadAssetVerifiedPlan(assId,params){
    var assId=document.getElementById('assIdHidden').value;
    callAjaxEx('assetverifiedplanList.do','tabChild3','assetverifiedplans.do?assId='+assId,'verifiedplanList',params);
    return false;
}
function addAssetVerifiedPlan(vpId){
    var assId=document.getElementById('assIdHidden').value;
    var assetName = document.getElementById('assetNameHidden').value;
    var url="assetverifiedplanForm.do";
    if(vpId!=null) url=url+"?vpId="+vpId+"&assId="+assId;
    callAjax(url,'tabChild3',null,function(data){
        setAjaxData(data,'tabChild3');
        document.forms['addAssetVerifiedPlan'].assetName.value = assetName;
    });
    return false;
}
function saveAssetVerifiedPlan() {
    var assId=document.getElementById('assIdHidden').value;
    var timePlan = document.forms['addAssetVerifiedPlan'].timePlan;
    var timeTrue = document.forms['addAssetVerifiedPlan'].timeTrue;
    var status = document.forms['addAssetVerifiedPlan'].status;
    var orgId = document.forms['addAssetVerifiedPlan'].orgId;
    var performPart = document.forms['addAssetVerifiedPlan'].performPart;
    if (timePlan.value.length==0){
        alert("Nh\u1EADp v\u00E0o Th\u1EDDi gian KÃƒÆ’Ã¢â‚¬Å¾ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡ÃƒÆ’Ã¢â‚¬Â ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡Ãƒâ€šÃ‚Â¬??HC theo k\u1EBF ho\u1EA1ch!");
        timePlan.focus();
        timePlan=null;
        return false;
    }
    if (timeTrue.value.length==0){
        alert("Nh\u1EADp v\u00E0o Th\u1EDDi gian KÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬Ãƒâ€¦Ã‚Â¾?HC th\u1EF1c t\u1EBF!");
        timeTrue.focus();
        timeTrue=null;
        return false;
    }
    if (status.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o T\u00ECnh tr\u1EA1ng KÃƒÆ’Ã¢â‚¬Å¾ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡ÃƒÆ’Ã¢â‚¬Â ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¢ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡Ãƒâ€šÃ‚Â¬??HC!");
        status.focus();
        status=null;
        return false;
    }
    if (orgId.selectedIndex==0 && performPart.value.length==0){
        alert("Nh\u1EADp v\u00E0o B\u1ED9 ph\u1EADn s\u1EEDa ch\u1EEFa!");
        orgId.focus();
        orgId=null;
        return false;
    }
    //callAjaxCheckError("addAssetVerifiedPlan.do?assId="+assId,null,document.forms['addAssetVerifiedPlan'],getAssetVerifiedPlanListData);
    callAjaxCheckError("addAssetVerifiedPlan.do?assId="+assId,null,document.forms['addAssetVerifiedPlan'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });
    return false;
}
function searchAssetVerifiedPlan(){
    var checkflag = true;
    var assId=document.getElementById('assIdHidden').value;
    if (document.forms['searchAssetVerifiedPlan'].searchid.value!=0) {
        if (document.forms['searchAssetVerifiedPlan'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchAssetVerifiedPlan'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchAssetVerifiedPlan.do?assId="+assId,"verifiedplanList",document.forms['searchAssetVerifiedPlan'],null);
    }
    return false;
}
function delAssetVerifiedPlans(){
    var checkflag = false;
    var vpId = document.forms['verifiedplansForm'].vpId;
    var assId=document.getElementById('assIdHidden').value;
    if (vpId.length!=null) {
        for (i = 0; i < vpId.length; i++) {
            if (vpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = vpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delAssetVerifiedPlan.do?assId='+assId,'verifiedplanList',document.forms['verifiedplansForm'],null);
        }
    }
    vpId=null;
    return false;
}
function searchAdvAssetVerifiedPlanForm(){
    callAjax('searchAdvAssetVerifiedPlanForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvAssetVerifiedPlan(){
    var assId=document.getElementById('assIdHidden').value;
    callAjax('searchAdvAssetVerifiedPlan.do?assId='+assId,null,document.forms['searchAssetVerifiedPlanForm'],getSearchAdvAssetVerifiedPlanData);
    hidePopupForm();
    return false;
}
function getSearchAdvAssetVerifiedPlanData(data){
    setAjaxData(data,'verifiedplanList');
}

//AssetTransferProcess
function loadAssetTransferProcessList(params){
    //callAjax('transferprocessList.do',null,null,getAssetTransferProcessListData);
    callAjaxEx('assettransferprocessList.do','ajaxContent','assettransferprocesss.do','transferprocessList',params);
    return false;
}
function getAssetTransferProcessListData(data){
    setAjaxData(data,'tabChild4');
    loadAssetTransferProcess();
    return false;
}
function loadAssetTransferProcesss(){
    callAjax("assettransferprocesss.do","transferprocessList",null,null);
    return false;
}
function loadAssetTransferProcess(assId,params){
    var assId=document.getElementById('assIdHidden').value;
    callAjaxEx('assettransferprocessList.do','tabChild4','assettransferprocesss.do?assId='+assId,'transferprocessList',params);
    return false;
}
function addAssetTransferProcess(tpId){
    var assId=document.getElementById('assIdHidden').value;
    var assetName = document.getElementById('assetNameHidden').value;
    var usedCode = document.getElementById('usedCodeHidden').value;
    var colorCode = document.getElementById('colorCodeHidden').value;
    var url="assettransferprocessForm.do";
    if(tpId!=null) url=url+"?tpId="+tpId+"&assId="+assId;
    callAjax(url,'tabChild4',null,function(data){
        setAjaxData(data,'tabChild4');
        document.forms['addAssetTransferProcess'].assetName.value = assetName;
        document.forms['addAssetTransferProcess'].usedCode.value = usedCode;
        document.forms['addAssetTransferProcess'].colorCode.value = colorCode;
    });
    return false;
}
function saveAssetTransferProcess() {
    var assId=document.getElementById('assIdHidden').value;
    var receiveOrg = document.forms['addAssetTransferProcess'].receiveOrg;
    var receiveEmp = document.forms['addAssetTransferProcess'].receiveEmp;
    var project = document.forms['addAssetTransferProcess'].project;
    var receiveDate = document.forms['addAssetTransferProcess'].receiveDate;
    var returnDate = document.forms['addAssetTransferProcess'].returnDate;
    if (receiveOrg.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o \u0110\u01A1n v\u1ECB nh\u1EADn!");
        receiveOrg.focus();
        receiveOrg=null;
        return false;
    }
    if (receiveEmp.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o Ng\u01B0\u1EDDi nh\u1EADn!");
        receiveEmp.focus();
        receiveEmp=null;
        return false;
    }
    if (project.selectedIndex==0){
        alert("Nh\u1EADp v\u00E0o Ph\u1EE5c v\u1EE5 d\u1EF1 \u00E1n!");
        project.focus();
        project=null;
        return false;
    }
    if (receiveDate.value.length==0){
        alert("Nh\u1EADp v\u00E0o Ng\u00E0y nh\u1EADn!");
        receiveDate.focus();
        receiveDate=null;
        return false;
    }
    if (returnDate.value.length==0){
        alert("Nh\u1EADp v\u00E0o Ng\u00E0y tr\u1EA3!");
        returnDate.focus();
        returnDate=null;
        return false;
    }
    //callAjaxCheckError("addAssetTransferProcess.do?assId="+assId,null,document.forms['addAssetTransferProcess'],getAssetTransferProcessListData);
    callAjaxCheckError("addAssetTransferProcess.do?assId="+assId,null,document.forms['addAssetTransferProcess'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });
    return false;
}
function searchAssetTransferProcess(){
    var checkflag = true;
    var assId=document.getElementById('assIdHidden').value;

    if (document.forms['searchAssetTransferProcess'].searchid.value!=0) {
        if (document.forms['searchAssetTransferProcess'].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms['searchAssetTransferProcess'].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchAssetTransferProcess.do?assId="+assId,"transferprocessList",document.forms['searchAssetTransferProcess'],null);
    }
    return false;
}
function delAssetTransferProcesss(){
    var checkflag = false;
    var tpId = document.forms['transferprocesssForm'].tpId;
    var assId=document.getElementById('assIdHidden').value;
    if (tpId.length!=null) {
        for (i = 0; i < tpId.length; i++) {
            if (tpId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = tpId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delAssetTransferProcess.do?assId='+assId,'transferprocessList',document.forms['transferprocesssForm'],null);
        }
    }
    tpId=null;
    return false;
}
function searchAdvAssetTransferProcessForm(){
    callAjax('searchAdvAssetTransferProcessForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvAssetTransferProcess(){
    var assId=document.getElementById('assIdHidden').value;
    callAjax('searchAdvAssetTransferProcess.do?assId='+assId,null,document.forms['searchAssetTransferProcessForm'],getSearchAdvAssetTransferProcessData);
    hidePopupForm();
    return false;
}
function getSearchAdvAssetTransferProcessData(data){
    setAjaxData(data,'transferprocessList');
}
function orgChangeAssetTransferProcess(cbx,idx) {
    if(idx < 0)
        return false;
    var value = cbx.options.item(idx).value;
    callAjax("cbxEmpListAssetTransferProcess.do?orgId=" + value,'empListCbxAssetTransferProcess',null,null)
    return false;
}

//Delivery Notice
function loadDn2List(params){
    callAjaxEx('dn2List.do?kind=2','ajaxContent','searchDn2.do?kind=2','dn2List',params);
    return false;
}
function loadDn3List(params){
    callAjaxEx('dn2List.do?kind=3','ajaxContent','searchDn2.do?kind=3','dn2List',params);
    return false;
}
function loadDn4List(params){
    callAjaxEx('dn2List.do?kind=4','ajaxContent','searchDn2.do?kind=4','dn2List',params);
    return false;
}
function getDn2ListData(data){
    setAjaxData(data,'ajaxContent');
    loadDn2s();
    return false;
}
function loadDn2s(){
    callAjax("dn2s.do","dn2List",null,function(data){
        setAjaxData(data);
        list=document.forms['dn2Form'].whichUse;
        alert(list)
        if(list!=null)
            whichUseChangedDn2(list);
        list=null;
    });
    return false;
}
function whichUseChangedDn2(list){
    if(list.selectedIndex==-1) return false;
    var dnId=null;
    if(document.forms['dn2Form']!=null) dnId=document.forms['dn2Form'].dnId;
    getWhichUseDn2(dnId.value,list.options[list.selectedIndex].value);
    list=null;
    dnId=null;
    return false;
}
function getWhichUseDn2(dnId,whichUse){
    var url="whichUseDn2List.do?whichUse="+whichUse;
    if(dnId!=null) url+="&dnId="+dnId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'whichUseSpan');
    });
}
function addDn2(dnId){
    var url="dn2Form.do";
    if(dnId!=null) url=url+"?dnId="+dnId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        list=document.forms['dn2Form'].whichUse;
        if(list!=null)
            whichUseChangedDn2(list);
        list=null;
    //loadRequestInMsv(document.forms['dn2Form'].stoId.value);
    });
    return false;
}
function dn2Form(reqId){
    var url="dn2Form.do";
    var kind = document.forms['dn2sForm'].kind.value;
    if(reqId!=null) url=url+"?reqId="+reqId;
    if(kind!=null) url=url+"?kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        //loadRequestInMsv(document.forms['dn2Form'].stoId.value);
        if (kind==3) setSelectedDn2MaterialNotProject();
        if(reqId!=null){
            callAjax('listDn2Detail.do?reqId='+reqId,null,null,function(data){
                setAjaxData(data,'listDn2MaterialDataDiv');
            });
        }
    });
    return false;
}
function dn2VendorChanged(list){
    if(list.selectedIndex==-1) return false;
    var url="dn2VendorList.do?venId="+list.options[list.selectedIndex].value;
    callAjax(url,'dn2ContractSpan',null,null);
    list=null;
    removeDeliveryMaterial();
    return false;
}
function reqChanged(list){
    //if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    loadMaterialInMsv(list.options[list.selectedIndex].value);
    list=null;
    return false;
}
function loadMaterialInMsv(reqId){
    var url='materialListForDn2.do?reqId='+reqId;
    callAjax(url,'listMaterialDataSpan',null,null);
}
function stoChanged(list){
    //if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    //loadRequestInMsv(list.options[list.selectedIndex].value);
    list=null;
    return false;
}
function loadRequestInMsv(stoId){
    var url='requestListForDn2.do?stoId='+stoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listRequestDataSpan');
        if (document.getElementById('reqId')!=null) loadMaterialInMsv(document.getElementById('reqId').value);
    });
}
function removeDn2Material(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('dn2DetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        parentNode.removeChild(selId);
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    return false;
}
function selectDn2Material(handle){
    if(handle==null) return false;
    var proId = document.forms['dn2Form'].proId;
    var kind = document.forms['dn2Form'].kind;;
    if(proId.selectedIndex==0){
        alert("D\u1EF1 \u00E1n?");
        proId.focus();
        proId=null;
        return false;
    }
    callAjax("chooseDn2MaterialForm.do?proId="+proId.value+"&kind="+kind.value,null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchDn2MaterialRequest();
    });
    return false;
}
function chooseDn2MaterialSelected(){
    var selId=document.forms['materialDn2RequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}
function searchDn2MaterialRequest(params){
    var form=document.forms['selectDn2MaterialForm'];
    var proId=document.forms['dn2Form'].proId.value;
    var kind = document.forms['dn2Form'].kind.value;
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchDn2MaterialRequest.do?"+params+"&proId="+proId+"&kind="+kind,null,form,function(data){
        setAjaxData(data,'materialDn2RequestList');
        var matId = document.forms['materialDn2RequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialDn2RequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}
function setDn2MaterialSelected(){
    var matId = document.forms['materialDn2RequestForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialDn2RequestForm'].matNameHidden;
    var matCodeHidden = document.forms['materialDn2RequestForm'].matCodeHidden;
    var matUnitHidden = document.forms['materialDn2RequestForm'].matUnitHidden;
    var matQuantityHidden = document.forms['materialDn2RequestForm'].matQuantityHidden;
    var matDetIdHidden = document.forms['materialDn2RequestForm'].matDetIdHidden;
    var matReqDetailIdHidden = document.forms['materialDn2RequestForm'].matReqDetailIdHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                var textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(3);
                var textNode = document.createTextNode(matUnitHidden[i].value);
                cell.appendChild(textNode);
                
                cell = row.insertCell(4);
                var textNode = document.createTextNode(matQuantityHidden[i].value);
                cell.appendChild(textNode);
                
                cell = row.insertCell(5);
                var textNode = document.createTextNode(matDetIdHidden[i].value);
                cell.appendChild(textNode);
                
                cell = row.insertCell(6);
                var textNode = document.createTextNode(matReqDetailIdHidden[i].value);
                cell.appendChild(textNode);

                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(3);
            textNode = document.createTextNode(matUnitHidden.value);
            cell.appendChild(textNode);
            
            cell = row.insertCell(4);
            textNode = document.createTextNode(matQuantityHidden.value);
            cell.appendChild(textNode);
            
            cell = row.insertCell(5);
            textNode = document.createTextNode(matDetIdHidden.value);
            cell.appendChild(textNode);
            
            cell = row.insertCell(6);
            textNode = document.createTextNode(matReqDetailIdHidden.value);
            cell.appendChild(textNode);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}
function delDn2MaterialRequest(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['materialDn2RequestForm'].matId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}
function setSelectedDn2Material(ids){
    //alert(ids)
    var proId = document.forms['dn2Form'].proId.value;
    var kind = document.forms['dn2Form'].kind.value;
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['dn2Form'].matId;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("dn2MaterialProject.do?proId="+proId+"&kind="+kind+"&ids="+ids,null,null,function(data){
        setAjaxData(data,'dn2MaterialProjectHideDiv');
        var matTable=document.getElementById('dn2MaterialProjectTable');
        var detTable=document.getElementById('dn2DetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function setSelectedDn2MaterialProject(){
    var proId = document.forms['dn2Form'].proId.value;
    var kind = document.forms['dn2Form'].kind.value;
    removeDn2Material();
    callAjax("dn2MaterialProject.do?proId="+proId+"&kind="+kind,null,null,function(data){
        setAjaxData(data,'dn2MaterialProjectHideDiv');
        var matTable=document.getElementById('dn2MaterialProjectTable');
        var detTable=document.getElementById('dn2DetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function setSelectedDn2MaterialNotProject(){
    var proId = 0;
    var kind = document.forms['dn2Form'].kind.value;
    removeDn2Material();
    callAjax("dn2MaterialProject.do?proId="+proId+"&kind="+kind,null,null,function(data){
        setAjaxData(data,'dn2MaterialProjectHideDiv');
        var matTable=document.getElementById('dn2MaterialProjectTable');
        var detTable=document.getElementById('dn2DetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function saveDn2(){
    if(scriptFunction=="saveDn2()") return false;
    var dnNumber = document.forms['dn2Form'].dnNumber;
    var proId = document.forms['dn2Form'].proId;
    var kind = 0;
    if(dnNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 y\u00EAu c\u1EA7u!");
        dnNumber.focus();
        dnNumber=null;
        return false;
    }
    if(proId.selectedIndex==0){
        alert("D\u1EF1 \u00E1n?");
        proId.focus();
        proId=null;
        return false;
    }
    if(proId.selectedIndex==0){
        kind = 3;
    }
    if(proId.selectedIndex>=1){
        kind = 2;
    }
    dnNumber=null;
    scriptFunction="saveDn2()";
    //callAjaxCheckError("saveDn2.do?kind="+kind,null,document.forms['dn2Form'],loadDn2List);
    callAjaxCheckError("saveDn2.do?kind="+kind,null,document.forms['dn2Form'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });

    return false;
}
function saveDn4(){
    if(scriptFunction=="saveDn4()") return false;
    var dnNumber = document.forms['dn2Form'].dnNumber;
    var proId = document.forms['dn2Form'].proId;
    var kind = 4;
    if(dnNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 y\u00EAu c\u1EA7u!");
        dnNumber.focus();
        dnNumber=null;
        return false;
    }
    if(proId.selectedIndex==0){
        alert("D\u1EF1 \u00E1n?");
        proId.focus();
        proId=null;
        return false;
    }
    dnNumber=null;
    scriptFunction="saveDn4()";
    //callAjaxCheckError("saveDn2.do?kind="+kind,null,document.forms['dn2Form'],loadDn4List);
    callAjaxCheckError("saveDn2.do?kind="+kind,null,document.forms['dn2Form'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");

    });
    return false;
}
function searchDn2(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchDn2.do?kind=2","dn2List",form,null);
    form=null;
    return false;
}
function searchDn4(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchDn2.do?kind=4","dn2List",form,null);
    form=null;
    return false;
}
function searchAdvDn2Form(){
    callAjax('searchAdvDn2Form.do?kind=2',null,null,function(data){
        showPopupForm(data);
    });
    return false;
}
function searchAdvDn2(){
    callAjax('searchAdvDn2.do?kind=2',null,document.forms['searchDn2Form'],getSearchAdvDn2Data);
    hidePopupForm();
    return false;
}
function searchAdvDn4Form(){
    callAjax('searchAdvDn2Form.do?kind=4',null,null,function(data){
        showPopupForm(data);
    });
    return false;
}
function searchAdvDn4(){
    callAjax('searchAdvDn2.do?kind=4',null,document.forms['searchDn2Form'],getSearchAdvDn2Data);
    hidePopupForm();
    return false;
}
function getSearchAdvDn2Data(data){
    setAjaxData(data,'dn2List');
}
function delDn2s(){
    var checkflag = false;
    var dnId = document.forms['dn2sForm'].dnId;
    if(dnId==null) return false;
    if (dnId.length!=null) {
        for (i = 0; i < dnId.length; i++) {
            if (dnId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = dnId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delDn2.do?kind=2','dn2List',document.forms['dn2sForm'],null);
    dnId=null;
    return false;
}
function delDn2Details(){
    var checkflag = false;
    var detId = document.forms['dn2Form'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delDn2Detail.do',null,document.forms['dn2Form'],function(data){
        var tbl=document.getElementById('dn2DetailTable');
        var parentNode;
        if(detId.length!=null){
            for (i=detId.length-1;i>=0;i--) {
                if(detId[i].checked==true){
                    parentNode=detId[i].parentNode;
                    parentNode=parentNode.parentNode;
                    parentNode=parentNode.parentNode;
                    tbl.deleteRow(parentNode.rowIndex);
                }
            }
        }else if(detId.checked==true) tbl.deleteRow(1);
        parentNode=null;
        tbl=null;
        detId=null;
    });
    //removeDn2Material();
    return false;
}
function loadMaterialListInStore(){
    callAjax('materialInStorePanel.do',null,null,getMaterialInStoreData);
    return false;
}
function getMaterialInStoreData(data){
    setAjaxData(data,'ajaxContent');
    loadMaterialInStore();
    return false;
}
function loadMaterialInStore(params){
    var kind=document.forms['materialInStoreForm'].kind.value;
    if(kind=='0') callAjaxExChild("searchRfmMaterialInStore.do?kind="+kind,"materialInStoreList",params);
    else callAjaxExChild("searchRfmMaterialInStore1.do?kind="+kind,"materialInStoreList",params);
    return false;
}
function searchDn2MaterialInStore(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchDn2MaterialInStore.do?kind=0","materialInStoreList",form,null);
    form=null;
    return false;
}
function dn2CheckQuantity(detId){
    var detquantity=document.getElementById("detquantity"+detId).value;
    var qt=document.getElementById("detqt"+detId).value;
    if (detquantity/1<0 || detquantity/1>qt/1) {
        document.getElementById("detquantity"+detId).value=document.getElementById("detqt"+detId).value;
        alert("SL nh\u1EADp v\u00E0o b\u1ECB sai!");
    }
    return false;
}
function printDn2(dnId){
    if (dnId==null) {
        dnId=document.forms['dn2Form'].dnId;
        if(dnId!=null) dnId=dnId.value;
    }
    if(dnId!=null) callServer('dn2Print.do?dnId='+dnId);
    dnId=null;
}

//ColorCode

function loadColorCodeList(params){
    callAjaxEx('colorCodeList.do','ajaxContent','searchColorCode.do','colorCodeList',params);
    return false;
}
function loadColorCodeListSort(params){
    callAjaxEx('searchColorCode.do','colorCodeList',null,null,params);
    return false;
}
function getColorCodeListData(data){
    setAjaxData(data,'ajaxContent');
    loadColorCodes();
    return false;
}
function loadColorCodes(){
    callAjax("colorCodes.do","colorCodeList",null,null);
    return false;
}
function addColorCode(ccId){
    var url="colorCodeForm.do";
    if(ccId!=null) url=url+"?ccId="+ccId;
    callAjax(url,"ajaxContent",null,null);
    return false;
}
function saveColorCode() {
    var colorCode = document.forms[0].colorCode;
    if(colorCode.value==''){
        alert("Vui l\u00F2ng nh\u1EADp M\u00E3 m\u00E0u!");
        colorCode.focus();
        colorCode=null;
        return false;
    }
    //callAjaxCheckError("addColorCode.do",null,document.forms[0],getColorCodeListData);
    callAjaxCheckError("addColorCode.do",null,document.forms[0],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        addColorCode(0)
    });

    return false;
}
function searchColorCode(){
    var checkflag = true;
    if (document.forms[0].searchid.value!=0) {
        if (document.forms[0].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms[0].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchColorCode.do","colorCodeList",document.forms[0],null);
    }
    return false;
}
function delColorCodes(){
    var checkflag = false;
    var ccId = document.forms['colorCodesForm'].ccId;
    if (ccId.length!=null) {
        for (i = 0; i < ccId.length; i++) {
            if (ccId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = ccId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delColorCode.do','colorCodeList',document.forms['colorCodesForm'],null);
        }
    }
    ccId=null;
    return false;
}
function searchAdvColorCodeForm(){
    callAjax('searchAdvColorCodeForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvColorCode(){
    callAjax('searchAdvColorCode.do',null,document.forms['searchColorCodeForm'],getSearchAdvColorCodeData);
    hidePopupForm();
    return false;
}
function getSearchAdvColorCodeData(data){
    setAjaxData(data,'colorCodeList');
}

//ReportDamage
function loadReportDamageList(params){
    //callAjax('reportdamageList.do',null,null,getReportDamageListData);
    callAjaxEx('reportdamageList.do','ajaxContent','searchReportDamage.do','reportdamageList',params);
    return false;
}
function loadReportDamageListSort(params){
    callAjaxEx('searchReportDamage.do','reportdamageList',null,null,params);
    return false;
}
function getReportDamageListData(data){
    setAjaxData(data,'ajaxContent');
    loadReportDamages();
    return false;
}
function loadReportDamages(){
    callAjax("reportdamages.do","reportdamageList",null,null);
    return false;
}
function addReportDamage(rdId){
    var url="reportdamageForm.do";
    if(rdId!=null) {
        url=url+"?rdId="+rdId;        
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(rdId!=null) {
            loadAttachFileSystem(22,rdId);
        }
    });
    return false;
}
function saveReportDamage() {
    var rdNumber = document.forms[0].rdNumber;
    if (rdNumber.value.length==0){
        alert("Nh\u1EADp v\u00E0o S\u1ED1 bi\u00EAn b\u1EA3n!");
        rdNumber.focus();
        return false;
    }
    //callAjaxCheckError("addReportDamage.do",null,document.forms[0],getReportDamageListData);
    callAjaxCheckError("addReportDamage.do",null,document.forms[0],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        addReportDamage(0)
    });

    return false;
}
function searchReportDamage(){
    var checkflag = true;
    if (document.forms[0].searchid.value!=0) {
        if (document.forms[0].searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else{
        document.forms[0].searchvalue.value="";
    }
    if (checkflag == true) {
        callAjax("searchReportDamage.do","reportdamageList",document.forms[0],null);
    }
    return false;
}
function delReportDamages(){
    var checkflag = false;
    var rdId = document.forms['reportdamagesForm'].rdId;
    if (rdId.length!=null) {
        for (i = 0; i < rdId.length; i++) {
            if (rdId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = rdId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delReportDamage.do','reportdamageList',document.forms['reportdamagesForm'],null);
        }
    }
    rdId=null;
    return false;
}
function printReportDamage(){
    var rdId=document.forms['addReportDamage'].rdId;
    if(rdId!=null) callServer('reportDamagePrint.do?rdId='+rdId.value);
    rdId=null;
    return false;
}
//time using
function loadTimeUsingList(params){
    callAjaxEx('timeUsingPanel.do','ajaxContent','searchTimeUsing.do','timeUsingList',params);
    return false;
}
function loadTimeUsingListSort(params){
    callAjaxEx('searchTimeUsing.do','timeUsingList',null,null,params);
    return false;
}
function timeUsingForm(updateDate){
    var url="timeUsingForm.do";
    if(updateDate!=null) url=url+"?updateDate="+updateDate;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
    });
    url=null;
    return false;
}

function saveTimeUsing() {
    //callAjaxCheckError("saveTimeUsing.do",null,document.forms['timeUsingForm'],getTimeUsingListData,'tuErrorMessageDiv');
    callAjaxCheckError("saveTimeUsing.do",null,document.forms['timeUsingForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        timeUsingForm(0)
    });
    return false;
}

function getTimeUsingListData(data){
    loadTimeUsingList();
    return false;
}

function calcTimeRemain(timeUsed,id) {
    var totalTimeUsed = document.getElementById("total_time_used_" + id);
    var totalTimeRepair = document.getElementById("total_time_repair_" + id);
    var timeRemain = document.getElementById("time_remain_" + id);
    if (totalTimeUsed==null ||
        totalTimeRepair==null ||
        timeRemain==null)
        return false;
    timeRemain.value = parseInt(totalTimeRepair.value) - parseInt(totalTimeUsed.value) - timeUsed;
}
//handed report
function loadHandedReportList(params){
    callAjaxEx('handedReportPanel.do','ajaxContent','searchHandedReport.do','handedReportList',params);
    return false;
}
function loadHandedReportListSort(params){
    callAjaxEx('searchHandedReport.do','handedReportList',null,null,params);
    return false;
}
function handedReportForm(hrId){
    var url="handedReportForm.do";
    if(hrId!=null) url=url+"?hrId="+hrId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        url='listHandedReportDetail.do';
        if(hrId!=null) url=url+"?hrId="+hrId;
        callAjax(url,'listHandedReportMaterialDataDiv',null,null);
        if(hrId!=null) {
            loadAttachFileSystem(13,hrId);
        }
    });
    return false;
}

function setSelectedHandedReportMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['handedReportForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("listHandedReportMaterial.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'handedReportMaterialHideDiv');
        var matTable=document.getElementById('handedReportMaterialTable');
        var detTable=document.getElementById('handedReportDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}

function hrOrgReceiveChanged(cbx, idx) {
    if(idx < 0)
        return false;
    var orgId = cbx.options.item(idx).value;
    callAjax('getRequireTransferListOfOrg.do?orgId='+orgId,'rtNumberDiv',null,null);
    callAjax('getEmployeeListOfOrg.do?orgId='+orgId,'empReceiveDiv',null,null);

}

function delHandedReportMaterial(){
    var checkflag = false;
    var detId = document.forms['handedReportForm'].chkDetId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true)
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delHandedReportDetail.do',null,document.forms['handedReportForm'],function(data){
                setAjaxData(data,'listHandedReportMaterialDataDiv');
            });
    detId=null;
    return false;
}

function saveHandedReport() {
    var hrNumber = document.forms['handedReportForm'].hrNumber;
    if(hrNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        hrNumber.focus();
        hrNumber=null;
        return false;
    }
    hrNumber=null;
    //callAjaxCheckError("saveHandedReport.do",null,document.forms['handedReportForm'],getHandedReportListData,'hrErrorMessageDiv');
    callAjaxCheckError("saveHandedReport.do",null,document.forms['handedReportForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        handedReportForm(0)
    });

    return false;
}

function getHandedReportListData(data){
    loadHandedReportList();
    return false;
}

function delHandedReports(){
    var checkflag = false;
    var hrId = document.forms['handedReportListForm'].hrId;
    //if(hrId==null) return false;
    if (hrId.length!=null) {
        for (i = 0; i < hrId.length; i++) {
            if (hrId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = hrId.checked;
    if (checkflag == true)
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delHandedReport.do','handedReportList',document.forms['handedReportListForm'],null);
    hrId=null;
    return false;
}

function searchHandedReport(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else form.searchvalue.value="";
    if (checkflag == true)
        callAjax("searchHandedReport.do","handedReportList",form,null);
    form=null;
    return false;
}

function printHandedReport(){
    var hrId=document.forms['handedReportForm'].hrId;
    if(hrId!=null) callServer('handedReportPrint.do?hrId='+hrId.value);
    hrId=null;
    return false;
}

function selectEquipmentOfRtId(handle){
    if(handle==null) return false;
    callAjax('chooseEquipmentOfHrForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEquipmentHrDefault();
    });
    return false;
}

function searchEquipmentHrDefault(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentOfHr.do?rtId="+document.forms['handedReportForm'].rtId.value;
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}

function searchEquipmentHr(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentOfHr.do?rtId="+document.forms['handedReportForm'].rtId.value;
    if(params!=null) url+="&"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}

// require transfer
function loadRequireTransferList(params){
    callAjaxEx('requireTransferPanel.do','ajaxContent','searchRequireTransfer.do','requireTransferList',params);
    return false;
}
function loadRequireTransferListSort(params){
    callAjaxEx('searchRequireTransfer.do','requireTransferList',null,null,params);
    return false;
}
function requireTransferForm(rtId){
    var url="requireTransferForm.do";
    if(rtId!=null) url=url+"?rtId="+rtId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        url='listRequireTransferDetail.do';
        if(rtId!=null) url=url+"?rtId="+rtId;
        callAjax(url,'listRequireTransferMaterialDataDiv',null,null);
        if(rtId!=null) {
            loadAttachFileSystem(12,rtId);
        }
    });
    return false;
}

function setSelectedRequireTransferMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['requireTransferForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("listRequireTransferMaterial.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'requireTransferMaterialHideDiv');
        var matTable=document.getElementById('requireTransferMaterialTable');
        var detTable=document.getElementById('requireTransferDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}

function delRequireTransferMaterial(){
    var checkflag = false;
    var detId = document.forms['requireTransferForm'].chkDetId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true)
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delRequireTransferDetail.do',null,document.forms['requireTransferForm'],function(data){
                setAjaxData(data,'listRequireTransferMaterialDataDiv');
            });
    detId=null;
    return false;
}

function saveRequireTransfer() {
    var rtNumber = document.forms['requireTransferForm'].rtNumber;
    if(rtNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        rtNumber.focus();
        rtNumber=null;
        return false;
    }
    rtNumber=null;
    //callAjaxCheckError("saveRequireTransfer.do",null,document.forms['requireTransferForm'],getRequireTransferListData,'rtErrorMessageDiv');
    callAjaxCheckError("saveRequireTransfer.do",null,document.forms['requireTransferForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        requireTransferForm(0)
    });

    return false;
}

function getRequireTransferListData(data){
    loadRequireTransferList();
    return false;
}

function delRequireTransfers(){
    var checkflag = false;
    var rtId = document.forms['requireTransferListForm'].rtId;
    if(rtId==null) return false;
    if (rtId.length!=null) {
        for (i = 0; i < rtId.length; i++) {
            if (rtId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = rtId.checked;
    if (checkflag == true)
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delRequireTransfer.do','requireTransferList',document.forms['requireTransferListForm'],null);
    rtId=null;
    return false;
}

function searchRequireTransfer(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else form.searchvalue.value="";
    if (checkflag == true)
        callAjax("searchRequireTransfer.do","requireTransferList",form,null);
    form=null;
    return false;
}
function selectEquipmentRequireTransfer(handle){
    if(handle==null) return false;
    callAjax('chooseEquipmentRequireTransfer.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEquipmentRequireTransfer();
    });
    return false;
}
function searchEquipmentRequireTransfer(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentRequireTransfer.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function chooseEquipmentRequireTransferSelected(){
    var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}
// require verified
function loadRequireVerifiedList(params){
    callAjaxEx('requireVerifiedPanel.do','ajaxContent','searchRequireVerified.do','requireVerifiedList',params);
    return false;
}
function loadRequireVerifiedListSort(params){
    callAjaxEx('searchRequireVerified.do','requireVerifiedList',null,null,params);
    return false;
}
function requireVerifiedForm(rvId){
    var url="requireVerifiedForm.do";
    if(rvId!=null) url=url+"?rvId="+rvId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        url='listRequireVerifiedDetail.do';
        if(rvId!=null) url=url+"?rvId="+rvId;
        callAjax(url,'listRequireVerifiedMaterialDataDiv',null,null);
        if(rvId!=null) {
            loadAttachFileSystem(11,rvId);
        }
    });
    return false;
}
function setSelectedRequireVerifiedMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['requireVerifiedForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("listRequireVerifiedMaterial.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'requireVerifiedMaterialHideDiv');
        var matTable=document.getElementById('requireVerifiedMaterialTable');
        var detTable=document.getElementById('requireVerifiedDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}

function delRequireVerifiedMaterial(){
    var checkflag = false;
    var detId = document.forms['requireVerifiedForm'].chkDetId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true)
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delRequireVerifiedDetail.do',null,document.forms['requireVerifiedForm'],function(data){
                setAjaxData(data,'listRequireVerifiedMaterialDataDiv');
            });
    detId=null;
    return false;
}

function saveRequireVerified() {
    var rvNumber = document.forms['requireVerifiedForm'].rvNumber;
    if(rvNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        rvNumber.focus();
        rvNumber=null;
        return false;
    }
    rvNumber=null;
    //callAjaxCheckError("saveRequireVerified.do",null,document.forms['requireVerifiedForm'],getRequireVerifiedListData,'rvErrorMessageDiv');
    callAjaxCheckError("saveRequireVerified.do",null,document.forms['requireVerifiedForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        requireVerifiedForm(0)
    });

    return false;
}

function getRequireVerifiedListData(data){
    loadRequireVerifiedList();
    return false;
}

function delRequireVerifieds(){
    var checkflag = false;
    var rvId = document.forms['requireVerifiedListForm'].rvId;
    if(rvId==null) return false;
    if (rvId.length!=null) {
        for (i = 0; i < rvId.length; i++) {
            if (rvId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = rvId.checked;
    if (checkflag == true)
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delRequireVerified.do','requireVerifiedList',document.forms['requireVerifiedListForm'],null);
    rvId=null;
    return false;
}

function searchRequireVerified(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else form.searchvalue.value="";
    if (checkflag == true)
        callAjax("searchRequireVerified.do","requireVerifiedList",form,null);
    form=null;
    return false;
}

function selectEquipmentOfOrg(handle){
    if(handle==null) return false;
    callAjax('chooseEquipmentOfOrgForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEquipmentDefault();
    });
    return false;
}

function searchEquipmentDefault(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentOfOrg.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}

//REQUIRE REPAIR
function loadRequireRepairList(params){
    callAjaxEx('requirerepairList.do','ajaxContent','searchRequireRepair.do','requirerepairList',params);
    return false;
}
function loadRequireRepairListSort(params){
    callAjaxEx('searchRequireRepair.do','requirerepairList',null,null,params);
    return false;
}
function getRequireRepairListData(data){
    setAjaxData(data,'ajaxContent');
    loadRequireRepairs();
    return false;
}
function loadRequireRepairs(){
    callAjax("requirerepairs.do","requirerepairList",null,null);
    return false;
}
function requirerepairForm(rrId){
    var url="requirerepairForm.do";
    if(rrId!=null) url=url+"?rrId="+rrId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        url='listRequireRepairDetail.do';
        if(rrId!=null) url=url+"?rrId="+rrId;
        callAjax(url,'listRequireRepairMaterialDataDiv',null,null);
        if(rrId!=null) {
            loadAttachFileSystem(23,rrId);
        }
    });
    return false;
}
function selectEquipment(handle){
    if(handle==null) return false;
    callAjax('chooseEquipmentForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEquipments();
    });
    return false;
}
function searchEquipments(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentRequest.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function selectEquipmentRequireRepair(handle){
    if(handle==null) return false;
    callAjax('chooseEquipmentForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEquipmentRequireRepair();
    });
    return false;
}
function searchEquipmentRequireRepair(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentRequireRepair.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function delEquipmentRequest(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var equId = document.forms['materialRequestForm'].equId;
    if(equId==null) return false;
    if (equId.length!=null){
        for (i = 0; i < equId.length; i++) {
            if (ids.indexOf(','+equId[i].value+',')>-1){
                equId[i].disabled=false;
                equId[i].checked=false;
            }
        }
        equId=null;
    }else if (ids.indexOf(','+equId.value+',')>-1){
        equId.disabled=false;
        equId.checked=false;
    }
    equId=null;
}
function setEquipmentSelected(){
    var equId = document.forms['materialRequestForm'].equId;
    if(equId==null) return false;
    var matNameHidden = document.forms['materialRequestForm'].matNameHidden;
    var matCodeHidden = document.forms['materialRequestForm'].matCodeHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (equId.length!=null) {
        for (i = 0; i < equId.length; i++) {
            if (equId[i].checked == true && equId[i].disabled==false) {
                equId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=equId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (equId.checked == true && equId.disabled==false) {
            equId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=equId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    equId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}
function chooseEquipmentSelected(){
    var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}
function setSelectedRequireRepairEquipment(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['requirerepairForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("listRequireRepairEquipment.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'equipmentHideDiv');
        var matTable=document.getElementById('equipmentTable');
        var detTable=document.getElementById('equipmentDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function saveRequireRepair() {
    var rrNumber = document.forms['requirerepairForm'].rrNumber;
    if(rrNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        rrNumber.focus();
        rrNumber=null;
        return false;
    }    
    //callAjaxCheckError("saveRequireRepair.do",null,document.forms['requirerepairForm'],getRequireRepairListData);
    callAjaxCheckError("saveRequireRepair.do",null,document.forms['requirerepairForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        requirerepairForm(0)
    });
    rrNumber=null;
    return false;
}
function delRequireRepairs(){
    var checkflag = false;
    var rrId = document.forms['requirerepairsForm'].rrId;
    if(rrId==null) return false;
    if (rrId.length!=null) {
        for (i = 0; i < rrId.length; i++) {
            if (rrId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = rrId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRequireRepair.do','requirerepairList',document.forms['requirerepairsForm'],null);
    rrId=null;
    return false;
}
function delRequireRepairMaterial(){
    var checkflag = false;
    var detId = document.forms['requirerepairForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRequireRepairDetail.do',null,document.forms['requirerepairForm'],function(data){
            {
                var tbl=document.getElementById('equipmentDetailTable');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
                }
            detId=null;
        });
    }else detId=null;
    return false;
}
function searchRequireRepair(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRequireRepair.do","requirerepairList",form,null);
    form=null;
    return false;
}

//AcceptanceTest
function loadAcceptanceTestList(params){
    callAjaxEx('acceptancetestList.do','ajaxContent','searchAcceptanceTest.do','acceptancetestList',params);
    return false;
}
function loadAcceptanceTestListSort(params){
    callAjaxEx('searchAcceptanceTest.do','acceptancetestList',null,null,params);
    return false;
}
function getAcceptanceTestListData(data){
    setAjaxData(data,'ajaxContent');
    loadAcceptanceTests();
    return false;
}
function loadAcceptanceTests(){
    callAjax("acceptancetests.do","acceptancetestList",null,null);
    return false;
}
function acceptancetestForm(atId){
    var url="acceptancetestForm.do";
    if(atId!=null) url=url+"?atId="+atId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        url='listAcceptanceTestDetail.do';
        if(atId!=null) url=url+"?atId="+atId;
        callAjax(url,'listAcceptanceTestMaterialDataDiv',null,null);
        if(atId!=null) {
            loadAttachFileSystem(26,atId);
        }
    });
    return false;
}
function searchEquipmentAcceptanceTest(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentRequest.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function selectEquipmentOfSrId(handle){
    if(handle==null) return false;
    callAjax('chooseEquipmentOfHrForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEquipmentSrDefault();
    });
    return false;
}

function searchEquipmentSrDefault(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentOfSr.do?srId="+document.forms['requirerepairForm'].srId.value;
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function searchEquipmentSr(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentOfSr.do?srId="+document.forms['requirerepairForm'].srId.value;
    if(params!=null) url+="&"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function setSelectedAcceptanceTestMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['acceptancetestForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("listAcceptanceTestMaterial.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'acceptancetestMaterialHideDiv');
        var matTable=document.getElementById('acceptancetestMaterialTable');
        var detTable=document.getElementById('acceptancetestDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function saveAcceptanceTest() {
    var atNumber = document.forms[0].atNumber;
    if(atNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        atNumber.focus();
        atNumber=null;
        return false;
    }
    atNumber=null;
    //callAjaxCheckError("saveAcceptanceTest.do",null,document.forms[0],getAcceptanceTestListData);
    callAjaxCheckError("saveAcceptanceTest.do",null,document.forms[0],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        acceptancetestForm(0);
    });
    return false;
}
function delAcceptanceTests(){
    var checkflag = false;
    var atId = document.forms['acceptancetestsForm'].atId;
    if(atId==null) return false;
    if (atId.length!=null) {
        for (i = 0; i < atId.length; i++) {
            if (atId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = atId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delAcceptanceTest.do','acceptancetestList',document.forms['acceptancetestsForm'],null);
    atId=null;
    return false;
}
function delAcceptanceTestMaterial(){
    var checkflag = false;
    var detId = document.forms[0].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delAcceptanceTestDetail.do',null,document.forms[0],function(data){
        setAjaxData(data,'listAcceptanceTestMaterialDataDiv');
    });
    detId=null;
    return false;
}
function searchAcceptanceTest(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchAcceptanceTest.do","acceptancetestList",form,null);
    form=null;
    return false;
}

//SurveyReport
function loadSurveyReportList(params){
    callAjaxEx('surveyreportList.do','ajaxContent','searchSurveyReport.do','surveyreportList',params);
    return false;
}
function loadSurveyReportListSort(params){
    callAjaxEx('searchSurveyReport.do','surveyreportList',null,null,params);
    return false;
}
function getSurveyReportListData(data){
    setAjaxData(data,'ajaxContent');
    loadSurveyReports();
    return false;
}
function loadSurveyReports(){
    callAjax("surveyreports.do","surveyreportList",null,null);
    return false;
}
function surveyreportForm(srId){
    var url="surveyreportForm.do";
    if(srId!=null) url=url+"?srId="+srId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        //Materials
        url='listSurveyReportDetail.do';
        if(srId!=null) url=url+"?srId="+srId;
        callAjax(url,'listSurveyReportMaterialDataDiv',null,null);
        //Equipment
        url='listSurveyReportDetails.do';
        if(srId!=null) url=url+"?srId="+srId;
        callAjax(url,'listSurveyReportEquipmentDataDiv',null,null);
        //
        if(srId!=null) {
            loadAttachFileSystem(24,srId);
        }
    });
    return false;
}
function searchEquipmentSurveyReport(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchEquipmentRequest.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var equId = document.forms['materialRequestForm'].equId;
        if(equId==null) return false;
        var selId=document.forms['equipmentRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (equId.length!=null){
            for (i = 0; i < equId.length; i++) {
                if (ids.indexOf(','+equId[i].value+',')>-1){
                    equId[i].disabled=true;
                    equId[i].checked=true;
                }
            }
            equId=null;
        }else if (ids.indexOf(','+equId.value+',')>-1){
            equId.disabled=true;
            equId.checked=true;
        }
        equId=null;
    });
    form=null;
    return false;
}
function setSelectedSurveyReportMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['surveyreportForm'].matId;
    if(matIds!=null){
        ids='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value) ids+=","+idLst[i];
            }
        }
        matIds=null;
        if(ids=='0') return;
    }
    callAjax("listSurveyReportMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'materialHideDiv');
        var matTable=document.getElementById('materialTable');
        var detTable=document.getElementById('materialDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function setSelectedSurveyReportEquipment(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var equIds=document.forms['surveyreportForm'].equId;
    if(equIds!=null){
        ids='0';
        if(equIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<equIds.length;j++){
                    if(idLst[i]==equIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=equIds.value) ids+=","+idLst[i];
            }
        }
        equIds=null;
        if(ids=='0') return;
    }
    callAjax("listSurveyReportEquipment.do?equIds="+ids,null,null,function(data){
        setAjaxData(data,'equipmentHideDiv');
        var matTable=document.getElementById('equipmentTable');
        var detTable=document.getElementById('equipmentDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;
    });
}
function saveSurveyReport() {
    var srNumber = document.forms[0].srNumber;
    if(srNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        srNumber.focus();
        srNumber=null;
        return false;
    }
    srNumber=null;
    //callAjaxCheckError("saveSurveyReport.do",null,document.forms[0],getSurveyReportListData);
    callAjaxCheckError("saveSurveyReport.do",null,document.forms[0],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        surveyreportForm(0)
    });
    return false;
}
function delSurveyReports(){
    var checkflag = false;
    var srId = document.forms['surveyreportsForm'].srId;
    if(srId==null) return false;
    if (srId.length!=null) {
        for (i = 0; i < srId.length; i++) {
            if (srId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = srId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delSurveyReport.do','surveyreportList',document.forms['surveyreportsForm'],null);
    srId=null;
    return false;
}
function delSurveyReportMaterial(){
    var checkflag = false;
    var detId = document.forms[0].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delSurveyReportMaterial.do',null,document.forms[0],function(data){
        setAjaxData(data,'listSurveyReportMaterialDataDiv');
    });
    detId=null;
    return false;
}
function delSurveyReportEquipment(){
    var checkflag = false;
    var detId = document.forms[0].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delSurveyReportEquipment.do',null,document.forms[0],function(data){
        setAjaxData(data,'listSurveyReportEquipmentDataDiv');
    });
    detId=null;
    return false;
}
function searchSurveyReport(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchSurveyReport.do","surveyreportList",form,null);
    form=null;
    return false;
}
function selectMaterialSr(handle){
    if(handle==null) return false;
    callAjax('chooseMaterialSrForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchMaterialSr();
    });
    return false;
}
function searchMaterialSr(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchMaterialSr.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRequestList');
        var matId = document.forms['materialRequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialRequestSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    matId[i].disabled=true;
                    matId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            matId.disabled=true;
            matId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}
function setMaterialSrSelected(){
    var matId = document.forms['materialRequestForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialRequestForm'].matNameHidden;
    var matCodeHidden = document.forms['materialRequestForm'].matCodeHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true && matId[i].disabled==false) {
                matId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=matId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (matId.checked == true && matId.disabled==false) {
            matId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=matId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    matId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}
function delMaterialSr(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var matId = document.forms['materialRequestForm'].matId;
    if(matId==null) return false;
    if (matId.length!=null){
        for (i = 0; i < matId.length; i++) {
            if (ids.indexOf(','+matId[i].value+',')>-1){
                matId[i].disabled=false;
                matId[i].checked=false;
            }
        }
        matId=null;
    }else if (ids.indexOf(','+matId.value+',')>-1){
        matId.disabled=false;
        matId.checked=false;
    }
    matId=null;
}
function chooseMaterialSrSelected(){
    var selId=document.forms['materialRequestSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    var handle=document.getElementById('callbackFunc').value;
    if(handle!='') eval(handle+"('"+ids+"')");
    hidePopupForm();
}

//RequireSettling
function loadRequireSettlingList(params){
    callAjaxEx('requiresettlingList.do','ajaxContent','searchRequireSettling.do','requiresettlingList',params);
    return false;
}
function loadRequireSettlingListSort(params){
    callAjaxEx('searchRequireSettling.do','requiresettlingList',null,null,params);
    return false;
}
function getRequireSettlingListData(data){
    setAjaxData(data,'ajaxContent');
    loadRequireSettlings();
    return false;
}
function loadRequireSettlings(){
    callAjax("requiresettlings.do","requiresettlingList",null,null);
    return false;
}
function requiresettlingForm(rsId){
    var url="requiresettlingForm.do";
    if(rsId!=null) {
        url=url+"?rsId="+rsId;        
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        setRequireSettlingDetail(rsId);
        if(rsId!=null) {
            loadAttachFileSystem(25,rsId);
        }
    });
    return false;
}
function saveRequireSettling() {
    var rsNumber = document.forms['requiresettlingForm'].rsNumber;
    if(rsNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 Phieu!");
        rsNumber.focus();
        rsNumber=null;
        return false;
    }
    rsNumber=null;
    //callAjaxCheckError("saveRequireSettling.do",null,document.forms['requiresettlingForm'],getRequireSettlingListData);
    callAjaxCheckError("saveRequireSettling.do",null,document.forms['requiresettlingForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        requiresettlingForm(0)
    });
    return false;
}
function delRequireSettlings(){
    var checkflag = false;
    var rsId = document.forms['requiresettlingsForm'].rsId;
    if(rsId==null) return false;
    if (rsId.length!=null) {
        for (i = 0; i < rsId.length; i++) {
            if (rsId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = rsId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRequireSettling.do','requiresettlingList',document.forms['requiresettlingsForm'],null);
    rsId=null;
    return false;
}
function searchRequireSettling(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRequireSettling.do","requiresettlingList",form,null);
    form=null;
    return false;
}
function setRequireSettlingDetail(rsId) {
    var url="getRequireSettlingDetail.do";
    if(rsId!=null) url+="?rsId="+rsId;
    callAjax(url,'requireSettlingDetailList',null,null);
    return false;
}
function addRequireSettlingDetail(){
    var tbl=document.getElementById('requireSettlingTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    var random=Math.random();
    var currentTime = new Date()
    var month = currentTime.getMonth() + 1
    var day = currentTime.getDate()
    var year = currentTime.getFullYear()
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'rsChk';
    el.value='';
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'delRsId';
    el.value='0';
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'reqDetId';
    el.value='0';
    cell.appendChild(el);

    cell = row.insertCell(1);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'workPlan';
    el.size=12;
    cell.appendChild(el);

    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'contentWork';
    el.size=25;
    cell.appendChild(el);

    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'location';
    el.size=15;
    cell.appendChild(el);

    cell = row.insertCell(4);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'quantity';
    el.value='0';
    el.size=10;
    cell.appendChild(el);

    cell = row.insertCell(5);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'startTimePlan';
    el.value=day + "/" + month + "/" + year;
    el.size=10;
    cell.appendChild(el);

    cell = row.insertCell(6);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'endTimePlan';
    el.value=day + "/" + month + "/" + year;
    el.size=10;
    cell.appendChild(el);

    cell = row.insertCell(7);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'startTimeReality';
    el.value=day + "/" + month + "/" + year;
    el.size=10;
    cell.appendChild(el);

    cell = row.insertCell(8);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'endTimeReality';
    el.value=day + "/" + month + "/" + year;
    el.size=10;
    cell.appendChild(el);

    cell = row.insertCell(9);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'explanation';
    el.size=25;
    cell.appendChild(el);

    cell = row.insertCell(10);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'comment';
    el.size=30;
    cell.appendChild(el);

    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delRequireSettlingDetail(){
    var checkflag = false;
    var detId = document.forms['requiresettlingForm'].rsChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true){
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRequireSettlingDetail.do',null,document.forms['requiresettlingForm'],function(data){
        {
                var tbl=document.getElementById('requireSettlingTbl');
                var len=detId.length;
                if(len!=null){
                    for (i=len-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            tbl.deleteRow(i+1);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                tbl=null;
                }
            detId=null;
        });
    }else detId=null;
    return false;
}

//Tu
function loadMivList(kind,params){
    if(kind==null){
        kind=document.getElementById('mivKind');        
        if(kind==null) kind=0;
        else kind=kind.value;
    }
    var ss = "" + kind
    if(kind.length>10)
    {
        params = ss.substring(1, ss.length - 6);
        kind = ss.substring(ss.length -1 , ss.length);
    }
    callAjax("mivList.do?kind="+kind,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        callAjaxEx('mivs.do?kind='+kind,'mivList',null,null,params);
    });
    return false;
}
function loadMivListSort(kind,params){
    if(kind==null){
        kind=document.getElementById('mivKind');        
        if(kind==null) kind=0;
        else kind=kind.value;
        var ss = "" + kind
        if(kind.length>10)
        {
            params = ss.substring(1, ss.length - 6);
            kind = ss.substring(ss.length -1 , ss.length);
        }
        callAjaxEx('searchMiv.do?kind='+kind,'mivList',null,null,params);
    }else{
        callAjaxEx('searchMiv.do?'+kind,'mivList',null,null,params);
    }
    return false;
}
function getMivListData(data){
    setAjaxData(data,'ajaxContent');
    loadMivs();
    return false;
}
function loadMivs(){
    var kind=document.getElementById('mivKind');
    if(kind==null) kind=0
    else kind=kind.value;
    callAjax("mivs.do?kind="+kind,"mivList",null,null);
    return false;
}
function mivForm(mivId,_kind){
    var url="mivForm.do";
    if (_kind==null) {
        kind=document.getElementById('mivKind');
        if(kind==null) kind=0;
        else kind=kind.value;
    } else {
        kind = _kind;
    }
    if(mivId!=null) url=url+"?mivId="+mivId+'&kind='+kind;
    else url=url+"?kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(mivId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateMiv').value=date+'/'+month+'/'+currentTime.getFullYear();
        }else{
            var rfmId = document.forms['mivForm'].rfmId;
            var url='getMaterialInRfm.do?rfmId='+rfmId.value+'&kind='+kind;
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listMivMaterialDataSpan');
                var price=document.forms['mivForm'].price;
                var quantity=document.forms['mivForm'].quantity;
                var detTotal=document.forms['mivForm'].detTotal;
                if(price!=null){
                    if (price.length!=null) {
                        for (i = 0; i < price.length; i++) {
                            tryNumberFormat(price[i]);
                            tryNumberFormat(quantity[i]);
                            tryNumberFormat(detTotal[i]);
                        }
                    } else{
                        tryNumberFormat(price);
                        tryNumberFormat(quantity);
                        tryNumberFormat(detTotal);
                    }
                }
                price=null;
                quantity=null;
                detTotal=null;
                var total=document.forms['mivForm'].total;
                if(total!=null) tryNumberFormat(total);
                total=null;
            });
            rfmId=null;
        }
    });
    return false;
}
function mivProChanged(list){
    if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    callAjax("getMivNumber.do?stoId="+list.options[list.selectedIndex].value,"mivNumberTd",null,null);
    return false;
}
function saveMiv() {
    if(scriptFunction=="saveMiv()") return false;
    var mivNumber = document.forms['mivForm'].mivNumber;
    var mivId = document.forms['mivForm'].mivId.value;
    if(mivNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 phi\u1EBFu xu\u1EA5t kho");
        mivNumber.focus();
        mivNumber=null;
        return false;
    }
    mivNumber=null;

    var createdDate = document.forms['mivForm'].createdDate;
    if(createdDate.value==''){
        alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y l\u1EADp');
        createdDate.focus();
        createdDate=null;
        return false;
    }
    createdDate=null;

    var rfmId = document.forms['mivForm'].rfmId;
    if(rfmId.selectedIndex==-1){
        alert("Vui l\u00F2ng ch\u1ECDn y\u00EAu c\u1EA7u xu\u1EA5t kho");
        rfmId=null;
        return false;
    }
    rfmId=null;

    var price=document.forms['mivForm'].price;
    var quantity=document.forms['mivForm'].quantity;
    var detTotal=document.forms['mivForm'].detTotal;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < price.length; i++) {
                reformatNumberMoney(price[i]);
                reformatNumberMoney(quantity[i]);
                reformatNumberMoney(detTotal[i]);
            }
        } else{
            reformatNumberMoney(price);
            reformatNumberMoney(quantity);
            reformatNumberMoney(detTotal);
        }
    }
    price=null;
    quantity=null;
    detTotal=null;
    var total=document.forms['mivForm'].total;
    if(total!=null) reformatNumberMoney(total);
    total=null;
    scriptFunction="saveMiv()";
    callAjaxCheckError("saveMiv.do",null,document.forms['mivForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        //loadMivList(document.forms['mivForm'].mivKind.value);
        mivForm(mivId);
    });
    return false;
}
function setSelectedMivMaterial(){
    var list=document.forms['mivForm'].material;
    if(list.selectedIndex==-1) return false;
    var materialId=list.options[list.selectedIndex].value;
    var matId = document.forms['mivForm'].matId;
    var existed=false;
    if(matId!=null){
        if (matId.length!=null) {
            for (i = 0; i < matId.length; i++) {
                if (matId[i].value == materialId) {
                    existed = true;
                    break;
                }
            }
        } else if(matId.value==materialId) existed=true;
    }
    if(existed==true){
        alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
        return false;
    }
    var rfmId=document.forms['mivForm'].rfmId;
    if(rfmId.selectedIndex==null) rfmId=rfmId.value
    else rfmId=rfmId.options[rfmId.selectedIndex].value;
    mivGetMaterials(rfmId,materialId);
    matId=null;
    return false;
}
function mivGetMaterials(rfmId,materialIds){
    var url='getMaterialInRfm.do?rfmId='+rfmId;
    if(materialIds!=null) url+='&materialIds='+materialIds;
    else  url+='&loadMaterials=true';
    var kind=document.forms['mivForm'].mivKind;
    if(kind!=null) kind=kind.value;
    url+='&kind='+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'mivMaterialHideDiv');
        var matTable=document.getElementById('mivMaterialTable');
        var detTable=document.getElementById('mivDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;

        var price=document.forms['mivForm'].price;
        var quantity=document.forms['mivForm'].quantity;
        var detTotal=document.forms['mivForm'].detTotal;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < price.length; i++) {
                    detTotal[i].value=price[i].value*quantity[i].value;
                    tryNumberFormat(price[i]);
                    tryNumberFormat(quantity[i]);
                    tryNumberFormat(detTotal[i]);
                }
            } else{
                detTotal.value=price.value*quantity.value;
                tryNumberFormat(price);
                tryNumberFormat(quantity);
                tryNumberFormat(detTotal);
            }
        }
        price=null;
        quantity=null;
        detTotal=null;
        caculateMiv();
    });
}
function caculateMiv(){
    var total=document.forms['mivForm'].total;
    if(total==null) return;
    var detTotal=document.forms['mivForm'].detTotal;
    var sum=0;
    if(detTotal!=null){
//        var price=document.forms['mivForm'].price;
//        var quantity=document.forms['mivForm'].quantity;
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
            }
        } else sum+=reformatNumberMoneyString(detTotal.value)*1;
    }

    total.value=sum;
    if(total!=null) tryNumberFormat(total);
    total=null;
    detTotal=null;
    return false;
}
function caculateMivDetail(matId){
    var quantity=document.getElementById("detquantity"+matId);
    var price=document.getElementById("detprice"+matId);
    var detTotal=document.getElementById("detTotal"+matId);
    if(quantity==null || price==null || detTotal==null) return;
    detTotal.value=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*1;
    reformatNumberMoneyString(detTotal);
    quantity=null;
    price=null;
    detTotal=null;
    caculateMiv();
    return false;
}

function whichUseChangedMiv(list,rfmId){
    if(list.selectedIndex==-1) return false;
    var orgId=null;
    var proId=null;
    var kind=null;
        orgId=document.forms['mivForm'].orgId;
        proId=document.forms['mivForm'].proId;
        kind=document.forms['mivForm'].mivKind;    
    orgId = orgId.options[orgId.selectedIndex].value;
    proId = proId.options[proId.selectedIndex].value;
    getWhichUseMiv(orgId,proId,kind.value,list.options[list.selectedIndex].value,rfmId);
    list=null;
    orgId=null;
    proId=null;
    kind=null;
    return false;
}
function getWhichUseMiv(orgId,proId,kind,whichUse,rfmId){
    var url="whichUseMivList.do?whichUse="+whichUse;
    if(orgId!=null) url+="&orgId="+orgId;
    if(proId!=null) url+="&proId="+proId;
    if(kind!=null) url+="&kind="+kind;
    if(rfmId!=null) url+="&rfmId="+rfmId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'whichUseSpan');
        mivRfmChanged(document.forms['mivForm'].rfmId);
    });
}
function mivRfmChanged(list){
    if(list.selectedIndex==-1) return false;
    removeMivMaterial();
    var rfmId=document.forms['mivForm'].rfmId;
    if(rfmId.selectedIndex==null) rfmId=rfmId.value
    else rfmId=rfmId.options[rfmId.selectedIndex].value;
    var kind=document.forms['mivForm'].mivKind;
    if(kind==null) kind=0;
    else kind=kind.value;
    var url='getRfmInfo.do?rfmId='+rfmId+'&kind='+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'trRfmInfo');
        var orgId=document.forms['mivForm'].orgId.value;
        if(orgId==0){
            orgId=document.forms['mivForm'].proId.value;
        }
        callAjax("getMivEmpList.do?kind=miv&orgId=" + orgId,'mivReceiverTd',null,null)
    });
    url='getMaterialInRfm.do?rfmId='+rfmId+'&kind='+kind;
    callAjax(url,'listMivMaterialDataSpan',null,null);
    list=document.forms['mivForm'].material;
    if(list==null) return false;
    for(i=list.options.length-1;i>=0;i--){
        list.remove(i);
    }
    list=null;
    mivGetMaterials(rfmId);
    return false;
}
function removeMivMaterial(){
    var selId=document.forms['mivForm'].matId;
    if(selId==null) return false;
    var tbl=document.getElementById('mivDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=1;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        if(parentNode!=null){
            parentNode.removeChild(selId);
        }
    }
    selId=null;
    if(tbl.rows.length!=null){
        var lastRow = tbl.rows.length;
        for (i=lastRow-1;i>=1;i--) {
            tbl.deleteRow(i);
        }
    }else{
        tbl.deleteRow(1);
    }
    parentNode=null;
    tbl=null;
}
function delMivDetails(){
    var checkflag = false;
    var detId = document.forms['mivForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true){
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delMivDetail.do',null,document.forms['mivForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('mivDetailTable');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
                caculateMiv();
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delMivs(){
    var checkflag = false;
    var mivId = document.forms['mivsForm'].mivId;
    if(mivId==null) return false;
    if (mivId.length!=null) {
        for (i = 0; i < mivId.length; i++) {
            if (mivId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = mivId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delMiv.do',null,document.forms['mivsForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else loadMivList(document.getElementById('mivKind').value);
    });
    mivId=null;
    return false;
}
function printMiv(){
    var mivId=document.forms['mivForm'].mivId;
    var kind=document.forms['mivForm'].mivKind;
    if(kind==null) kind=0;
    else kind=kind.value;
    if(mivId!=null) callServer('mivPrint.do?mivId='+mivId.value+'&kind='+kind);
    mivId=null;
}
function loadStoreLevel2List(params){
    callAjaxEx('storeLevel2List.do','ajaxContent','storeLevel2s.do','storelevel2sList',params);
    return false;
}
function loadMaterialStoreUsedList(stoId,params){
    var url="materialStoreUseds.do";
    stoId=stoId+"";
    if(stoId.indexOf("stoId=")!=-1){
//        stoId=document.getElementById('materialUsedStoIdHidden').value;
        url+="?"+stoId;
    } else{
        url+='?stoId='+stoId
    } 
    callAjax('materialStoreUsedList.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(stoId.indexOf("stoId=")==-1){
            document.getElementById('materialUsedStoIdHidden').value=stoId;
        }
        callAjaxEx(url,'materialUsedsList',params);
    });

    return false;
}
function searchMaterialStoreUsed(){
    var checkflag = true;
    var form=document.forms['searchSimpleUsedMaterialStore2Form'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    var stoId=document.getElementById('materialUsedStoIdHidden').value;
    if (checkflag == true) callAjax("searchMaterialStoreUsed.do?stoId="+stoId,"materialUsedsList",form,null);
    form=null;
    return false;
}
function loadMaterialStoreReturnedList(stoId,params){
    if(stoId==null){
        stoId=document.getElementById('materialReturnedStoIdHidden').value;
    }
    callAjax('materialStoreReturnedList.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        document.getElementById('materialReturnedStoIdHidden').value=stoId;
        callAjaxEx('materialStoreReturneds.do?stoId='+stoId,'materialReturnedsList',params);
    });

    return false;
}
function searchMaterialStoreReturned(){
    var checkflag = true;
    var form=document.forms['searchSimpleReturneMaterialStore2Form'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    var stoId=document.getElementById('materialReturnedStoIdHidden').value;
    if (checkflag == true) callAjax("searchMaterialStoreReturned.do?stoId="+stoId,"materialReturnedsList",form,null);
    form=null;
    return false;
}
function materialUsedForm(umsId) {
    var url="materialUsedForm.do";
    if (umsId!=null) url=url+"?umsId="+umsId;
    else{
        var stoId=document.getElementById('materialUsedStoIdHidden').value;
        url=url+"?stoId="+stoId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        var quantity=document.forms['umsForm'].quantity;
        if(quantity!=null){
            if (quantity.length!=null) {
                for (i = 0; i < quantity.length; i++) {
                    tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(quantity);
            }
        }
        quantity=null;
    });
    return false;
}
function materialReturnedForm(rmsId) {
    var url="materialReturnedForm.do";
    if (rmsId!=null) url=url+"?rmsId="+rmsId;
    else{
        var stoId=document.getElementById('materialReturnedStoIdHidden').value;
        url=url+"?stoId="+stoId;
    }
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        var quantity=document.forms['rmsForm'].quantity;
        if(quantity!=null){
            if (quantity.length!=null) {
                for (i = 0; i < quantity.length; i++) {
                    tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(quantity);
            }
        }
        quantity=null;
    });
    return false;
}
function saveUms() {
    if(scriptFunction=="saveUms()") return false;
    var updateDate = document.forms['umsForm'].updateDate;
    if(updateDate.value==''){
        alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y l\u1EADp');
        updateDate.focus();
        updateDate=null;
        return false;
    }
    updateDate=null;
    
    var createdEmp = document.forms['umsForm'].createdEmp;
    if(createdEmp.selectedIndex==-1){
        alert('Vui l\u00f2ng ch\u1ecdn ng\u01b0\u1eddi nh\u1eadn');
        createdEmp.focus();
        createdEmp=null;
        return false;
    }
    createdEmp=null;
    
    var orgId = document.forms['umsForm'].orgId;
    if(orgId.selectedIndex==-1){
        alert('Vui l\u00f2ng ch\u1ecdn b\u1ed9 ph\u1eadn');
        orgId.focus();
        orgId=null;
        return false;
    }
    orgId=null;

    var quantity=document.forms['umsForm'].usedQuantity;
    if(quantity!=null){
        if (quantity.length!=null) {
            for (i = 0; i < quantity.length; i++) {
                if(quantity[i].value=="0"){
                    alert("S\u1ED1 l\u01B0\u1EE3ng s\u1EED d\u1EE5ng ph\u1EA3i kh\u00E1c 0");
                    quantity[i].focus();
                    quantity=null;
                    return false;
                }
                reformatNumberMoney(quantity[i]);
            }
        } else{
            if(quantity.value=="0"){
                    alert("S\u1ED1 l\u01B0\u1EE3ng s\u1EED d\u1EE5ng ph\u1EA3i kh\u00E1c 0");
                    quantity.focus();
                    quantity=null;
                    return false;
                }
            reformatNumberMoney(quantity);
        }
        quantity=null;
    }
    scriptFunction="saveUms()";
    callAjaxCheckError("saveUms.do",null,document.forms['umsForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        var umsId = document.forms['umsForm'].umsId.value;
        materialUsedForm(umsId);
        //var stoId=document.forms['umsForm'].sto2Id.value;
        //loadMaterialStoreUsedList(stoId);
    });
    return false;
}
function saveRms() {
    if(scriptFunction=="saveRms()") return false;
    var updateDate = document.forms['rmsForm'].updateDate;
    if(updateDate.value==''){
        alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y l\u1EADp');
        updateDate.focus();
        updateDate=null;
        return false;
    }
    updateDate=null;
    
    var createdEmp = document.forms['rmsForm'].createdEmp;
    if(createdEmp.selectedIndex==-1){
        alert('Vui l\u00f2ng ch\u1ecdn ng\u01b0\u1eddi tr\u1EA3');
        createdEmp.focus();
        createdEmp=null;
        return false;
    }
    createdEmp=null;
    
    var orgId = document.forms['rmsForm'].orgId;
    if(orgId.selectedIndex==-1){
        alert('Vui l\u00f2ng ch\u1ecdn b\u1ed9 ph\u1eadn');
        orgId.focus();
        orgId=null;
        return false;
    }
    orgId=null;

    var quantity=document.forms['rmsForm'].returnedQuantity;
    if(quantity!=null){
        if (quantity.length!=null) {
            for (i = 0; i < quantity.length; i++) {
                reformatNumberMoney(quantity[i]);
            }
        } else{
            reformatNumberMoney(quantity);
        }
        quantity=null;
    }
    scriptFunction="saveRms()";
    callAjaxCheckError("saveRms.do",null,document.forms['rmsForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        var rmsId = document.forms['rmsForm'].rmsId.value;
        materialReturnedForm(rmsId);
    });
    return false;
}
function searchUsedMaterial(){
    callAjax('chooseUsedMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        searchMaterialUms();
    });
    return false;
}
function searchReturnedMaterial(){
    callAjax('chooseReturnedMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        searchMaterialRms();
    });
    return false;
}
function searchMaterialUms(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    var sto2Id=document.forms['umsForm'].sto2Id.value;
    callAjax("searchMaterialUms.do?stoId="+sto2Id+'&'+params,null,form,function(data){
        setAjaxData(data,'materialUmsList');
        var ms2rId = document.forms['materialUmsForm'].ms2rId;
        if(ms2rId==null) return false;
        var selId=document.forms['materialUmsSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (ms2rId.length!=null){
            for (i = 0; i < ms2rId.length; i++) {
                if (ids.indexOf(','+ms2rId[i].value+',')>-1){
                    ms2rId[i].disabled=true;
                    ms2rId[i].checked=true;
                }
            }
        }else if (ids.indexOf(','+ms2rId.value+',')>-1){
            ms2rId.disabled=true;
            ms2rId.checked=true;
        }
        ms2rId=null;
    });
    form=null;
    return false;
}
function searchMaterialRms(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    var sto2Id=document.forms['rmsForm'].sto2Id.value;
    var createdEmp=document.forms['rmsForm'].createdEmp.value;
    var url ="searchMaterialRms.do?stoId="+sto2Id+'&empId='+createdEmp; 
    var searchUpdateDateRms=document.forms['selectMaterialForm'].searchUpdateDateRms;
    if(searchUpdateDateRms!=null&&searchUpdateDateRms.value!=''){
        url+='&date='+searchUpdateDateRms.value;
        searchUpdateDateRms=null;
    }
    url+='&'+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialRmsList');
        var ms2rId = document.forms['materialRmsForm'].ms2rId;
        if(ms2rId==null) return false;
        var selId=document.forms['materialRmsSelectedForm'].materialSelectedChk;
        if(selId==null) return false;
        var ids='0';
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        ids+=',0';
        if (ms2rId.length!=null){
            for (i = 0; i < ms2rId.length; i++) {
                if (ids.indexOf(','+ms2rId[i].value+',')>-1){
                    ms2rId[i].disabled=true;
                    ms2rId[i].checked=true;
                }
            }
        }else if (ids.indexOf(','+ms2rId.value+',')>-1){
            ms2rId.disabled=true;
            ms2rId.checked=true;
        }
        ms2rId=null;
    });
    form=null;
    return false;
}
function setMaterialUmsSelected(){
    var ms2rId = document.forms['materialUmsForm'].ms2rId;
    if(ms2rId==null) return false;
    var matNameHidden = document.forms['materialUmsForm'].matNameHidden;
    var matCodeHidden = document.forms['materialUmsForm'].matCodeHidden;
    var quantityHidden = document.forms['materialUmsForm'].quantityHidden;
    var requestNumber = document.forms['materialUmsForm'].requestNumber;
    var mivNumber = document.forms['materialUmsForm'].mivNumber;

    var tbl=document.getElementById('materialUmsSelectedTbl');
    var lastRow = tbl.rows.length;
    if (ms2rId.length!=null) {
        for (i = 0; i < ms2rId.length; i++) {
            if (ms2rId[i].checked == true && ms2rId[i].disabled==false) {
                ms2rId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=ms2rId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(3);
                textNode = document.createTextNode(quantityHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(4);
                textNode = document.createTextNode(requestNumber[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(5);
                textNode = document.createTextNode(mivNumber[i].value);
                cell.appendChild(textNode);

                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (ms2rId.checked == true && ms2rId.disabled==false) {
            ms2rId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=ms2rId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(3);
            textNode = document.createTextNode(quantityHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(4);
            textNode = document.createTextNode(requestNumber.value);
            cell.appendChild(textNode);

            cell = row.insertCell(5);
            textNode = document.createTextNode(mivNumber.value);
            cell.appendChild(textNode);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    ms2rId=null;
    matNameHidden=null;
    matCodeHidden=null;
    quantityHidden=null;
    requestNumber=null;
    tbl=null;
}
function setMaterialRmsSelected(){
    var ms2rId = document.forms['materialRmsForm'].ms2rId;
    if(ms2rId==null) return false;
    var matNameHidden = document.forms['materialRmsForm'].matNameHidden;
    var matCodeHidden = document.forms['materialRmsForm'].matCodeHidden;
    var quantityHidden = document.forms['materialRmsForm'].quantityHidden;
    var requestNumber = document.forms['materialRmsForm'].requestNumber;
    var umsNumber = document.forms['materialRmsForm'].umsNumber;

    var tbl=document.getElementById('materialRmsSelectedTbl');
    var lastRow = tbl.rows.length;
    if (ms2rId.length!=null) {
        for (i = 0; i < ms2rId.length; i++) {
            if (ms2rId[i].checked == true && ms2rId[i].disabled==false) {
                ms2rId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=ms2rId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(3);
                textNode = document.createTextNode(quantityHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(4);
                textNode = document.createTextNode(requestNumber[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(5);
                textNode = document.createTextNode(umsNumber[i].value);
                cell.appendChild(textNode);

                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (ms2rId.checked == true && ms2rId.disabled==false) {
            ms2rId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=ms2rId.value;
            cell.appendChild(el);

            cell = row.insertCell(1);
            var textNode = document.createTextNode(matCodeHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(2);
            textNode = document.createTextNode(matNameHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(3);
            textNode = document.createTextNode(quantityHidden.value);
            cell.appendChild(textNode);

            cell = row.insertCell(4);
            textNode = document.createTextNode(requestNumber.value);
            cell.appendChild(textNode);

            cell = row.insertCell(5);
            textNode = document.createTextNode(umsNumber.value);
            cell.appendChild(textNode);

            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    ms2rId=null;
    matNameHidden=null;
    matCodeHidden=null;
    quantityHidden=null;
    requestNumber=null;
    umsNumber=null;
    tbl=null;
}
function delMaterialUms(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialUmsSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var ms2rId = document.forms['materialUmsForm'].ms2rId;
    if(ms2rId==null) return false;
    if (ms2rId.length!=null){
        for (i = 0; i < ms2rId.length; i++) {
            if (ids.indexOf(','+ms2rId[i].value+',')>-1){
                ms2rId[i].disabled=false;
                ms2rId[i].checked=false;
            }
        }

    }else if (ids.indexOf(','+ms2rId.value+',')>-1){
        ms2rId.disabled=false;
        ms2rId.checked=false;
    }
    ms2rId=null;
}
function delMaterialRms(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialRmsSelectedTbl');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(selId[i].checked==true){
                ids+=','+selId[i].value;
                parentNode=selId[i].parentNode;
                parentNode=parentNode.parentNode;
                tbl.deleteRow(parentNode.rowIndex);
            }
        }
        for(i=1;i<tbl.rows.length;i++){//header = 0, ignore
            if(i%2) tbl.rows[i].className="odd"
            else tbl.rows[i].className="even";
        }
    }else if(selId.checked==true){
        ids+=','+selId.value;
        tbl.deleteRow(0);
    }
    parentNode=null;
    tbl=null;
    selId=null;
    ids+=',0';
    var ms2rId = document.forms['materialRmsForm'].ms2rId;
    if(ms2rId==null) return false;
    if (ms2rId.length!=null){
        for (i = 0; i < ms2rId.length; i++) {
            if (ids.indexOf(','+ms2rId[i].value+',')>-1){
                ms2rId[i].disabled=false;
                ms2rId[i].checked=false;
            }
        }

    }else if (ids.indexOf(','+ms2rId.value+',')>-1){
        ms2rId.disabled=false;
        ms2rId.checked=false;
    }
    ms2rId=null;
}
function chooseMaterialUmsSelected(){
    var selId=document.forms['materialUmsSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    getUsedMaterialByMsrId(ids);
    hidePopupForm();
}
function chooseMaterialRmsSelected(){
    var selId=document.forms['materialRmsSelectedForm'].materialSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
        }
    }else ids+=','+selId.value;
    if(ids!='0') ids=ids.substring(2);
    getReturnedMaterialByMsrId(ids);
    hidePopupForm();
}
function getUsedMaterialByMsrId(ids){
    callAjax('getUsedMaterialByMsrId.do?msr2Id='+ids+'&stoId='+document.forms['umsForm'].sto2Id.value,null,null,function(data){
        setAjaxData(data,'musMaterialHideDiv');
        var matTable=document.getElementById('umsMaterialTable');
        var detTable=document.getElementById('umsDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;

        var usedQuantity=document.forms['umsForm'].usedQuantity;
        var currentQuantity=document.forms['umsForm'].usedQuantity;
        if(currentQuantity!=null){
            if (currentQuantity.length!=null) {
                for (i = 0; i < currentQuantity.length; i++) {
                    tryNumberFormat(currentQuantity[i]);
                    tryNumberFormat(usedQuantity[i]);
                }
            } else{
                tryNumberFormat(currentQuantity);
                tryNumberFormat(usedQuantity);
            }
            currentQuantity=null;
            usedQuantity=null;
        }
        });
    return false;
}
function getReturnedMaterialByMsrId(ids){
    callAjax('getReturnedMaterialByMsrId.do?msr2Id='+ids+'&stoId='+document.forms['rmsForm'].sto2Id.value,null,null,function(data){
        setAjaxData(data,'rusMaterialHideDiv');
        var matTable=document.getElementById('rmsMaterialTable');
        var detTable=document.getElementById('rmsDetailTable');
        if(matTable.tBodies[0]==null || detTable.tBodies[0]==null){
            matTable=null;
            detTable=null;
            return;
        }
        for( var i=matTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(matTable.tBodies[0].rows[i]);
        }
        matTable=null;
        detTable=null;

        var returnedQuantity=document.forms['rmsForm'].returnedQuantity;
        var currentQuantity=document.forms['rmsForm'].usedQuantity;
        if(currentQuantity!=null){
            if (currentQuantity.length!=null) {
                for (i = 0; i < currentQuantity.length; i++) {
                    tryNumberFormat(currentQuantity[i]);
                    tryNumberFormat(returnedQuantity[i]);
                }
            } else{
                tryNumberFormat(currentQuantity);
                tryNumberFormat(returnedQuantity);
            }
            currentQuantity=null;
            returnedQuantity=null;
        }
        });
    return false;
}
function delUmsMaterial(){
    var checkflag = false;
    var detId = document.forms['umsForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true){
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delUmsDetail.do',null,document.forms['umsForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('umsDetailTable');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delRmsMaterial(){
    var checkflag = false;
    var detId = document.forms['rmsForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true){
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRmsDetail.do',null,document.forms['rmsForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('rmsDetailTable');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delMaterialUseds(){
    var checkflag = false;
    var umsId = document.forms['materialUsedsForm'].umsId;
    if(umsId==null) return false;
    if (umsId.length!=null) {
        for (i = 0; i < umsId.length; i++) {
            if (umsId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = umsId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delUms.do',null,document.forms['materialUsedsForm'],function(data){
        stoId=document.getElementById('materialUsedStoIdHidden').value;
        loadMaterialStoreUsedList(stoId);
    });
    umsId=null;
    return false;
}
function delMaterialReturneds(){
    var checkflag = false;
    var rmsId = document.forms['materialReturnedsForm'].rmsId;
    if(rmsId==null) return false;
    if (rmsId.length!=null) {
        for (i = 0; i < rmsId.length; i++) {
            if (rmsId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = rmsId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRms.do',null,document.forms['materialReturnedsForm'],function(data){
        var stoId=document.getElementById('materialReturnedStoIdHidden').value;
        loadMaterialStoreReturnedList(stoId);
    });
    rmsId=null;
    return false;
}
function umsCheckQuantity(id){
    var usedquantity=document.getElementById("usedquantity"+id);
    var currentquantity=document.getElementById("currentquantity"+id).value*1;
    if (usedquantity.value*1 > currentquantity) {
        alert("SL s\u1EED d\u1EE5ng kh\u00F4ng \u0111\u01B0\u1EE3c v\u01B0\u1EE3t qu\u00E1 SL hi\u1EC7n t\u1EA1i!");
        usedquantity.value=currentquantity;
    }
    usedquantity=null;
    return false;
}
function rmsCheckQuantity(id){
    var returnedquantity=document.getElementById("returnedquantity"+id);
    var currentquantity=document.getElementById("currentquantity"+id).value*1;
    if (returnedquantity.value*1 > currentquantity) {
        alert("SL tr\u1EA3 kh\u00F4ng \u0111\u01B0\u1EE3c v\u01B0\u1EE3t qu\u00E1 SL nh\u1EADn!");
        returnedquantity.value=currentquantity;
    }
    returnedquantity=null;
    return false;
}
function loadMCReportForm(){
    callAjax('mcReportForm.do','ajaxContent',null,null);
    return false;
}
function printMCReport() {
//    var mcReportProject=document.forms['mcReportForm'].mcReportProject;
//    //if(mcReportProject.selectedIndex==-1 || mcReportProject.selectedIndex==0){
//    if(mcReportProject.selectedIndex==-1){
//        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
//        mcReportProject.focus();
//        mcReportProject=null;
//        return false;
//    }
    var mcStore=document.forms['mcReportForm'].mcStore;
    if(mcStore.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn kho');
        mcStore.focus();
        mcStore=null;
        return false;
    }
    var mcReportMaterialKind=document.forms['mcReportForm'].mcReportMaterialKind;
    if(mcReportMaterialKind.selectedIndex==-1) mcReportMaterialKind="0";
    else mcReportMaterialKind=mcReportMaterialKind.value;
//    var url='mcReportPrint.do?mcReportProject='+mcReportProject.value;
    var url='mcReportPrint.do?mcReportProject=1';
    url+='&mcStore='+mcStore.value;
    url+='&mcReportMaterialKind='+mcReportMaterialKind;
    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value
    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value
    url+='&rfmFromDate='+document.forms['mcReportForm'].rfmFromDate.value
    url+='&rfmEndDate='+document.forms['mcReportForm'].rfmEndDate.value
    url+='&mivFromDate='+document.forms['mcReportForm'].mivFromDate.value
    url+='&mivEndDate='+document.forms['mcReportForm'].mivEndDate.value
    callServer(url);
//    mcReportProject=null;
    mcStore=null;
    mcReportMaterialKind=null;
}
function searchMiv(){
    var checkflag = true;
    var form=document.forms['searchSimpleMivForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchMiv.do","mivList",form,null);
    form=null;
    return false;
}
function addPermissionOrg(){
    var list=document.forms['permissionForm'].orgList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    var permissionOrgId = document.forms['permissionForm'].permissionOrgId;
    var existed=false;
    if(permissionOrgId!=null){
        if (permissionOrgId.length!=null) {
            for (i = 0; i < permissionOrgId.length; i++) {


                if (permissionOrgId[i].value == id) {
                    existed = true;
                    break;
                }
            }
        } else if(permissionOrgId.value==id) existed=true;
    }
    if(existed==true){
        alert("Ph\u00F2ng ban \u0111\u00E3 t\u1ED3n t\u1EA1i");
        return false;
    }

    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('permissionOrgTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'permissionOrgChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'permissionOrgId';
    el.value=id;
    cell.appendChild(el);

    cell = row.insertCell(1);
    el = document.createElement('span');
    el.name = 'permissionOrg';
    el.innerHTML=name;
    cell.appendChild(el);

    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}

function addPermissionEmp(){
    var list=document.forms['permissionForm'].empList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    var permissionEmpId = document.forms['permissionForm'].permissionEmpId;
    var existed=false;
    if(permissionEmpId!=null){
        if (permissionEmpId.length!=null) {
            for (i = 0; i < permissionEmpId.length; i++) {
                if (permissionEmpId[i].value == id) {
                    existed = true;
                    break;
                }
            }
        } else if(permissionEmpId.value==id) existed=true;
    }
    if(existed==true){
        alert("Nh\u00E2n vi\u00EAn \u0111\u00E3 t\u1ED3n t\u1EA1i");
        return false;
    }

    var name=list.options[list.selectedIndex].text.split(" - ");
    list=null;
    var tbl=document.getElementById('permissionEmpTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'permissionEmpChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'permissionEmpId';
    el.value=id;
    cell.appendChild(el);

    cell = row.insertCell(1);
    el = document.createElement('span');
    el.name = 'permissionEmp';
    el.innerHTML=name[0];
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('span');
    el.name = 'permissionEmp';
    el.innerHTML=name[1];
    cell.appendChild(el);
    
    cell = row.insertCell(3);
    el = document.createElement('span');
    el.name = 'permissionEmp';
    el.innerHTML=name[2];
    cell.appendChild(el);

    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delPermissionOrg(){
    var checkflag = false;
    var detId = document.forms['permissionForm'].permissionOrgChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true){
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPermissionOrg.do',null,document.forms['permissionForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('permissionOrgTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delPermissionEmp(){
    var checkflag = false;
    var detId = document.forms['permissionForm'].permissionEmpChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true){
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPermissionEmp.do',null,document.forms['permissionForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('permissionEmpTbl');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {
                        if(detId[i].checked==true){
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);
                        }
                    }
                }else if(detId.checked==true) tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delPermissions(){
    var checkflag = false;
    var perId = document.forms['permissionsForm'].perId;
    if(perId==null) return false;
    if (perId.length!=null) {
        for (i = 0; i < perId.length; i++) {
            if (perId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = perId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPermission.do','permissionList',document.forms['permissionsForm'],null);
    perId=null;
    return false;
}

function loadMCRequestReportForm(){
    callAjax('mcRequestReportForm.do','ajaxContent',null,null);
    return false;
}
function printMCRequestReport() {
    var mcReportProject=document.forms['mcReportForm'].mcReportProject;
    if(mcReportProject.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
        mcReportProject.focus();
        mcReportProject=null;
        return false;
    }
    var mcReportRequest=document.forms['mcReportForm'].mcReportRequest;
    if(mcReportRequest.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn phi\u1EBFu \u0111\u1EC1 xu\u1EA5t');
        mcReportRequest.focus();
        mcReportRequest=null;
        return false;
    }
    var url='mcRequestReportPrint.do?mcReportProject='+mcReportProject.value;
    url+='&mcReportRequest='+mcReportRequest.value;
    url+='&deliveryFromDate='+document.forms['mcReportForm'].deliveryFromDate.value;
    url+='&deliveryEndDate='+document.forms['mcReportForm'].deliveryEndDate.value;
    url+='&contractFromDate='+document.forms['mcReportForm'].contractFromDate.value
    url+='&contractEndDate='+document.forms['mcReportForm'].contractEndDate.value
    url+='&mrirFromDate='+document.forms['mcReportForm'].mrirFromDate.value
    url+='&mrirEndDate='+document.forms['mcReportForm'].mrirEndDate.value
    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value
    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value
    url+='&rfmFromDate='+document.forms['mcReportForm'].rfmFromDate.value
    url+='&rfmEndDate='+document.forms['mcReportForm'].rfmEndDate.value
    url+='&mivFromDate='+document.forms['mcReportForm'].mivFromDate.value
    url+='&mivEndDate='+document.forms['mcReportForm'].mivEndDate.value
    url+='&nameVn='+encodeURIComponent(document.forms['mcReportForm'].nameVn.value);
    url+='&code='+document.forms['mcReportForm'].code.value;
    callServer(url);
    mcReportProject=null;
    mcReportRequest=null;
}
function loadMCProjectStoreReportForm(){
    callAjax('mcProjectStoreReportForm.do','ajaxContent',null,null);
    return false;
}
function loadStoreLevel2BalanceReportForm(){
    callAjax('storeLevel2ReportForm.do','ajaxContent',null,null);
    return false;
}
function printMCProjectStoreReport() {
    var mcReportProject=document.forms['mcReportForm'].mcReportProject;
    var mcStore=document.forms['mcReportForm'].mcStore;
    if(mcReportProject.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
        mcReportProject.focus();
        mcReportProject=null;
        return false;
    }else if(mcReportProject.selectedIndex!=0){
            if(mcStore.selectedIndex==-1){
            alert('Vui l\u00F2ng ch\u1ECDn kho');
            mcStore.focus();
            mcStore=null;
            return false;
        }
    }
//    var mcReportMaterialKind=document.forms['mcReportForm'].mcReportMaterialKind;
//    if(mcReportMaterialKind.selectedIndex==-1) mcReportMaterialKind="0";
//    else mcReportMaterialKind=mcReportMaterialKind.value;
    var url='mcProjectStoreReportPrint.do?mcReportProject='+mcReportProject.value;
    url+='&mcStore='+mcStore.value;
//    url+='&mcReportMaterialKind='+mcReportMaterialKind;
    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value;
    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value;
    url+='&rfmFromDate='+document.forms['mcReportForm'].rfmFromDate.value;
    url+='&rfmEndDate='+document.forms['mcReportForm'].rfmEndDate.value;
    url+='&mivFromDate='+document.forms['mcReportForm'].mivFromDate.value;
    url+='&mivEndDate='+document.forms['mcReportForm'].mivEndDate.value;
    url+='&nameVn='+encodeURIComponent(document.forms['mcReportForm'].nameVn.value);
    url+='&code='+document.forms['mcReportForm'].code.value;
    callServer(url);
    mcReportProject=null;
    mcStore=null;
    mcReportMaterialKind=null;
}
function reportProjectChanged(list){
    if(list.selectedIndex==-1) return false;
    var proId=null;
    if(document.forms['mcReportForm']!=null){
        proId=document.forms['mcReportForm'].mcReportProject;
    }
    if(proId.selectedIndex==null) proId=proId.value
    else proId=proId.options[proId.selectedIndex].value;
    var url='getRequestOfProject.do?proId='+proId;
    callAjax(url,null,null,function(data){
        if(document.forms['mcReportForm']!=null){
            setAjaxData(data,'projectRequest');
        }
    });
    list=null;
    return false;
}
function mcReportProjectChanged(list){
    if(list.selectedIndex==-1) return false;
    var proId=null;
    if(document.forms['mcReportForm']!=null){
        proId=document.forms['mcReportForm'].mcReportProject;
    }
    if(proId.selectedIndex==null) proId=proId.value
    else proId=proId.options[proId.selectedIndex].value;
    var url='getStoreProject.do?proId='+proId;
    callAjax(url,null,null,function(data){
        if(document.forms['mcReportForm']!=null){
            setAjaxData(data,'projectStore');
        }
    });
    list=null;
    return false;
}

function loadMCMaterialBalanceReportForm(){
    callAjax('mcMaterialBalanceReportForm.do','ajaxContent',null,null);
    return false;
}
function printMCMaterialBalanceReport() {
    var mcReportProject=document.forms['mcReportForm'].mcReportProject;
    if(mcReportProject.selectedIndex==-1 || mcReportProject.selectedIndex==0){
        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
        mcReportProject.focus();
        mcReportProject=null;
        return false;
    }
    var mcStore=document.forms['mcReportForm'].mcStore;
    if(mcStore.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn kho');
        mcStore.focus();
        mcStore=null;
        return false;
    }
    var mcReportMaterialKind=document.forms['mcReportForm'].mcReportMaterialKind;
    if(mcReportMaterialKind.selectedIndex==-1) mcReportMaterialKind="0";
    else mcReportMaterialKind=mcReportMaterialKind.value;
    var url='mcMaterialBalanceReportPrint.do?mcReportProject='+mcReportProject.value;
    url+='&mcStore='+mcStore.value;
    url+='&mcReportMaterialKind='+mcReportMaterialKind;
    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value
    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value
    url+='&rfmFromDate='+document.forms['mcReportForm'].rfmFromDate.value
    url+='&rfmEndDate='+document.forms['mcReportForm'].rfmEndDate.value
    url+='&mivFromDate='+document.forms['mcReportForm'].mivFromDate.value
    url+='&mivEndDate='+document.forms['mcReportForm'].mivEndDate.value
    callServer(url);
    mcReportProject=null;
    mcStore=null;
    mcReportMaterialKind=null;
    return false;
}

function umsOrganizationChanged(list,detId,div){
    if(list.selectedIndex==-1) return false;
    var orgId=list.options[list.selectedIndex].value;
    var url='getEmployeeOfOrg.do?orgId='+orgId;
    if(div==null) div='umsEmployee';
    if(detId!=null) div+=detId;
    callAjax(url,div,null,null);
    list=null;
    return false;
}

function rmsOrganizationChanged(list,detId,div){
    if(list.selectedIndex==-1) return false;
    var orgId=list.options[list.selectedIndex].value;
    var url='getEmployeeOfOrg.do?orgId='+orgId;
    if(div==null) div='rmsEmployee';
    if(detId!=null) div+=detId;
    callAjax(url,div,null,null);
    list=null;
    return false;
}

function printUms(){
    var umsId=document.forms['umsForm'].umsId;
    if(umsId!=null) callServer('umsPrint.do?umsId='+umsId.value);
    umsId=null;
}
function printRms(){
    var rmsId=document.forms['rmsForm'].rmsId;
    if(rmsId!=null) callServer('rmsPrint.do?rmsId='+rmsId.value);
    rmsId=null;
}

function store2ProjectChanged(list){
    if(list.selectedIndex==-1) return false;
    var proId=null;
    if(document.forms['mcReportForm']!=null){
        proId=document.forms['mcReportForm'].mcReportProject;
    }
    if(proId.selectedIndex==null) proId=proId.value
    else proId=proId.options[proId.selectedIndex].value;
    var url='getStore2OfProject.do?proId='+proId;
    callAjax(url,"mcReportOrganization",null,null);
    list=null;
    return false;
}

function printStore2BalanceReport() {
    var mcReportProject=document.forms['mcReportForm'].mcReportProject;
    var mcReportOrganization=document.forms['mcReportForm'].mcReportOrganization;
//    var mcReportOrgList=document.forms['mcReportForm'].mcReportOrgList;
//    if(mcReportProject.selectedIndex==-1){
//        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
//        mcReportProject.focus();
//        mcReportProject=null;
//        return false;
//    }else if(mcReportProject.selectedIndex!=0){
//            if(mcStore.selectedIndex==-1){
//            alert('Vui l\u00F2ng ch\u1ECDn kho');
//            mcStore.focus();
//            mcStore=null;
//            return false;
//        }
//    }
    var url='store2BalanceReportPrint.do?mcReportProject='+mcReportProject.value;
    url+='&mcStore='+mcReportOrganization.value;
//    url+='&mcOrg='+mcReportOrgList.value;
    url+='&nameVn='+encodeURIComponent(document.forms['mcReportForm'].nameVn.value);
    url+='&code='+document.forms['mcReportForm'].code.value;
    callServer(url);
    mcReportProject=null;
    mcReportOrganization=null;
//    mcReportOrgList=null;
    return false;
}
