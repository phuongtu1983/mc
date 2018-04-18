function loadRequestList(params){
    callAjaxEx('requestList.do','ajaxContent','searchRequest.do','requestList',params);
    return false;
}
function loadRequestListSort(params){
    //phan trang loi : callAjaxEx('requests.do','requestList',null,null,params);
    callAjaxEx('searchRequest.do','requestList',null,null,params);
    return false;
}
function loadRequestListSort1(params){
    callAjaxEx('requests.do','requestList',null,null,params);
    return false;
}
function getRequestListData(data){
    setAjaxData(data,'ajaxContent');
    loadRequests();
    return false;
}
function loadRequests(){
    callAjax("requests.do","requestList",null,null);
    return false;
}
function requestForm(reqId, divContent,isShortcut){
    var url="requestForm.do?t=1";
    if(reqId!=null) url=url+"&reqId="+reqId;
    if (divContent==null)
        divContent='ajaxContent';
    callAjax(url,null,null,function(data){
        setAjaxData(data,divContent);
        if(reqId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateRequest').value=date+'/'+month+'/'+currentTime.getFullYear();
            
            if(isShortcut!=null && isShortcut==true) document.forms['requestForm'].isShortCut.value=1;
        }
        if(document.forms['requestForm']!=null) reqId=document.forms['requestForm'].reqId.value;
        var list=document.forms['requestForm'].whichUse;
        if(list!=null && list.selectedIndex>-1){
            getWhichUse(reqId,list.options[list.selectedIndex].value);
            list=null;
        }
        url='listRequestDetail.do';
        if(reqId!=null) url=url+"?reqId="+reqId;
        if(document.forms['requestForm'].isAssignEmp.value>0) url=url+"&assignEmp=1";
        callAjax(url,null,null,function(data){
            setAjaxData(data,'listRequestMaterialDataDiv');
            var presentQuantity=document.forms['requestForm'].presentQuantity;
            var requestQuantity=document.forms['requestForm'].requestQuantity;
            
            if(requestQuantity!=null){
                if (requestQuantity.length!=null) {
                    for (i = 0; i < requestQuantity.length; i++) {
                        tryNumberFormat(requestQuantity[i],"USD");
                        tryNumberFormat(presentQuantity[i],"USD");
                    }
                } else{
                    tryNumberFormat(requestQuantity,"USC");
                    tryNumberFormat(presentQuantity,"USD");
                }
            }
            
            requestQuantity=null;
            presentQuantity=null;
        });
        if(reqId!=null) {
            loadAttachFileSystem(1,reqId);
        }
    });
    return false;
}
function whichUseChanged(list){
    if(list.selectedIndex==-1) return false;
    var reqId=null;
    if(document.forms['requestForm']!=null) reqId=document.forms['requestForm'].reqId;
    getWhichUse(reqId.value,list.options[list.selectedIndex].value);
    list=null;
    reqId=null;
    return false;
}
function getWhichUse(reqId,kind,params){
    var url="whichUseList.do?whichuse="+kind;
    if(reqId!=null) url+="&reqId="+reqId;
    if(params!=null) url+="&"+params;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'whichuseSpan');
    });
}
function saveRequest() {
    if(scriptFunction=="saveRequest()") return false;
    var requestNumber = document.forms['requestForm'].requestNumber;
    if(requestNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u");
        requestNumber.focus();
        requestNumber=null;
        return false;
    }
    requestNumber=null;
    var approveSuggest = document.forms['requestForm'].approveSuggest;
    var checked=0;
    for(var i=0;i<approveSuggest.length;i++){
        if(approveSuggest[i].checked==true){
            checked=1;
            break;
        }
    }
    if(checked==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0111\u1EC1 ngh\u1ECB ph\u00EA duy\u1EC7t");
        return false;
    }
    approveSuggest=null;
    
    var matId = document.forms['requestForm'].matId;
    if(matId==null){
        alert("Vui l\u00F2ng ch\u1ECDn v\u1EADt t\u01B0");
        return false;
    }
    matId=null;
    
    var certificateRequire = document.forms['requestForm'].certificateRequire;
    if(certificateRequire.value==""){
        alert("Vui l\u00F2ng nh\u1EADp 'Y\u00EAu c\u1EA7u ch\u1EE9ng ch\u1EC9'");
        certificateRequire.focus();
        certificateRequire=null;
        return false;
    }
    
    var description = document.forms['requestForm'].description;
    if(description.value==""){
        alert("Vui l\u00F2ng nh\u1EADp 'Gi\u1EA3i tr\u00ECnh'");
        description.focus();
        description=null;
        return false;
    }
    
    var deliverDate = document.forms['requestForm'].deliverDate;
    if(deliverDate!=null){
        if(deliverDate.length!=null){
            for(var i=0;i<deliverDate.length;i++){
                if(deliverDate[i].value!=""){
                    if(!isDate(deliverDate[i].value,"dd/MM/yyyy")){
                        alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
                        deliverDate[i].focus();
                        deliverDate=null;
                        return false;
                    }
                }else{
                    alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
                    return false;
                }
            }
        }else if(deliverDate.value!=""){
            if(!isDate(deliverDate.value,"dd/MM/yyyy")){
                alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
                deliverDate.focus();
                deliverDate=null;
                return false;
            }
        }else{
            alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
            return false;
        }
    }
    deliverDate=null;
    var requestQuantity = document.forms['requestForm'].requestQuantity;
    if(requestQuantity!=null){
        if(requestQuantity.length!=null){
            for(var i=0;i<requestQuantity.length;i++){
                var number=Number(requestQuantity[i].value);
                if(number==0){
                    alert('Vui l\u00F2ng nh\u1EADp s\u1ED1 l\u01B0\u1EE3ng y\u00EAu c\u1EA7u');
                    requestQuantity[i].focus();
                    requestQuantity=null;
                    return false;
                }
            }
        }else if(requestQuantity.value=="0"){
            alert('Vui l\u00F2ng nh\u1EADp s\u1ED1 l\u01B0\u1EE3ng y\u00EAu c\u1EA7u');
            requestQuantity.focus();
            requestQuantity=null;
            return false;
        }
    }
    var storeOrg=document.forms['requestForm'].storeOrg;
    if(storeOrg!=null){
        if(storeOrg.selectedIndex==0){
            alert("Vui l\u00F2ng ch\u1ECDn kho \u0111\u1EC3 ki\u1EC3m tra t\u1ED3n kho");
            storeOrg=null;
            return false;
        }
        storeOrg=null;
    }
    var presentQuantity=document.forms['requestForm'].presentQuantity;
    var additionalQuantity=document.forms['requestForm'].additionalQuantity;
//    var requestQuantity=document.forms['requestForm'].requestQuantity;
    
    if(requestQuantity!=null){
        if (requestQuantity.length!=null) {
            for (i = 0; i < requestQuantity.length; i++) {
                reformatNumberMoney(presentQuantity[i]);
                reformatNumberMoney(additionalQuantity[i]);
                reformatNumberMoney(requestQuantity[i]);
            }
        } else{
            reformatNumberMoney(presentQuantity);
            reformatNumberMoney(additionalQuantity);
            reformatNumberMoney(requestQuantity);
        }
    }

    presentQuantity=null;
    additionalQuantity=null;
    requestQuantity=null;
    
    //callAjaxCheckError("saveRequest.do",null,document.forms['requestForm'],getRequestListData);
    var reqId = document.forms['requestForm'].reqId.value;
    scriptFunction="saveRequest()";
    callAjaxCheckError("saveRequest.do",null,document.forms['requestForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        requestForm(reqId);
    });
    return false;
}
function saveRequestCopy() {
    if(scriptFunction=="saveRequestCopy()") return false;
    var requestNumber = document.forms['requestForm'].requestNumber;
    if(requestNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 y\u00EAu c\u1EA7u");
        requestNumber.focus();
        requestNumber=null;
        return false;
    }
    requestNumber=null;
    
    var certificateRequire = document.forms['requestForm'].certificateRequire;
    if(certificateRequire.value==""){
        alert("Vui l\u00F2ng nh\u1EADp 'Y\u00EAu c\u1EA7u ch\u1EE9ng ch\u1EC9'");
        certificateRequire.focus();
        certificateRequire=null;
        return false;
    }
    
    var description = document.forms['requestForm'].description;
    if(description.value==""){
        alert("Vui l\u00F2ng nh\u1EADp 'Gi\u1EA3i tr\u00ECnh'");
        description.focus();
        description=null;
        return false;
    }
    
    var deliverDate = document.forms['requestForm'].deliverDate;
    if(deliverDate!=null){
        if(deliverDate.length!=null){
            for(var i=0;i<deliverDate.length;i++){
                if(deliverDate[i].value!=""){
                    if(!isDate(deliverDate[i].value,"dd/MM/yyyy")){
                        alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
                        deliverDate[i].focus();
                        deliverDate=null;
                        return false;
                    }
                }else{
                    alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
                    return false;
                }
            }
        }else if(deliverDate.value!=""){
            if(!isDate(deliverDate.value,"dd/MM/yyyy")){
                alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
                deliverDate.focus();
                deliverDate=null;
                return false;
            }
        }else{
            alert('Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7');
            return false;
        }
    }
    deliverDate=null;
    var requestQuantity = document.forms['requestForm'].requestQuantity;
    if(requestQuantity!=null){
        if(requestQuantity.length!=null){
            for(var i=0;i<requestQuantity.length;i++){
                var number=Number(requestQuantity[i].value);
                if(number==0){
                    alert('Vui l\u00F2ng nh\u1EADp s\u1ED1 l\u01B0\u1EE3ng y\u00EAu c\u1EA7u');
                    requestQuantity[i].focus();
                    requestQuantity=null;
                    return false;
                }
            }
        }else if(requestQuantity.value=="0"){
            alert('Vui l\u00F2ng nh\u1EADp s\u1ED1 l\u01B0\u1EE3ng y\u00EAu c\u1EA7u');
            requestQuantity.focus();
            requestQuantity=null;
            return false;
        }
    }
    var storeOrg=document.forms['requestForm'].storeOrg;
    if(storeOrg!=null){
        if(storeOrg.selectedIndex==0){
            alert("Vui l\u00F2ng ch\u1ECDn kho \u0111\u1EC3 ki\u1EC3m tra t\u1ED3n kho");
            storeOrg=null;
            return false;
        }
        storeOrg=null;
    }
    var presentQuantity=document.forms['requestForm'].presentQuantity;
    var additionalQuantity=document.forms['requestForm'].additionalQuantity;
//    var requestQuantity=document.forms['requestForm'].requestQuantity;
    
    if(requestQuantity!=null){
        if (requestQuantity.length!=null) {
            for (i = 0; i < requestQuantity.length; i++) {
                reformatNumberMoney(presentQuantity[i]);
                reformatNumberMoney(additionalQuantity[i]);
                reformatNumberMoney(requestQuantity[i]);
            }
        } else{
            reformatNumberMoney(presentQuantity);
            reformatNumberMoney(additionalQuantity);
            reformatNumberMoney(requestQuantity);
        }
    }

    presentQuantity=null;
    additionalQuantity=null;
    requestQuantity=null;
    
    var reqId = document.forms['requestForm'].reqId.value;
    scriptFunction="saveRequestCopy()";
    callAjaxCheckError("saveRequestCopy.do",null,document.forms['requestForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        requestForm(reqId);
    });
    return false;
}
function emailForNotMaterialCode(){
    var reqId = document.forms['requestForm'].reqId;
    if(reqId==null) return false;
    else reqId=reqId.value;
    callAjaxCheckError("emailNotCode.do?reqId="+reqId,null,null,function(data){
        alert("\u0110\u00E3 g\u1EDFi email!");
        requestForm(reqId);
    });
    return false;
}
function requestSentTo(status, orgHandle, sendMailHandle) {
    if(scriptFunction=="requestSentTo") return false;
    var reqId = document.forms['requestForm'].reqId.value;
    var url="requestSentTo.do?reqId="+reqId+"&status="+status;
    if(orgHandle!=null) url+="&orgHandle="+orgHandle;
    scriptFunction="requestSentTo";
    callAjaxCheckError(url,null,null,function(data){
        scriptFunction="";
        saveRequest();
        if(sendMailHandle!=null) sendMailHandle();
    });
    return false;

}
function emailRequestToStore(){
    var reqId = document.forms['requestForm'].reqId;
    if(reqId==null) return false;
    else reqId=reqId.value;
    callAjaxCheckError("emailRequestToStore.do?reqId="+reqId,null,null,null);
    return false;
}
function emailStoreToRequest(){
    var reqId = document.forms['requestForm'].reqId;
    if(reqId==null) return false;
    else reqId=reqId.value;
    callAjaxCheckError("emailStoreToRequest.do?reqId="+reqId,null,null,null);
    return false;
}

function requestSentTo_Store(status) {
    var storeApprove = document.forms['requestForm'].storeApprove;
    if(storeApprove.value!=1){
        alert("Vui l\u00F2ng duy\u1EC7t tr\u01B0\u1EDBc khi chuy\u1EC3n");
        return false;
    }else{
        requestSentTo(status,null,emailStoreToRequest);
    }
    storeApprove=null;
    return false;

}
function requestSentTo_Check(status) {
    var checkApprove = document.forms['requestForm'].checkApprove;
    if(checkApprove.value!=1){
        alert("Vui l\u00F2ng duy\u1EC7t tr\u01B0\u1EDBc khi chuy\u1EC3n");
        return false;
    }else{
        requestSentTo(status);
    }
    checkApprove=null;
    return false;
}
function requestSentTo_PlanDep(status) {
    var plandepApprove = document.forms['requestForm'].plandepApprove;
//    if(plandepApprove.value!=1){
//        alert("Vui l\u00F2ng duy\u1EC7t tr\u01B0\u1EDBc khi chuy\u1EC3n");
//        return false;
//    }else{
//        requestSentTo(status);
//    }
    var storeApprove = document.forms['requestForm'].storeApprove;
    if(storeApprove.value!=1){
        alert("Vui l\u00F2ng duy\u1EC7t tr\u01B0\u1EDBc khi chuy\u1EC3n");
        return false;
    }else{
        requestSentTo(status);
    }
    plandepApprove=null;
    return false;
}
function requestSentTo_Manager(status) {
    var bomApprove = document.forms['requestForm'].bomApprove;
    if(bomApprove.value!=1){
        alert("Vui l\u00F2ng duy\u1EC7t tr\u01B0\u1EDBc khi chuy\u1EC3n");
        return false;
    }
    bomApprove=null;
    var orgHandle = document.forms['requestForm'].orgHandle;
    var checked='';
    
    for(var i=0;i<orgHandle.length;i++){
        if(orgHandle[i].checked==true) checked+=","+orgHandle[i].value;
    }
    if(checked==''){
        alert("Vui l\u00F2ng ch\u1ECDn \u0111\u01A1n v\u1ECB x\u1EED l\u00FD");
        return false;
    }else{
        requestSentTo(status,checked.substring(1));
    }
    orgHandle=null;
    return false;
}
function selectMaterial(handle,title){
    if(handle==null) return false;
    popupName=title;
    callAjax('chooseMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchMaterialRequest();
    });
    return false;
}
function checkMaterial(title, reqId){
    popupName=title;
    callAjax('checkMaterialForm.do?reqId='+reqId,null,null,function(data){
        showPopupForm(data);
        checkMaterialRequest();
    });
    return false;
}
function checkMaterialRequest(params){
    var url="checkMaterialRequest.do?a=1";
    var reqId=document.forms['checkRequestForm'].reqId;
    if(reqId!=null) url+="&reqId="+reqId.value;
    reqId=null;
    var matId=document.forms['checkRequestForm'].material;
    if(matId!=null) url+="&matId="+matId.value;
    matId=null;
    var stoId=document.forms['checkRequestForm'].orgId;
    if(stoId!=null) url+="&stoId="+stoId.value;
    stoId=null;
    if(params!=null) url+="&"+params;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'checkMaterialRequestList');
    });
    return false;
}
function newRequestMaterial(handle,id,title){
    popupName=title;
    if(handle==null){
        callAjax('materialForm.do?matId='+id,null,null,function(data){
            showPopupForm(data);
        });
    }
    else{
        if(id==null){
            callAjax('addRequestMaterialForm.do',null,null,function(data){
                showPopupForm(data);
                document.getElementById('callbackFunc').value=handle;
            });
        }else{
            callAjax('addRequestMaterialForm.do?matId='+id,null,null,function(data){
                showPopupForm(data);
                document.getElementById('callbackFunc').value=handle;
            });
        }
    }
    return false;
}
function saveRequestMaterial() {
    var nameVn = document.forms['addRequestMaterial'].nameVn;
    var nameEn = document.forms['addRequestMaterial'].nameEn;
    if (nameVn.value.length==0 && nameEn.value.length==0){
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn=null;
        return false;
    }
    var uniId = document.forms['addRequestMaterial'].uniId;
    if(uniId.selectedIndex==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId=null;
        return false;
    }
    callAjaxCheckError("addRequestMaterial.do",null,document.forms['addRequestMaterial'],function(data){
        var index=data.indexOf('success:');
        if(index==0){
            var handle=document.getElementById('callbackFunc').value;
            if(handle!='') eval(handle+"('"+data.substring(8)+"')");
            hidePopupForm();
        }
    });
    return false;
}
function reloadRequestDetail(){
    var url='listRequestDetail.do';
    var reqId=document.forms['requestForm'].reqId;
    if(reqId!=null) reqId=reqId.value;
    else return false;
    if(reqId!=null) url=url+"?reqId="+reqId;
    if(document.forms['requestForm'].isAssignEmp.value>0) url=url+"&assignEmp=1";
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listRequestMaterialDataDiv');
        var presentQuantity=document.forms['requestForm'].presentQuantity;
        var requestQuantity=document.forms['requestForm'].requestQuantity;

        if(requestQuantity!=null){
            if (requestQuantity.length!=null) {
                for (i = 0; i < requestQuantity.length; i++) {
                    tryNumberFormat(requestQuantity[i],"USD");
                    tryNumberFormat(presentQuantity[i],"USD");
                }
            } else{
                tryNumberFormat(requestQuantity,"USC");
                tryNumberFormat(presentQuantity,"USD");
            }
        }

        requestQuantity=null;
        presentQuantity=null;
    });
}
function setRequestMaterialDetail(matId){
    var url='materialDetail.do?matId='+matId;
    callAjax(url,null,null,function(data){
        var obj=eval('('+data+')');
        document.getElementById('code_'+matId).innerHTML=obj.matCode;
        document.getElementById('name_'+matId).innerHTML=obj.matName;
        document.getElementById('unit_'+matId).innerHTML=obj.matUnit;
    });
}
function setSelectedRequestMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['requestForm'].matId;
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
function delRequestMaterial(){
    var checkflag = false;
    var detId = document.forms['requestForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRequestDetail.do',null,document.forms['requestForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('requestDetailTable');
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
function searchRequest(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchRequest.do","requestList",form,null);
    form=null;
    return false;
}
function delRequests(){
    var checkflag = false;
    var reqId = document.forms['requestsForm'].reqId;
    if(reqId==null) return false;
    if (reqId.length!=null) {
        for (i = 0; i < reqId.length; i++) {
            if (reqId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = reqId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delRequest.do',null,document.forms['requestsForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else setAjaxData(data,'requestList');
    });
    reqId=null;
    return false;
}
function searchAdvRequestForm(){
    callAjax('searchAdvRequestForm.do',null,null,function(data){
        showPopupForm(data);
        var list=document.forms['searchRequestForm'].whichUse;
        if(list!=null && list.selectedIndex>-1){
            getWhichUse(null,list.options[list.selectedIndex].value,"addFirst=1");
            list=null;
        }
    });
    return false;
}
function searchAdvRequest(){
    callAjax('searchAdvRequest.do',null,document.forms['searchRequestForm'],getSearchAdvRequestData);
    hidePopupForm();
    return false;
}
function getSearchAdvRequestData(data){
    setAjaxData(data,'requestList');
}
function searchMaterialRequest(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchMaterialRequest.do";
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
function searchMaterialChangeRequest(params){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchMaterialRequestForChange.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialChangeRequestList');
        var matId = document.forms['materialChangeRequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialChangeRequestSelectedForm'].materialSelectedChk;
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
function setMaterialSelected(){
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
    return false;
}
function delMaterialRequest(){
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
    return false;
}
function chooseMaterialSelected(){
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
    return false;
}
//INTERMEMO
function loadIntermemoList(params){
    callAjaxEx('intermemoList.do','ajaxContent','searchIntermemo.do','intermemoList',params);
    return false;
}
function loadIntermemoListSort(params){
    //phan trang loi:    callAjaxEx('intermemos.do','intermemoList',null,null,params);
    callAjaxEx('searchIntermemo.do','intermemoList',null,null,params);
    return false;
}
function loadIntermemoListSort(params){
    callAjaxEx('intermemos.do','intermemoList',null,null,params);
    return false;
}
function getIntermemoListData(data){
    setAjaxData(data,'ajaxContent');
    loadIntermemos();
    return false;
}
function loadIntermemos(){
    callAjax("intermemos.do","intermemoList",null,null);
    return false;
}
function intermemoForm(reqId){
    var url="intermemoForm.do";
    if(reqId!=null) url=url+"?reqId="+reqId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(reqId==null){
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateRequest').value=date+'/'+month+'/'+currentTime.getFullYear();
        }
        url='listIntermemoDetail.do';
        if(reqId!=null) url=url+"?reqId="+reqId;
        callAjax(url,'listIntermemoMaterialDataDiv',null,null);
        if(reqId!=null) {
            loadAttachFileSystem(1,reqId);
        }
    });
    return false;
}
function setSelectedIntermemoMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['intermemoForm'].matId;
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
    callAjax("listIntermemoMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'intermemoMaterialHideDiv');
        var matTable=document.getElementById('intermemoMaterialTable');
        var detTable=document.getElementById('intermemoDetailTable');
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
function saveIntermemo() {
    var requestNumber = document.forms['intermemoForm'].requestNumber;
    if(requestNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 intermemo!");
        requestNumber.focus();
        requestNumber=null;
        return false;
    }
    requestNumber=null;
    //callAjaxCheckError("saveIntermemo.do",null,document.forms['intermemoForm'],getIntermemoListData);
    var reqId = document.forms['intermemoForm'].reqId.value;
    callAjaxCheckError("saveIntermemo.do",null,document.forms['intermemoForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        intermemoForm(reqId);
    });
    return false;
}
function delIntermemos(){
    var checkflag = false;
    var reqId = document.forms['intermemosForm'].reqId;
    if(reqId==null) return false;
    if (reqId.length!=null) {
        for (i = 0; i < reqId.length; i++) {
            if (reqId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = reqId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delIntermemo.do','intermemoList',document.forms['intermemosForm'],null);
    reqId=null;
    return false;
}
function delIntermemoMaterial(){
    var checkflag = false;
    var detId = document.forms['intermemoForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delIntermemoDetail.do',null,document.forms['intermemoForm'],function(data){
        setAjaxData(data,'listIntermemoMaterialDataDiv');
    });
    detId=null;
    return false;
}
function searchIntermemo(){
    var checkflag = true;
    var form=document.forms['searchSimpleRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchIntermemo.do","intermemoList",form,null);
    form=null;
    return false;
}
function searchAdvIntermemoForm(){
    callAjax('searchAdvIntermemoForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvIntermemo(){
    callAjax('searchAdvIntermemo.do',null,document.forms['searchIntermemoForm'],getSearchAdvIntermemoData);
    hidePopupForm();
    return false;
}
function getSearchAdvIntermemoData(data){
    setAjaxData(data,'intermemoList');
}
//CONTRACT
function loadContractList(params){
    callAjax('contractList.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        var contractKindHidden=document.getElementById("contractKindHidden");
        if(contractKindHidden.value=="contract") loadContracts(params);
        else if(contractKindHidden.value=="contractPrinciple") loadContractPrinciples(params);
        else if(contractKindHidden.value=="order") loadOrders(params);
        contractKindHidden=null;
    });
    return false;
}
function loadContractListSort(params){
    var contractKindHidden=document.getElementById("contractKindHidden");
    if(contractKindHidden.value=="contract") loadContracts(params);
    else if(contractKindHidden.value=="contractPrinciple") loadContractPrinciples(params);
    else if(contractKindHidden.value=="order") loadOrders(params);
    contractKindHidden=null;
    return false;
}
function getContractListData(data){
    setAjaxData(data,'ajaxContent');
    var contractKindHidden=document.getElementById("contractKindHidden");
    if(contractKindHidden.value=="contract") loadContracts();
    else if(contractKindHidden.value=="contractPrinciple") loadContractPrinciples();
    else if(contractKindHidden.value=="order") loadOrders();
    contractKindHidden=null;
    return false;
}
function loadContracts(params){
    //phan trang bi sai : callAjaxExChild("contracts.do","contractList",params);
    var kind=document.getElementById("kind").value;
    callAjaxExChild("searchContract.do?kind="+kind,"contractList",params);
    return false;
}
function contractForm(kind,conId,tenId,isAutoCreate,appKind,matIds,reqDetailIds,appId,adjId){
    if(conId!=null){
        if(kind==1){//hard code for contract kind = contract
            getAppendixTabs(function(){
                loadContractForm(kind,conId,tenId,isAutoCreate,appKind,matIds,reqDetailIds,appId,adjId);
            });
        }else{
            if(kind==null) kind=0;
            if(kind==2){//hard code for contract kind = contract                
            getAdjustmentTabs2(function(){  
                if(conId!=null){
                setContractId(conId);} 
            var url="contractForm.do?kind="+kind;            
            if(conId!=null) url=url+"&conId="+conId;
            if(tenId!=null) url=url+"&tenId="+tenId;
            callAjax(url,null,null,function(data){
                setAjaxData(data,'contractdetail');
                if(conId!=null){
                    if(tenId==null) tenId=document.forms['contractForm'].tenId;
                    var venId=document.forms['contractForm'].venId;
                    if(tenId!=null) loadMaterialInTenderPlan(conId,tenId.value,venId.value,0);
                    loadAttachFileSystem(4,conId,'divAttachFileSystem');
                    tenId=null;
                    venId=null;
                    
                    var total=document.forms['contractForm'].total;
                    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
                    var sumVAT=document.forms['contractForm'].sumVAT;
                    var transport=document.forms['contractForm'].transport;
                    var otherFee=document.forms['contractForm'].otherFee;
                    var detTotal=document.forms['contractForm'].detTotal;
                    var vat=document.forms['contractForm'].vat;
                    var price=document.forms['contractForm'].price;
                    var quantity=document.forms['contractForm'].quantity;
                    var currency=document.forms['contractForm'].currency;
                    if(currency!=null) currency=currency.value;
                    if(price!=null){
                        if (price.length!=null) {
                            for (i = 0; i < price.length; i++) {
                                tryNumberFormat(vat[i],currency);
                                tryNumberFormat(price[i],currency);
                                if(detTotal!=null) tryNumberFormat(detTotal[i],currency);
//                                if(quantity!=null) tryNumberFormat(quantity[i],currency);
                                if(quantity!=null) tryNumberFormat(quantity[i],"USD");
                            }
                        } else{
                            tryNumberFormat(vat,currency);
                            tryNumberFormat(price,currency);
                            if(detTotal!=null) tryNumberFormat(detTotal,currency);
//                            if(quantity!=null) tryNumberFormat(quantity,currency);
                            if(quantity!=null) tryNumberFormat(quantity,"USD");
                        }
                    }
                    if(transport!=null) tryNumberFormat(transport,currency);
                    if(otherFee!=null) tryNumberFormat(otherFee,currency);
                    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT,currency);
                    if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
                    if(total!=null) tryNumberFormat(total,currency);
                    vat=null;
                    price=null;
                    quantity=null;
                    total=null;
                    detTotal=null;
                    transport=null;
                    otherFee=null;
                    sumVAT=null;
                    totalNotVAT=null;
                }
            });
            });            
            }
            if(kind==3){//hard code for contract kind = contract                
            getAdjustmentTabs3(function(){  
                if(conId!=null){
                setContractId(conId);} 
            var url="contractForm.do?kind="+kind;            
            if(conId!=null) url=url+"&conId="+conId;
            if(tenId!=null) url=url+"&tenId="+tenId;
            callAjax(url,null,null,function(data){
                setAjaxData(data,'contractdetail');
                //var orderMaterialSource=document.forms['contractForm'].orderMaterialSource;
                //orderMaterialSource.value="other";
                //setAjaxData('','listTenderPlanMaterialDataSpan');
                //callAjax('orderMaterialSource.do?kind=3','orderSourceTr',null,null);
                if(conId!=null){
                    if(tenId==null) tenId=document.forms['contractForm'].tenId;
                    var venId=document.forms['contractForm'].venId;
                    if(tenId!=null) loadMaterialInTenderPlan(conId,tenId.value,venId.value,0);
                    loadAttachFileSystem(4,conId,'divAttachFileSystem');
                    tenId=null;
                    venId=null;
                    
                    var total=document.forms['contractForm'].total;
                    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
                    var sumVAT=document.forms['contractForm'].sumVAT;
                    var transport=document.forms['contractForm'].transport;
                    var otherFee=document.forms['contractForm'].otherFee;
                    var detTotal=document.forms['contractForm'].detTotal;
                    var vat=document.forms['contractForm'].vat;
                    var price=document.forms['contractForm'].price;
                    var quantity=document.forms['contractForm'].quantity;
                    var currency=document.forms['contractForm'].currency;
                    if(currency!=null) currency=currency.value;
                    if(price!=null){
                        if (price.length!=null) {
                            for (i = 0; i < price.length; i++) {
                                tryNumberFormat(vat[i],currency);
                                tryNumberFormat(price[i],currency);
                                if(detTotal!=null) tryNumberFormat(detTotal[i],currency);
//                                if(quantity!=null) tryNumberFormat(quantity[i],currency);
                                if(quantity!=null) tryNumberFormat(quantity[i],"USD");
                            }
                        } else{
                            tryNumberFormat(vat,currency);
                            tryNumberFormat(price,currency);
                            if(detTotal!=null) tryNumberFormat(detTotal,currency);
//                            if(quantity!=null) tryNumberFormat(quantity,currency);
                            if(quantity!=null) tryNumberFormat(quantity,"USD");
                        }
                    }
                    if(transport!=null) tryNumberFormat(transport,currency);
                    if(otherFee!=null) tryNumberFormat(otherFee,currency);
                    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT,currency);
                    if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
                    if(total!=null) tryNumberFormat(total,currency);
                    vat=null;
                    price=null;
                    quantity=null;
                    total=null;
                    detTotal=null;
                    transport=null;
                    otherFee=null;
                    sumVAT=null;
                    totalNotVAT=null;
                }
            });
            });            
            }
            if(kind==5){//hard code for contract kind = contract                
            getAdjustmentTabs5(function(){  
                if(conId!=null){
                setContractId(conId);}   
            var url="contractForm.do?kind="+kind;            
            if(conId!=null) url=url+"&conId="+conId;
            if(tenId!=null) url=url+"&tenId="+tenId;
            callAjax(url,null,null,function(data){
                setAjaxData(data,'contractdetail');
                if(conId!=null){
                    if(tenId==null) tenId=document.forms['contractForm'].tenId;
                    var venId=document.forms['contractForm'].venId;
                    if(tenId!=null) loadMaterialInTenderPlan(conId,tenId.value,venId.value,0);
                    loadAttachFileSystem(4,conId,'divAttachFileSystem');
                    tenId=null;
                    venId=null;
                    
                    var total=document.forms['contractForm'].total;
                    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
                    var sumVAT=document.forms['contractForm'].sumVAT;
                    var transport=document.forms['contractForm'].transport;
                    var otherFee=document.forms['contractForm'].otherFee;
                    var detTotal=document.forms['contractForm'].detTotal;
                    var vat=document.forms['contractForm'].vat;
                    var price=document.forms['contractForm'].price;
                    var quantity=document.forms['contractForm'].quantity;
                    var currency=document.forms['contractForm'].currency;
                    if(currency!=null) currency=currency.value;
                    if(price!=null){
                        if (price.length!=null) {
                            for (i = 0; i < price.length; i++) {
                                tryNumberFormat(vat[i],currency);
                                tryNumberFormat(price[i],currency);
                                if(detTotal!=null) tryNumberFormat(detTotal[i],currency);
//                                if(quantity!=null) tryNumberFormat(quantity[i],currency);
                                if(quantity!=null) tryNumberFormat(quantity[i],"USD");
                            }
                        } else{
                            tryNumberFormat(vat,currency);
                            tryNumberFormat(price,currency);
                            if(detTotal!=null) tryNumberFormat(detTotal,currency);
//                            if(quantity!=null) tryNumberFormat(quantity,currency);
                            if(quantity!=null) tryNumberFormat(quantity,"USD");
                        }
                    }
                    var amount=document.forms['contractForm'].amount;
                    if(amount!=null){
                        if (amount.length!=null) {
                            for (i = 0; i < amount.length; i++) {
                                tryNumberFormat(amount[i],currency);
                            }
                        } else{
                            tryNumberFormat(amount,currency);
                        }
                    }
                    
                    if(transport!=null) tryNumberFormat(transport,currency);
                    if(otherFee!=null) tryNumberFormat(otherFee,currency);
                    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT,currency);
                    if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
                    if(total!=null) tryNumberFormat(total,currency);
                    vat=null;
                    price=null;
                    quantity=null;
                    total=null;
                    detTotal=null;
                    transport=null;
                    otherFee=null;
                    sumVAT=null;
                    totalNotVAT=null;
                    amount=null;
                }
            });
            });            
            }
            
        }
    }else{
        if(kind==null) kind=0;
        var url="contractForm.do?kind="+kind;
        if(tenId!=null) url=url+"&tenId="+tenId;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            if(kind==5){//hard code for contract kind = delivery request
                var list=document.forms['contractForm'].venId;
                if(list!=null) deliveryRequestVendorChanged(list);
                list=null;
            }
            if(conId!=null){
                var tenId=document.forms['contractForm'].tenId;
                var venId=document.forms['contractForm'].venId;
                loadMaterialInTenderPlan(conId,tenId.value,venId.value,0);
                loadAttachFileSystem(4,conId,'divAttachFileSystem');
                tenId=null;
                venId=null;
            }
        });
    }
    return false;
}
function loadContractForm(kind,conId,tenId,isAutoCreate,appKind,matIds,reqDetailIds,appId,adjId){
    if(kind==null) kind=0;
    var url="contractForm.do?kind="+kind;
    if(conId!=null) url=url+"&conId="+conId;
    if(tenId!=null) url=url+"&tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'contractdetail');
        if(conId!=null){
            setContractId(document.forms['contractForm'].conId.value);
            if(isAutoCreate==true){
                createAutoAppendix(appKind,matIds,reqDetailIds,conId);
            //  createAutoAdjustment(appKind,matIds,reqDetailIds,conId);
                if(appId!=null) appendixForm(appKind,appId);
                if(adjId!=null) adjustmentForm(appKind,adjId);
            }
            //showhide2('listContractProjectDataSpan', true);
            callAjax('getContractProject.do?conId='+conId,null,null,function(data){
                setAjaxData(data,'listContractProjectDataSpan');
                var list=document.forms['contractForm'].project;
                if(list!=null){
                    if(list.length!=null){
                        var total=reformatNumberMoneyString(document.forms['contractForm'].total.value);
                        if(list.length>0) total=total/list.length;
                        total=reformatNumberMoneyString(total+'');
                        for (i=0;i<list.length;i++){
                            setSelectedContractCostProject(list.options[i].value,list.options[i].text,total);
                        }
                    }
                }
            });
            var tenId=document.forms['contractForm'].tenId;
            var venId=document.forms['contractForm'].venId;
            showhide2('contractFormTitle',true);
            loadMaterialInTenderPlan(conId,tenId.value,venId.value,0);
            loadAttachFileSystem(4,conId,'divAttachFileSystem');
            tenId=null;
            venId=null;

            
            var total=document.forms['contractForm'].total;
            var totalNotVAT=document.forms['contractForm'].totalNotVAT;
            var sumVAT=document.forms['contractForm'].sumVAT;
            var transport=document.forms['contractForm'].transport;
            var otherFee=document.forms['contractForm'].otherFee;
            var detTotal=document.forms['contractForm'].detTotal;
            var vat=document.forms['contractForm'].vat;
            var price=document.forms['contractForm'].price;
            var quantity=document.forms['contractForm'].quantity;
            var currency=document.forms['contractForm'].currency;
            if(currency!=null) currency=currency.value;
            if(price!=null){
                if (price.length!=null) {
                    for (i = 0; i < detTotal.length; i++) {
                        tryNumberFormat(vat[i],currency);
                        tryNumberFormat(price[i],currency);
                        if(detTotal!=null) tryNumberFormat(detTotal[i],currency);
                        if(quantity!=null) tryNumberFormat(quantity[i],currency);
                    }
                } else{
                    tryNumberFormat(vat,currency);
                    tryNumberFormat(price,currency);
                    if(detTotal!=null) tryNumberFormat(detTotal,currency);
                    if(quantity!=null) tryNumberFormat(quantity,currency);
                }
            }
            if(transport!=null) tryNumberFormat(transport,currency);
            if(otherFee!=null) tryNumberFormat(otherFee,currency);
            if(totalNotVAT!=null) tryNumberFormat(totalNotVAT,currency);
            if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
            if(total!=null) tryNumberFormat(total,currency);
            vat=null;
            price=null;
            quantity=null;
            total=null;
            detTotal=null;
            transport=null;
            otherFee=null;
            sumVAT=null;
            totalNotVAT=null;
            
            var cost=document.forms['contractForm'].cost;
            if(cost!=null){
                if (cost.length!=null) {
                    for (i = 0; i < cost.length; i++) {
                        tryNumberFormat(cost[i],currency);
                    }
                } else{
                    tryNumberFormat(cost,currency);
                }
                cost=null;
            }
            
            var amount=document.forms['contractForm'].amount;
            if(amount!=null){
                if (amount.length!=null) {
                    for (i = 0; i < amount.length; i++) {
                        tryNumberFormat(amount[i],currency);
                    }
                } else{
                    tryNumberFormat(amount,currency);
                }
                amount=null;
            }
        }
    });
    return false;
}
function createDn(whichUse,conId){
    var form=document.forms['contractForm'];
    //var conId=document.forms['contractForm'].conId.value;
    var ids = '0';
    var detId = form.detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                ids+=','+detId[i].value;
            }
        }
    } else 
        if (detId.checked == true) {
                ids+=','+detId.value;
            }
    //alert(detId.checked)
    if (ids!='0'){
        callAjax('dnForm.do?kind=1',null,null,function(data){
            setAjaxData(data,'ajaxContent');             
            document.forms['dnForm'].whichUse.value = whichUse;
            document.forms['dnForm'].conId.value = conId;
            whichUseChangedDn(document.forms['dnForm'].whichUse,conId);
            url='listDnDetail.do?kind=1';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listDnMaterialDataDiv');
                setSelectedDnMaterial(ids.substring(2),conId);
            });
        });
    }
    matId=null;
    form=null;
    return false;
}
function createDnAdjustment(whichUse,conId){
    var form=document.forms['adjustmentForm'];
    //var conId=document.forms['contractForm'].conId.value;
    var ids = '0';
    var detId = form.detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                ids+=','+detId[i].value;
            }
        }
    } else 
        if (detId.checked == true) {
                ids+=','+detId.value;
            }
    //alert(detId.checked)
    if (ids!='0'){
        callAjax('dnForm.do?kind=1',null,null,function(data){
            setAjaxData(data,'ajaxContent');             
            document.forms['dnForm'].whichUse.value = whichUse;
            document.forms['dnForm'].conId.value = conId;
            whichUseChangedDn(document.forms['dnForm'].whichUse,conId);
            url='listDnDetail.do?kind=1';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listDnMaterialDataDiv');
                setSelectedDnAdjustmentMaterial(ids.substring(2),conId);
            });
        });
    }
    matId=null;
    form=null;
    return false;
}
function createDnAppendix(whichUse,conId){
    var form=document.forms['appendixForm'];
    //var conId=document.forms['contractForm'].conId.value;
    var ids = '0';
    var detId = form.detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                ids+=','+detId[i].value;
            }
        }
    } else 
        if (detId.checked == true) {
                ids+=','+detId.value;
            }
    //alert(ids)
    if (ids!='0'){
        callAjax('dnForm.do?kind=1',null,null,function(data){
            setAjaxData(data,'ajaxContent');             
            document.forms['dnForm'].whichUse.value = whichUse;
            document.forms['dnForm'].conId.value = conId;
            whichUseChangedDn(document.forms['dnForm'].whichUse,conId);
            url='listDnDetail.do?kind=1';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listDnMaterialDataDiv');
                setSelectedDnMaterial(ids.substring(2),conId);
            });
        });
    }
    matId=null;
    form=null;
    return false;
}
function createDn4(whichUse,conId){
    var form=document.forms['contractForm'];
    //var conId=document.forms['contractForm'].conId.value;
    var ids = '0';
    var detId = form.detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                ids+=','+detId[i].value;
            }
        }
    } else
        if (detId.checked == true) {
                ids+=','+detId.value;
            }
    //alert(ids)
    if (ids!='0'){
        callAjax('dnForm.do?kind=1',null,null,function(data){
            setAjaxData(data,'ajaxContent');
            document.forms['dnForm'].whichUse.value = whichUse;
            if(document.forms['dnForm'].conId!=null) document.forms['dnForm'].conId.value = conId;
            if(document.forms['dnForm'].drId!=null) document.forms['dnForm'].drId.value = conId;
            whichUseChangedDn(document.forms['dnForm'].whichUse,conId);
            url='listDnDetail.do?kind=1';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listDnMaterialDataDiv');
                setSelectedDnMaterial(ids.substring(2),conId);
            });
        });
    }
    matId=null;
    form=null;
    return false;
}
function getAppendixTabs(handle){
    callAjax('appendixTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addAppendixTabs();
        handle();
    });
}
function addAppendixTabs(){
    displayTabs("appendixTabs","appendixTabChild",appendixTabHandle);
}
function appendixTabHandle(child){
    if(child.isLoaded=='true') return true;
    if(child.id=='appendixlist') loadAppendixList();
    if(child.id=='adjustmentlist') loadAdjustmentList();
    child.isLoaded='true';
    child=null;
    return false;
}
function getAdjustmentTabs(handle){
    callAjax('adjustmentTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addAdjustmentTabs();
        handle();
    });
}
function getAdjustmentTabs2(handle){
    callAjax('adjustmentTabs2.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addAdjustmentTabs();
        handle();
    });
}
function getAdjustmentTabs3(handle){
    callAjax('adjustmentTabs3.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addAdjustmentTabs();
        handle();
    });
}
function getAdjustmentTabs5(handle){
    callAjax('adjustmentTabs5.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addAdjustmentTabs();
        handle();
    });
}
function addAdjustmentTabs(){
    displayTabs("adjustmentTabs","adjustmentTabChild",adjustmentTabHandle);
}
function adjustmentTabHandle(child){
    if(child.isLoaded=='true') return true;
    if(child.id=='adjustmentlist') loadAdjustmentList();
    child.isLoaded='true';
    child=null;
    return false;
}
function getContractId(){
    var con=document.getElementById('conIdHidden');
    var conId='';
    if(con!=null){
        conId=con.value;
        con=null;
    }
    return conId;
}
function setContractId(conId){
    var contract=document.getElementById('conIdHidden');
    if(contract!=null){
        contract.value=conId;
        contract=null;
    }
}
function loadAppendixList(params){
    var conId=getContractId();
    if(conId=='') return true;
    callAjaxEx('appendixList.do','appendixlist','appendixs.do?conId='+conId,'appendixList',params);
    return false;
}
function getAppendixListData(data){
    setAjaxData(data,'appendixlist');
    loadAppendixs();
    return false;
}
function saveAppendix(){
    if(scriptFunction=="saveAppendix()") return false;
    var contractNumber = document.forms['appendixForm'].contractNumber;
    if(contractNumber.value==''){
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o s\u1ED1 H\u0110!");
        contractNumber.focus();
        contractNumber=null;
        return false;
    }
    contractNumber=null;
    var followEmp = document.forms['appendixForm'].followEmp;
    if(followEmp!=null){
        if(followEmp.selectedIndex==-1){
            alert("Vui l\u00F2ng ch\u1ECDn nh\u00E2n vi\u00EAn qu\u1EA3n l\u00FD h\u1EE3p \u0111\u1ED3ng");
            followEmp=null;
            return false;
        }
        followEmp=null;
    }
//    var expireDate = document.forms['appendixForm'].expireDate;
//    if(expireDate.value==''){
//        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y hi\u1EC7u l\u1EF1c");
//        expireDate.focus();
//        expireDate=null;
//        return false;
//    }
//    expireDate=null;
    
    var total=document.forms['appendixForm'].total;
    var totalNotVAT=document.forms['appendixForm'].totalNotVAT;
    var sumVAT=document.forms['appendixForm'].sumVAT;
    var otherFee=document.forms['appendixForm'].otherFee;
    var transport=document.forms['appendixForm'].transport;
    var detTotal=document.forms['appendixForm'].detTotal;
    var vat=document.forms['appendixForm'].vat;
    var price=document.forms['appendixForm'].price;
    var quantity=document.forms['appendixForm'].quantity;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < price.length; i++) {
                reformatNumberMoney(vat[i]);
                reformatNumberMoney(price[i]);
                reformatNumberMoney(detTotal[i]);
                reformatNumberMoney(quantity[i]);
            }
        } else{
            reformatNumberMoney(vat);
            reformatNumberMoney(price);
            reformatNumberMoney(detTotal);
            reformatNumberMoney(quantity);
        }
    }
    if(otherFee!=null) reformatNumberMoney(otherFee);
    if(transport!=null) reformatNumberMoney(transport);
    if(totalNotVAT!=null) reformatNumberMoney(totalNotVAT);
    if(sumVAT!=null) reformatNumberMoney(sumVAT);
    if(total!=null) reformatNumberMoney(total);
    vat=null;
    price=null;
    quantity=null;
    total=null;
    detTotal=null;
    otherFee=null;
    sumVAT=null;
    totalNotVAT=null;
    
//    callAjaxCheckError("saveContract.do",null,document.forms['appendixForm'],getAppendixListData,'appendixErrorMessageDiv');
    scriptFunction="saveAppendix()";
    callAjaxCheckError("saveContract.do",null,document.forms['appendixForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        var conId=document.forms['appendixForm'].conId.value;
        var kind=document.forms['appendixForm'].kind.value;
        appendixForm(kind,conId);
    });
    
    return false;
}
function loadAppendixs(){
    var conId=getContractId();
    if(conId=='') return true;
    callAjax('appendixs.do?conId='+conId,"appendixList",null,null);
    return false;
}
function appendixForm(kind,conId){
    if(kind==null) kind=0;
    var url="contractForm.do?kind="+kind;
    if(conId!=null) url=url+"&conId="+conId;
    var parentId=getContractId();
    if(parentId!='') url=url+"&parentId="+parentId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'appendixlist');        
        callAjax('orderMaterialListInContract.do?conId='+parentId,'listAppendixMaterialDataSpan',null,null);
        callAjax('getVendorByContract.do?name=1&conId='+parentId,'appendixVendorName',null,null);
        loadAttachFileSystem(4,conId,'divAttachFileSystem1');
        var total=document.forms['appendixForm'].total;
        var totalNotVAT=document.forms['appendixForm'].totalNotVAT;
        var sumVAT=document.forms['appendixForm'].sumVAT;
        var otherFee=document.forms['appendixForm'].otherFee;
        var transport=document.forms['appendixForm'].transport;
        var detTotal=document.forms['appendixForm'].detTotal;
        var vat=document.forms['appendixForm'].vat;
        var price=document.forms['appendixForm'].price;
        var currency=document.forms['appendixForm'].currency;
        if(currency!=null) currency=currency.value;

        var quantity=document.forms['appendixForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(vat[i],currency);
                    tryNumberFormat(price[i],currency);
                    tryNumberFormat(detTotal[i],currency);
                    tryNumberFormat(quantity[i],currency);
                }
            } else{
                tryNumberFormat(vat,currency);
                tryNumberFormat(price,currency);
                tryNumberFormat(detTotal,currency);
                tryNumberFormat(quantity,currency);
            }
        }
        if(otherFee!=null) tryNumberFormat(otherFee,currency);
        if(transport!=null) tryNumberFormat(transport,currency);
        if(totalNotVAT!=null) tryNumberFormat(totalNotVAT,currency);
        if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
        if(total!=null) tryNumberFormat(total,currency);
        vat=null;
        price=null;
        quantity=null;
        total=null;
        detTotal=null;
        otherFee=null;
        sumVAT=null;
        totalNotVAT=null;
    });
    return false;
}
function createAutoAppendix(kind,matIds,reqDetailIds,conId){
    dijit.byId('appendixTabs').selectChild('appendixlist');
    if(kind==null) kind=0;
    var url="contractForm.do?kind="+kind+"&conIds="+conId;
    var parentId=getContractId();
    if(parentId!='') url=url+"&parentId="+parentId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'appendixlist');
        if(matIds!=null){
            document.forms['appendixForm'].reqDetailIds.value=reqDetailIds;
            url='orderMaterialListInContract.do?conId='+parentId+'&matIds='+matIds;
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listAppendixMaterialDataSpan');
                var total=document.forms['appendixForm'].total;
                var totalNotVAT=document.forms['appendixForm'].totalNotVAT;
                var sumVAT=document.forms['appendixForm'].sumVAT;
                var otherFee=document.forms['appendixForm'].otherFee;
                var detTotal=document.forms['appendixForm'].detTotal;
                var vat=document.forms['appendixForm'].vat;
                var price=document.forms['appendixForm'].price;

                var quantity=document.forms['appendixForm'].quantity;
                if(price!=null){
                    if (price.length!=null) {
                        for (i = 0; i < detTotal.length; i++) {
                            tryNumberFormat(vat[i]);
                            tryNumberFormat(price[i]);
                            tryNumberFormat(detTotal[i]);
                            tryNumberFormat(quantity[i]);
                        }
                    } else{
                        tryNumberFormat(vat);
                        tryNumberFormat(price);
                        tryNumberFormat(detTotal);
                        tryNumberFormat(quantity);
                    }
                }
                if(otherFee!=null) tryNumberFormat(otherFee);
                if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
                if(sumVAT!=null) tryNumberFormat(sumVAT);
                if(total!=null) tryNumberFormat(total);
                vat=null;
                price=null;
                quantity=null;
                total=null;
                detTotal=null;
                otherFee=null;
                sumVAT=null;
                totalNotVAT=null;

                var list=document.forms['appendixForm'].material;
                var ids='';
                var conIds='';
                for(i=0;i<list.options.length;i++){
                    ids+=','+list.options[i].value;
                    conIds+=','+parentId;
                }
                if(ids!=''){
                    ids=ids.substring(1);
                    conIds=conIds.substring(1);
                }
                setSelectedAppendixMaterial(ids,conIds);
            });
            callAjax('getVendorByContract.do?name=1&conId='+parentId,'appendixVendorName',null,null);
        }
    });
    return false;
}
function appendixContractChanged(list){
    if(list.selectedIndex==-1) return false;
    callAjax('getVendorByContract.do?name=1&conId='+list.options[list.selectedIndex].value,'appendixVendorName',null,null);
    callAjax('orderMaterialListInContract.do?conId='+list.options[list.selectedIndex].value,'listAppendixMaterialDataSpan',null,null);
    list=null;
    removeAppendixMaterial();
    return false;
}
function removeAppendixMaterial(){
    var selId=document.forms['appendixForm'].matId;
    if(selId==null) return false;
    var tbl=document.getElementById('appendixDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
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
function setSelectedAppendixMaterial(matIds,conIds){
    var url='appendixMaterial.do?conId=';
    var contract=document.forms['appendixForm'].parentId;
    var list=document.forms['appendixForm'].material;
    if(list.selectedIndex==-1) return false;
    var materialId=null;
    if(matIds!=null){
        materialId=matIds;
        url+=conIds;
    }
    else{
        if(contract.selectedIndex!=null) url+=contract.options[contract.selectedIndex].value;
        else url+=contract.value;
        materialId=list.options[list.selectedIndex].value;
        var matId = document.forms['appendixForm'].matId;
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
    }
    url+='&matId='+materialId;
    url+='&reqDetailIds='+document.forms['appendixForm'].reqDetailIds.value;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'appendixMaterialHideDiv');
        var matTable=document.getElementById('orderSourceMaterialTable');
        var detTable=document.getElementById('appendixDetailTable');
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
        
        var detTotal=document.forms['appendixForm'].detTotal;
        var vat=document.forms['appendixForm'].vat;
        var price=document.forms['appendixForm'].price;
        var quantity=document.forms['appendixForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(vat[i]);
                    tryNumberFormat(price[i]);
                    tryNumberFormat(detTotal[i]);
                    tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat);
                tryNumberFormat(price);
                tryNumberFormat(detTotal);
                tryNumberFormat(quantity);
            }
        }
        vat=null;
        price=null;
        quantity=null;
        detTotal=null;
        
        caculateAppdendix();
    });
    matId=null;
    list=null;
    contract=null;
    return false;
}

function caculateAppdendix(){
    var total=document.forms['appendixForm'].total;
    if(total==null) return;
    var transport=document.forms['appendixForm'].transport;
    var otherFee=document.forms['appendixForm'].otherFee;
    var detTotal=document.forms['appendixForm'].detTotal;
    var totalNotVAT=document.forms['appendixForm'].totalNotVAT;
    var sumVAT=document.forms['appendixForm'].sumVAT;
    var sum=0;
    if(detTotal!=null){
        var vat=document.forms['appendixForm'].vat;
        var price=document.forms['appendixForm'].price;
        var quantity=document.forms['appendixForm'].quantity;
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
            }
        } else sum+=reformatNumberMoneyString(detTotal.value)*1;
        var totalvat=0;
        if (vat.length!=null) {
            for (i = 0; i < vat.length; i++) {
                totalvat+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value)*reformatNumberMoneyString(vat[i].value)*0.01;
            }
        } else totalvat+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*reformatNumberMoneyString(vat.value)*0.01;
        vat=null;
        price=null;
        quantity=null;
        sumVAT.value=totalvat;
        totalNotVAT.value=sum-totalvat;
    }
    
    if(transport!=null) sum+=reformatNumberMoneyString(transport.value)*1;
    if(otherFee!=null) sum+=reformatNumberMoneyString(otherFee.value)*1;
    total.value=sum;
    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
    if(sumVAT!=null) tryNumberFormat(sumVAT);
    if(total!=null) tryNumberFormat(total);
    total=null;
    transport=null;
    otherFee=null;
    detTotal=null;
    sumVAT=null;
    totalNotVAT=null;
    return false;
}
function caculateAppendixDetail(matId){
    var quantity=document.getElementById("det2quantity"+matId);
    var price=document.getElementById("det2price"+matId);
    var vat=document.getElementById("det2vat"+matId);
    var detTotal=document.getElementById("det2Total"+matId);
    detTotal.value=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*(100+reformatNumberMoneyString(vat.value)*1)*0.01;
    tryNumberFormat(detTotal);
    quantity=null;
    price=null;
    vat=null;
    detTotal=null;
    caculateAppdendix();
    return false;
}
function delAppendixDetails(){
    var checkflag = false;
    var detId = document.forms['appendixForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delContractDetail.do',null,document.forms['appendixForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('appendixDetailTable');
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
                caculateAppdendix();
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delAppendixs(kind){
    var checkflag = false;
    var conId = document.forms['appendixsForm'].conId;
    if(conId==null) return false;
    if (conId.length!=null) {
        for (i = 0; i < conId.length; i++) {
            if (conId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = conId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delContract.do?kind='+kind,null,document.forms['appendixsForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else setAjaxData(data,'appendixList');
    });
    conId=null;
    return false;
}

function loadAdjustmentList(params){
    var conId=getContractId();
    if(conId=='') return true;
    callAjaxEx('adjustmentList.do','adjustmentlist','adjustments.do?conId='+conId,'adjustmentList',params);
    return false;
}
function getAdjustmentListData(data){
    setAjaxData(data,'adjustmentlist');
    loadAdjustments();
    return false;
}
function saveAdjustment(){
    if(scriptFunction=="saveAdjustment()") return false;
    var contractNumber = document.forms['adjustmentForm'].contractNumber;
    if(contractNumber.value==''){
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o s\u1ED1 H\u0110!");
        contractNumber.focus();
        contractNumber=null;
        return false;
    }
    contractNumber=null;
    var followEmp = document.forms['adjustmentForm'].followEmp;
    if(followEmp!=null){
        if(followEmp.selectedIndex==-1){
            alert("Vui l\u00F2ng ch\u1ECDn nh\u00E2n vi\u00EAn qu\u1EA3n l\u00FD h\u1EE3p \u0111\u1ED3ng");
            followEmp=null;
            return false;
        }
        followEmp=null;
    }
//    var expireDate = document.forms['adjustmentForm'].expireDate;
//    if(expireDate.value==''){
//        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y hi\u1EC7u l\u1EF1c");
//        expireDate.focus();
//        expireDate=null;
//        return false;
//    }
//    expireDate=null;
    
    var total=document.forms['adjustmentForm'].total;
    var totalNotVAT=document.forms['adjustmentForm'].totalNotVAT;
    var sumVAT=document.forms['adjustmentForm'].sumVAT;
    var otherFee=document.forms['adjustmentForm'].otherFee;
    var detTotal=document.forms['adjustmentForm'].detTotal;
    var vat=document.forms['adjustmentForm'].vat;
    var price=document.forms['adjustmentForm'].price;
    var quantity=document.forms['adjustmentForm'].quantity;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < price.length; i++) {
                reformatNumberMoney(vat[i]);
                reformatNumberMoney(price[i]);
                reformatNumberMoney(detTotal[i]);
                reformatNumberMoney(quantity[i]);
            }
        } else{
            reformatNumberMoney(vat);
            reformatNumberMoney(price);
            reformatNumberMoney(detTotal);
            reformatNumberMoney(quantity);
        }
    }
    if(otherFee!=null) reformatNumberMoney(otherFee);
    if(totalNotVAT!=null) reformatNumberMoney(totalNotVAT);
    if(sumVAT!=null) reformatNumberMoney(sumVAT);
    if(total!=null) reformatNumberMoney(total);
    vat=null;
    price=null;
    quantity=null;
    total=null;
    detTotal=null;
    otherFee=null;
    sumVAT=null;
    totalNotVAT=null;
    
//    callAjaxCheckError("saveContract.do",null,document.forms['adjustmentForm'],getAdjustmentListData,'adjustmentErrorMessageDiv');
    scriptFunction="saveAdjustment()";
    callAjaxCheckError("saveContract.do?kind="+document.forms['adjustmentForm'].kind.value,null,document.forms['adjustmentForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        var conId=document.forms['adjustmentForm'].conId.value;
        var kind=document.forms['adjustmentForm'].kind.value;
        var venId=document.forms['adjustmentForm'].venId.value;
        adjustmentForm(kind,conId,venId);
    },"adjustmentErrorMessageDiv");
    
    return false;
}
function loadAdjustments(){
    var conId=getContractId();
    if(conId=='') return true;
    callAjax('adjustments.do?conId='+conId,"adjustmentList",null,null);
    return false;
}
function adjustmentForm(kind,conId,venId){
    if(kind==null) kind=0;
    var url="contractForm.do?kind="+kind;
    if(conId!=null) url=url+"&conId="+conId;
    var parentId=getContractId();
    if(parentId!='') url=url+"&parentId="+parentId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'adjustmentlist');   
        if(document.forms['adjustmentForm'].conId.value > 0)  conId=document.forms['adjustmentForm'].conId.value;
        callAjax('orderMaterialListInContract.do?conId='+parentId+'&kind='+kind,'listAdjustmentMaterialDataSpan',null,null);
        if(conId!=null){
            callAjax('getVendorByContract.do?name=1&conId='+conId+'&contractNumber='+document.forms['adjustmentForm'].contractNumber.value+'&venId='+venId,'adjustmentVendorName',null,null);
        }else{
            callAjax('getVendorByContract.do?name=1&conId='+parentId+'&contractNumber='+document.forms['adjustmentForm'].contractNumber.value,'adjustmentVendorName',null,null);
        }        
        loadAttachFileSystem(4,conId,'divAttachFileSystem2');
        var total=document.forms['adjustmentForm'].total;
        var totalNotVAT=document.forms['adjustmentForm'].totalNotVAT;
        var sumVAT=document.forms['adjustmentForm'].sumVAT;
        var otherFee=document.forms['adjustmentForm'].otherFee;
        var detTotal=document.forms['adjustmentForm'].detTotal;
        var vat=document.forms['adjustmentForm'].vat;
        var price=document.forms['adjustmentForm'].price;
        var price1=document.forms['adjustmentForm'].price1;
        var currency=document.forms['adjustmentForm'].currency;
        var quantity=document.forms['adjustmentForm'].quantity;
        if(currency!=null) currency=currency.value;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    if(vat!=null) tryNumberFormat(vat[i],currency);
                    if(price!=null) tryNumberFormat(price[i],currency);
                   // if(price1!=null) tryNumberFormat(price1[i],currency);
                    if(detTotal!=null) tryNumberFormat(detTotal[i],currency);
                    if(quantity!=null) tryNumberFormat(quantity[i],currency);
                }
            } else{
                if(vat!=null) tryNumberFormat(vat,currency);
                if(price!=null) tryNumberFormat(price,currency);
                //if(price1!=null) tryNumberFormat(price1[i],currency);
                if(detTotal!=null) tryNumberFormat(detTotal,currency);
                if(quantity!=null) tryNumberFormat(quantity,currency);
            }
        }
        if(otherFee!=null) tryNumberFormat(otherFee,currency);
        if(totalNotVAT!=null) tryNumberFormat(totalNotVAT,currency);
        if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
        if(total!=null) tryNumberFormat(total,currency);
        if(price1!=null) tryNumberFormat(price1,currency);
        vat=null;
        price=null;
        price1=null;
        quantity=null;
        total=null;
        detTotal=null;
        otherFee=null;
        sumVAT=null;
        totalNotVAT=null;
    });
    return false;
}
function createAutoAdjustment(kind,matIds,reqDetailIds,conId){
    dijit.byId('appendixTabs').selectChild('adjustmentlist');
    if(kind==null) kind=0;
    var url="contractForm.do?kind="+kind+"&conIds="+conId;
    var parentId=getContractId();
    if(parentId!='') url=url+"&parentId="+parentId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'adjustmentlist');
        if(matIds!=null){
            document.forms['adjustmentForm'].reqDetailIds.value=reqDetailIds;
            url='orderMaterialListInContract.do?conId='+parentId+'&matIds='+matIds;
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listAdjustmentMaterialDataSpan');
                var total=document.forms['adjustmentForm'].total;
                var totalNotVAT=document.forms['adjustmentForm'].totalNotVAT;
                var sumVAT=document.forms['adjustmentForm'].sumVAT;
                var otherFee=document.forms['adjustmentForm'].otherFee;
                var detTotal=document.forms['adjustmentForm'].detTotal;
                var vat=document.forms['adjustmentForm'].vat;
                var price=document.forms['adjustmentForm'].price;

                var quantity=document.forms['adjustmentForm'].quantity;
                if(price!=null){
                    if (price.length!=null) {
                        for (i = 0; i < detTotal.length; i++) {
                            tryNumberFormat(vat[i]);
                            tryNumberFormat(price[i]);
                            tryNumberFormat(detTotal[i]);
                            tryNumberFormat(quantity[i]);
                        }
                    } else{
                        tryNumberFormat(vat);
                        tryNumberFormat(price);
                        tryNumberFormat(detTotal);
                        tryNumberFormat(quantity);
                    }
                }
                if(otherFee!=null) tryNumberFormat(otherFee);
                if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
                if(sumVAT!=null) tryNumberFormat(sumVAT);
                if(total!=null) tryNumberFormat(total);
                vat=null;
                price=null;
                quantity=null;
                total=null;
                detTotal=null;
                otherFee=null;
                sumVAT=null;
                totalNotVAT=null;

                var list=document.forms['adjustmentForm'].material;
                var ids='';
                var conIds='';
                for(i=0;i<list.options.length;i++){
                    ids+=','+list.options[i].value;
                    conIds+=','+parentId;
                }
                if(ids!=''){
                    ids=ids.substring(1);
                    conIds=conIds.substring(1);
                }
                setSelectedAdjustmentMaterial(ids,conIds);
            });
            callAjax('getVendorByContract.do?conId='+parentId,'adjustmentVendorName',null,null);
        }
    });
    return false;
}
function adjustmentContractChanged(list){
    if(list.selectedIndex==-1) return false;
    callAjax('getVendorByContract.do?conId='+list.options[list.selectedIndex].value,'adjustmentVendorName',null,null);
    callAjax('orderMaterialListInContract.do?conId='+list.options[list.selectedIndex].value,'listAdjustmentMaterialDataSpan',null,null);
    list=null;
    removeAdjustmentMaterial();
    return false;
}
function removeAdjustmentMaterial(){
    var selId=document.forms['adjustmentForm'].matId;
    if(selId==null) return false;
    var tbl=document.getElementById('adjustmentDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
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
function setSelectedAdjustmentMaterial(matIds,conIds){
    var url='adjustmentMaterial.do?conId=';
    var contract=document.forms['adjustmentForm'].parentId;
    var list=document.forms['adjustmentForm'].material;
    if(list.selectedIndex==-1) return false;
    var materialId=null;
    if(matIds!=null){
        materialId=matIds;
        url+=conIds;
    }
    else{
        if(contract.selectedIndex!=null) url+=contract.options[contract.selectedIndex].value;
        else url+=contract.value;
        materialId=list.options[list.selectedIndex].value;
        var matId = document.forms['adjustmentForm'].matId;
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
    }
    url+='&matId='+materialId;
    url+='&reqDetailIds='+document.forms['adjustmentForm'].reqDetailIds.value;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'adjustmentMaterialHideDiv');
        var matTable=document.getElementById('orderSourceMaterialTable');
        var detTable=document.getElementById('adjustmentDetailTable');
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
        
        var detTotal=document.forms['adjustmentForm'].detTotal;
        var vat=document.forms['adjustmentForm'].vat;
        var price1=document.forms['adjustmentForm'].price1;
        var price=document.forms['adjustmentForm'].price;
        var quantity=document.forms['adjustmentForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(vat[i]);
                    tryNumberFormat(price1[i]);
                    tryNumberFormat(price[i]);
                    tryNumberFormat(detTotal[i]);
                    tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat);
                tryNumberFormat(price1);
                tryNumberFormat(price);
                tryNumberFormat(detTotal);
                tryNumberFormat(quantity);
            }
        }
        vat=null;
        price1=null;
        price=null;
        quantity=null;
        detTotal=null;
        
        caculateAdjustment();
    });
    matId=null;
    list=null;
    contract=null;
    return false;
}

function caculateAdjustment(){
    var total=document.forms['adjustmentForm'].total;
    if(total==null) return;
    var otherFee=document.forms['adjustmentForm'].otherFee;
    var detTotal=document.forms['adjustmentForm'].detTotal;
    var totalNotVAT=document.forms['adjustmentForm'].totalNotVAT;
    var sumVAT=document.forms['adjustmentForm'].sumVAT;
    var sum=0;
    if(detTotal!=null){
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
            }
        } else sum+=reformatNumberMoneyString(detTotal.value)*1;
        var vat=document.forms['adjustmentForm'].vat;
        var price=document.forms['adjustmentForm'].price;
        var quantity=document.forms['adjustmentForm'].quantity;
        var totalvat=0;
        if (vat.length!=null) {
            for (i = 0; i < vat.length; i++) {
                totalvat+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value)*reformatNumberMoneyString(vat[i].value)*0.01;
            }
        } else totalvat+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*reformatNumberMoneyString(vat.value)*0.01;
        vat=null;
        price=null;
        quantity=null;
        sumVAT.value=totalvat;
        totalNotVAT.value=sum-totalvat;
    }
    
    if(otherFee!=null) sum+=reformatNumberMoneyString(otherFee.value)*1;
    total.value=sum;
    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
    if(sumVAT!=null) tryNumberFormat(sumVAT);
    if(total!=null) tryNumberFormat(total);
    total=null;
    otherFee=null;
    detTotal=null;
    totalNotVAT=null;
    sumVAT=null;
    return false;
}
function caculateAdjustmentDetail(matId){
    var quantity=document.getElementById("det2quantity"+matId);
    var price=document.getElementById("det2price"+matId);
    var vat=document.getElementById("det2vat"+matId);
    var detTotal=document.getElementById("det2Total"+matId);
    detTotal.value=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*(100+reformatNumberMoneyString(vat.value)*1)*0.01;
    tryNumberFormat(detTotal);
    quantity=null;
    price=null;
    vat=null;
    detTotal=null;
    caculateAdjustment();
    return false;
}
function delAdjustmentDetails(){
    var checkflag = false;
    var detId = document.forms['adjustmentForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delContractDetail.do',null,document.forms['adjustmentForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('adjustmentDetailTable');
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
                caculateAdjustment();
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function delAdjustments(kind){
    var checkflag = false;
    var conId = document.forms['adjustmentsForm'].conId;
    if(conId==null) return false;
    if (conId.length!=null) {
        for (i = 0; i < conId.length; i++) {
            if (conId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = conId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delContract.do?kind='+kind,null,document.forms['adjustmentsForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else setAjaxData(data,'adjustmentList');
    });
    conId=null;
    return false;
}
function printContractAdjustment(){
    var conId=document.forms['adjustmentForm'].conId;
    //if(conId!=null) callServer('contractAdjustmentPrint.do?conId='+conId.value);
    if(conId!=null) callServer('contractAppendixPrint.do?conId='+conId.value);
    conId=null;
}
function printAdjustmentRequest(){
    var conId=document.forms['adjustmentForm'].conId;
    if(conId!=null) callServer('contractAdjustmentRequestPrint.do?conId='+conId.value);
    conId=null;
}

function delContracts(kind){
    var checkflag = false;
    var conId = document.forms['contractsForm'].conId;
    if(conId==null) return false;
    if (conId.length!=null) {
        for (i = 0; i < conId.length; i++) {
            if (conId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = conId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delContract.do?kind='+kind,null,document.forms['contractsForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else setAjaxData(data,'contractList');
    });
    conId=null;
    return false;
}
function getConrtactDetail(kind,conId, tenId){
    var url="listContractDetail.do?kind="+kind;
    if(conId!=null) url=url+"&conId="+conId;
    if(tenId!=null) url=url+"&tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listContractMaterialDataDiv');
        var detTotal=document.forms['contractForm'].detTotal;
        var price=document.forms['contractForm'].price;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(price[i]);
                    if(detTotal!=null) tryNumberFormat(detTotal[i]);
                }
            } else{
                tryNumberFormat(price);
                if(detTotal!=null) tryNumberFormat(detTotal);
            }
        }
        price=null;
        detTotal=null;
    });
}
function saveContract(){
    if(scriptFunction=="saveContract()") return false;
    var contractNumber = document.forms['contractForm'].contractNumber;
    if(contractNumber.value==''){
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o s\u1ED1 H\u0110!");
        contractNumber.focus();
        contractNumber=null;
        return false;
    }
    contractNumber=null;
    var expireDate = document.forms['contractForm'].expireDate;
    if(expireDate!=null){
        if(expireDate.value==''){
            alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y k\u1EBFt th\u00FAc");
            expireDate.focus();
            expireDate=null;
            return false;
        }
        expireDate=null;
    }
    var followEmp = document.forms['contractForm'].followEmp;
    if(followEmp!=null){
        if(followEmp.selectedIndex==-1){
            alert("Vui l\u00F2ng ch\u1ECDn nh\u00E2n vi\u00EAn qu\u1EA3n l\u00FD h\u1EE3p \u0111\u1ED3ng");
            followEmp.focus();
            followEmp=null;
            return false;
        }
        followEmp=null;
    }
    var venId = document.forms['contractForm'].venId;
    if(venId!=null){
        if(venId.selectedIndex==-1){
            alert("Vui l\u00F2ng ch\u1ECDn nh\u00E0 cung c\u1EA5p");
            venId.focus();
            venId=null;
            return false;
        }
        venId=null;
    }
    var total=document.forms['contractForm'].total;
    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
    var sumVAT=document.forms['contractForm'].sumVAT;
    var transport=document.forms['contractForm'].transport;
    var otherFee=document.forms['contractForm'].otherFee;
    var detTotal=document.forms['contractForm'].detTotal;
    var vat=document.forms['contractForm'].vat;
    var price=document.forms['contractForm'].price;
    var quantity=document.forms['contractForm'].quantity;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < price.length; i++) {
                reformatNumberMoney(vat[i]);
                reformatNumberMoney(price[i]);
                if(detTotal!=null) reformatNumberMoney(detTotal[i]);
                if(quantity!=null) reformatNumberMoney(quantity[i]);
            }
        } else{
            reformatNumberMoney(vat);
            reformatNumberMoney(price);
            if(detTotal!=null) reformatNumberMoney(detTotal);
            if(detTotal!=null) reformatNumberMoney(quantity);
        }
    }
    if(transport!=null) reformatNumberMoney(transport);
    if(otherFee!=null) reformatNumberMoney(otherFee);
    if(totalNotVAT!=null) reformatNumberMoney(totalNotVAT);
    if(sumVAT!=null) reformatNumberMoney(sumVAT);
    if(total!=null) reformatNumberMoney(total);
    vat=null;
    price=null;
    quantity=null;
    total=null;
    detTotal=null;
    transport=null;
    otherFee=null;
    sumVAT=null;
    totalNotVAT=null;
    
    var cost=document.forms['contractForm'].cost;
    if(cost!=null){
        if (cost.length!=null) {
            for (i = 0; i < cost.length; i++) {
                reformatNumberMoney(cost[i]);
            }
        } else{
            reformatNumberMoney(cost);
        }
        cost=null;
    }
        
    var amount=document.forms['contractForm'].amount;
    if(amount!=null){
        if (amount.length!=null) {
            for (i = 0; i < amount.length; i++) {
                reformatNumberMoney(amount[i]);
            }
        } else{
            reformatNumberMoney(amount);
        }
        amount=null;
    }
        
    //callAjaxCheckError("saveContract.do",null,document.forms['contractForm'],getContractListData);
    var conId = document.forms['contractForm'].conId.value;
    var kind = document.forms['contractForm'].kind.value;
    scriptFunction="saveContract()";
    callAjaxCheckError("saveContract.do",null,document.forms['contractForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        contractForm(kind,conId);
    });
    return false;
}
function saveContractExpireDate(){
    if(scriptFunction=="saveContractExpireDate()") return false;
    var expireDate = document.forms['contractForm'].expireDate;
    if(expireDate!=null){
        if(expireDate.value==''){
            alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y k\u1EBFt th\u00FAc");
            expireDate.focus();
            expireDate=null;
            return false;
        }
        expireDate=null;
    }    
    var conId = document.forms['contractForm'].conId.value;
    var kind = document.forms['contractForm'].kind.value;
    scriptFunction="saveContractExpireDate()";
    callAjaxCheckError("saveContractExpireDate.do",null,document.forms['contractForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        contractForm(kind,conId);
    });
    return false;
}
function delContractDetails(){
    var checkflag = false;
    var detId = document.forms['contractForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delContractDetail.do',null,document.forms['contractForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('contractDetailTable');
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
                caculateContract();
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function caculateContractDetail(matId){
    var quantity=document.getElementById("detquantity"+matId);
    var price=document.getElementById("detprice"+matId);
    var vat=document.getElementById("detvat"+matId);
    var detTotal=document.getElementById("detTotal"+matId);
    if(quantity==null || price==null || vat==null || detTotal==null) return;
    detTotal.value=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*(100+vat.value*1)*0.01;
    tryNumberFormat(detTotal);
    quantity=null;
    price=null;
    vat=null;
    detTotal=null;
    caculateContract();
    return false;
}

function searchContract(kind){
    var checkflag = true;
    var form=document.forms['searchSimpleContractForm'];
    if (form.searchid.value!=0 && form.searchid.value!=12 && form.searchid.value!=13) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchContract.do?kind="+kind,"contractList",form,null);
    form=null;
    return false;
}
function exportContract(kind){
    var form=document.forms['searchSimpleContractForm'];
    var url="contractReportPrint.do?kind="+kind+"&searchid="+form.searchid.value+"&searchvalue="+encodeURIComponent(form.searchvalue.value);
    form=null;
    callServer(url);
    return false;
}
function searchAdvContractForm(kind){
    callAjax('searchAdvContractForm.do?kind='+kind,null,null,showPopupForm);
    return false;
}
function searchAdvContract(){
    callAjax('searchAdvContract.do',null,document.forms['searchContractForm'],getSearchAdvContractData);
    hidePopupForm();
    return false;
}
function getSearchAdvContractData(data){
    setAjaxData(data,'contractList');
}
function contractTenderPlanChanged(list){
    if(list.selectedIndex==-1) return false;
    clearContractContent();
    var url="getVendorForContract.do?tenId="+list.options[list.selectedIndex].value;
    callAjax(url,'contractTenderPlanDiv',null,null);
    list=document.forms['contractForm'].material;
    if(list==null) return false;
    for(i=list.options.length-1;i>=0;i--){
        list.remove(i);
    }
    list=null;
    removeContractMaterial();
    return false;
}
function clearContractContent(){
    var total=document.forms['contractForm'].total;
    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
    var sumVAT=document.forms['contractForm'].sumVAT;
    var transport=document.forms['contractForm'].transport;
    var otherFee=document.forms['contractForm'].otherFee;
    var certificate=document.forms['contractForm'].certificate;
    var note=document.forms['contractForm'].note;
    var currency=document.forms['contractForm'].currency;
    
    if(transport!=null) transport.value=0;
    if(otherFee!=null) otherFee.value=0;
    if(totalNotVAT!=null) totalNotVAT.value=0;
    if(sumVAT!=null) sumVAT.value=0;
    if(total!=null) total.value=0;
    if(certificate!=null) certificate.value="";
    if(note!=null) note.value="";
    if(currency!=null) currency.value=0;
    total=null;
    transport=null;
    otherFee=null;
    sumVAT=null;
    totalNotVAT=null;
    certificate=null;
    note=null;
    currency=null;
}
function contractVendorChanged(list){
    if(list.selectedIndex==-1) return false;
    var tenderList=document.forms['contractForm'].tenId;
    clearContractContent();
    removeContractMaterial();
    loadMaterialInTenderPlan(null,tenderList.options[tenderList.selectedIndex].value,list.options[list.selectedIndex].value,1);
    callAjax('tenderPlanDetailContract.do?tenId='+tenderList.options[tenderList.selectedIndex].value+'&venId='+list.options[list.selectedIndex].value,null,null,function(data){
        data = data.replace(/\n/g, "\\n");
        var obj=eval('('+data+')');
        document.forms['contractForm'].currency.value=obj.currency;
        document.forms['contractForm'].delivery.value=obj.delivery;
        document.forms['contractForm'].certificate.value=obj.certificate;
        document.forms['contractForm'].note.value=obj.note;
    });
    tenderList=null;
    list=null;
    return false;
}
function removeContractMaterial(){
    var selId=document.forms['contractForm'].matId;
    if(selId==null) return false;
    var tbl=document.getElementById('contractDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=1;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            parentNode=parentNode.parentNode;
        //            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        if(parentNode!=null) parentNode.removeChild(selId);
    //        tbl.deleteRow(0);
    }
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
    selId=null;
}
function setSelectedContractMaterial(matIds){
    var list=document.forms['contractForm'].material;
    if(list.selectedIndex==-1) return false;
    var materialId='';
    if(matIds!=null) materialId=matIds;
    else materialId=list.options[list.selectedIndex].value;
    var matId = document.forms['contractForm'].matId;
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
    var tenId=document.forms['contractForm'].tenId;
    var venId=document.forms['contractForm'].venId;
    if(tenId.selectedIndex==null) tenId=tenId.value
    else tenId=tenId.options[tenId.selectedIndex].value;
    if(venId.selectedIndex==null) venId=venId.value
    else venId=venId.options[venId.selectedIndex].value;
    var url='getMaterialInBidEvalSum.do?kind='+document.forms['contractForm'].kind.value+'&matIds='+materialId+'&tenId='+tenId+'&venId='+venId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'contractMaterialHideDiv');
        var matTable=document.getElementById('bidEvalSumDetailDetailTable');
        var detTable=document.getElementById('contractDetailTable');
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
        
        var detTotal=document.forms['contractForm'].detTotal;
        var vat=document.forms['contractForm'].vat;
        var price=document.forms['contractForm'].price;
        var quantity=document.forms['contractForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < price.length; i++) {
                    tryNumberFormat(vat[i]);


                    tryNumberFormat(price[i]);
                    if(detTotal!=null) tryNumberFormat(detTotal[i]);
                    if(quantity!=null) tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat);
                tryNumberFormat(price);
                if(detTotal!=null) tryNumberFormat(detTotal);
                if(quantity!=null) tryNumberFormat(quantity);
            }
        }
        vat=null;
        price=null;
        quantity=null;
        detTotal=null;
        
        caculateContract();
    });
    matId=null;
    return false;
}
function caculateContract(){
    var total=document.forms['contractForm'].total;
    if(total==null) return;
    var transport=document.forms['contractForm'].transport;
    var otherFee=document.forms['contractForm'].otherFee;
    var detTotal=document.forms['contractForm'].detTotal;
    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
    var sumVAT=document.forms['contractForm'].sumVAT;
    var sum=0;
    var notVAT=0;
    if(detTotal!=null){
        var vat=document.forms['contractForm'].vat;
        var price=document.forms['contractForm'].price;
        var quantity=document.forms['contractForm'].quantity;
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
//                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
                notVAT+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value);
            }
        } else{
//            sum+=reformatNumberMoneyString(detTotal.value)*1;
            notVAT+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value);
        }
        var totalvat=0;
        if (vat.length!=null) {
            for (i = 0; i < vat.length; i++) {
                totalvat+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value)*reformatNumberMoneyString(vat[i].value)*0.01;
            }
        } else totalvat+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*reformatNumberMoneyString(vat.value)*0.01;
        vat=null;
        price=null;
        quantity=null;
        sumVAT.value=totalvat;
//        totalNotVAT.value=sum-totalvat;
        totalNotVAT.value=notVAT;
        sum = totalvat + notVAT;
    }
    
    if(transport!=null) sum+=reformatNumberMoneyString(transport.value)*1;
    if(otherFee!=null) sum+=reformatNumberMoneyString(otherFee.value)*1;
    total.value=sum;
    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
    if(sumVAT!=null) tryNumberFormat(sumVAT);
    if(total!=null) tryNumberFormat(total);
    total=null;
    transport=null;
    otherFee=null;
    detTotal=null;
    sumVAT=null;
    totalNotVAT=null;
    return false;
}

function caculateAdjustment(){
    var total=document.forms['adjustmentForm'].total;
    if(total==null) return;
    var transport=document.forms['adjustmentForm'].transport;
    var otherFee=document.forms['adjustmentForm'].otherFee;
    var detTotal=document.forms['adjustmentForm'].detTotal;
    var totalNotVAT=document.forms['adjustmentForm'].totalNotVAT;
    var sumVAT=document.forms['adjustmentForm'].sumVAT;
    var sum=0;
    var notVAT=0;
    if(detTotal!=null){
        var vat=document.forms['adjustmentForm'].vat;
        var price=document.forms['adjustmentForm'].price;
        var quantity=document.forms['adjustmentForm'].quantity;
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
//                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
                notVAT+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value);
            }
        } else{
//            sum+=reformatNumberMoneyString(detTotal.value)*1;
            notVAT+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value);
        }
        var totalvat=0;
        if (vat.length!=null) {
            for (i = 0; i < vat.length; i++) {
                totalvat+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value)*reformatNumberMoneyString(vat[i].value)*0.01;
            }
        } else totalvat+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*reformatNumberMoneyString(vat.value)*0.01;
        vat=null;
        price=null;
        quantity=null;
        sumVAT.value=totalvat;
//        totalNotVAT.value=sum-totalvat;
        totalNotVAT.value=notVAT;
        sum = totalvat + notVAT;
    }
    
    if(transport!=null) sum+=reformatNumberMoneyString(transport.value)*1;
    if(otherFee!=null) sum+=reformatNumberMoneyString(otherFee.value)*1;
    total.value=sum;
    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
    if(sumVAT!=null) tryNumberFormat(sumVAT);
    if(total!=null) tryNumberFormat(total);
    total=null;
    transport=null;
    otherFee=null;
    detTotal=null;
    sumVAT=null;
    totalNotVAT=null;
    return false;
}
function setSelectedContractCostProject(selectedValue,selectedText,costValue){
    var list=null;
    var projectId=null;
    var text=null;
    if(selectedValue!=null){
        projectId=selectedValue;
        text=selectedText;
    }else{
        list=document.forms['contractForm'].project;
        if(list.selectedIndex==-1) return false;
        projectId=list.options[list.selectedIndex].value;
        text=list.options[list.selectedIndex].text;
    }
    var proId = document.forms['contractForm'].proId;
    var existed=false;
    if(proId!=null){
        if (proId.length!=null) {
            for (i = 0; i < proId.length; i++) {
                if (proId[i].value == projectId) {
                    existed = true;
                    break;
                }
            }
        } else if(proId.value==projectId) existed=true;
    }
    if(existed==true && selectedValue==null){
        alert('D\u1EF1 \u00E1n \u0111\u00E3 t\u1ED3n t\u1EA1i');
        return false;
    }
    proId=null;
    var tbl=document.getElementById('contractCostTable');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var div = document.createElement('div');
    cell.appendChild(div);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'pcId';
    div.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'conProId';
    el.value='0';
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'proId';
    el.value=projectId;
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    var span = document.createElement('span');
    span.innerHTML=text;
    cell.appendChild(span);
    
    cell = row.insertCell(2);
    el = document.getElementById("inputCostTemplate").cloneNode(true);
    if(el!=null){
        el.name="cost";
        showhide2(el,false);
        cell.appendChild(el);
        if(costValue!=null) el.value=costValue;
    }
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    list=null;
    return false;
}
function setSelectedContractBill(){
    var tbl=document.getElementById('contractBillTable');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var div = document.createElement('div');
    cell.appendChild(div);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'icId';
    div.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'conInvId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    el = document.createElement('input');
    el.type = 'text';
    el.size=30;
    el.name = 'invoice';
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.getElementById("inputBillTemplate").cloneNode(true);
    if(el!=null){
        el.id="paymentDate"+lastRow;
        el.name="paymentDate";
        showhide2(el,false);
        cell.appendChild(el);
    }
    cell = row.insertCell(3);
    el = document.getElementById("inputBillDateTemplate").cloneNode(true);
    if(el!=null){
        el.id="invoiceDate"+lastRow;
        el.name="invoiceDate";
        showhide2(el,false);
        cell.appendChild(el);
    }
    cell = row.insertCell(4);
    el = document.getElementById("inputBillAmountTemplate").cloneNode(true);
    if(el!=null){
        el.id="amount"+lastRow;
        el.name="amount";
        showhide2(el,false);
        cell.appendChild(el);
    }
    cell = row.insertCell(5);
    el = document.getElementById("inputBillNoteTemplate").cloneNode(true);
    if(el!=null){
        el.name="invoiceNote";
        showhide2(el,false);
        cell.appendChild(el);
    }
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function saveMaterialOrderContract(detId,kind) {
    var nameVn = document.forms['addMaterialOrderContract'].nameVn;
    var nameEn = document.forms['addMaterialOrderContract'].nameEn;
    if (nameVn.value.length==0 && nameEn.value.length==0){
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn=null;
        return false;
    }
    var uniId = document.forms['addMaterialOrderContract'].uniId;
    if(uniId.selectedIndex==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId=null;
        return false;
    }
    callAjaxCheckError("addMaterial.do?detId="+detId+"&kind="+kind,null,document.forms['addMaterialOrderContract'],function(data){        
        if(kind == 8){
            adjustmentForm(document.forms['adjustmentForm'].kind.value,document.forms['adjustmentForm'].conId.value);
        }else{
            contractForm(document.forms['contractForm'].kind.value,document.forms['contractForm'].conId.value);
        }        
        hidePopupForm();
        //setMaterialBidEvalSum(document.forms['addMaterialTechEval'].tenId.value);
    },'adjustmentErrorMessageDiv');
    hidePopupForm();
    return false;
}
function delOrderContractDetails(){
    var detId = document.forms['contractForm'].detId;
    var tbl=document.getElementById('contractDetailTable');
                var parentNode;
                if(detId.length!=null){
                    for (i=detId.length-1;i>=0;i--) {                        
                            parentNode=detId[i].parentNode;
                            parentNode=parentNode.parentNode;
                            parentNode=parentNode.parentNode;
                            tbl.deleteRow(parentNode.rowIndex);                        
                    }
                }else tbl.deleteRow(1);
                parentNode=null;
                tbl=null;
                caculateContract();
    return false;
}
function selectMaterialOrderContract(handle,title,matId,reqDetId,matIds,reqIds,detIds,detId,kind){
    if(handle==null) return false;
    //var tenId = document.forms['bidEvalSumForm'].tenId.value;
    popupName=title;
    callAjax('chooseMaterialOrderContractForm.do?matId='+matId+'&matIds='+matIds+'&reqIds='+reqIds+'&detIds='+detIds+'&detId='+detId+'&kind='+kind,null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        document.forms['selectMaterialOrderContractForm'].searchid.value = 2;
        var nameVn=document.getElementById("materialTempName"+detId);
        if(nameVn!=null) nameVn=nameVn.value;
        document.forms['selectMaterialOrderContractForm'].searchvalue.value = nameVn.replace("^"," ");
        searchMaterialOrderContract();
    });
    return false;
}
function newMaterialOrderContract(handle,id,title,detId,reqDetId,matIds,reqIds,detIds,kind,matId){
    popupName=title;
    if(handle==null){
        callAjax('materialForm.do?matId='+id,null,null,function(data){
            showPopupForm(data);
        });
    }
    else{
        callAjaxCheckError('addMaterialOrderContractForm.do?detId='+detId+'&matIds='+matIds+'&reqIds='+reqIds+'&detIds='+detIds+'&matId='+matId,null,null,function(data){
            showPopupForm(data);
            document.getElementById('callbackFunc').value=handle;
            document.getElementById('kind').value=kind;
        },'adjustmentErrorMessageDiv');
    }
    return false;
}
function deleteMaterialOrderContract(reqDetId){
    if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n ph\u1EE5c h\u1ED3i VT n\u00E0y ?')){
        callAjaxCheckError("reConfirmMaterial.do?reqDetailId="+reqDetId,null,null,function(data){
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        });
    }
        
    return false;
}
function searchMaterialOrderContract(params){
    var form=document.forms['selectMaterialOrderContractForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchMaterialOrderContract.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialOrderContractList');
        var matId = document.forms['materialOrderContractForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialOrderContractSelectedForm'].materialSelectedChk;
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
function setMaterialOrderContractSelected(kind){
    var matId = document.forms['materialOrderContractForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialOrderContractForm'].matNameHidden;
    var matCodeHidden = document.forms['materialOrderContractForm'].matCodeHidden;
    var tbl=document.getElementById('materialOrderContractSelectedTbl');
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
    chooseMaterialOrderContractSelected(kind);
}
function delMaterialOrderContract(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialOrderContractSelectedTbl');
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
    var matId = document.forms['materialOrderContractForm'].matId;
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
function chooseMaterialOrderContractSelected(kind){
    var selId=document.forms['materialOrderContractSelectedForm'].materialSelectedChk;
    var detIds=document.forms['materialOrderContractForm'].detIds.value;
    var detId=document.forms['materialOrderContractForm'].detId.value;
    var matIda=document.forms['materialOrderContractForm'].matIda.value;
    var matIds=document.forms['materialOrderContractForm'].matIds.value;
    var reqIds=document.forms['materialOrderContractForm'].reqIds.value;
    var kind=document.forms['materialOrderContractForm'].kind.value;
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
    //callAjaxCheckError("saveBidEvalSumMaterial.do?matId="+ids+"&detId="+detId,null,null,function(data){
        hidePopupForm();
        //setMaterialBidEvalSum(tenId);
        setSelectedOrderMaterialNotInContractA(detIds,matIds,reqIds,ids,matIda,detId,kind);
    //});
    hidePopupForm();
}
//CONTRACT PRINCIPLE
function loadContractPrincipleList(){
    callAjax('contractPrincipleList.do',null,null,getContractListData);
    return false;
}
function loadContractPrinciples(params){
    //callAjaxExChild("contractPrinciples.do","contractList",params);
    callAjaxExChild("searchContract.do?kind=2","contractList",params);
    return false;
}

//ORDER
function loadOrderList(){
    callAjax('orderList.do',null,null,getContractListData);
    return false;
}
function loadOrders(params){
    //callAjaxExChild("orders.do","contractList",params);
    callAjaxExChild("searchContract.do?kind=3","contractList",params);
    return false;
}
function orderMaterialSourceChanged(list){
    if(list.selectedIndex==-1) return false;
    if(list.options[list.selectedIndex].value=='') return false;
    var url='orderMaterialSource.do?kind=';
    if(list.options[list.selectedIndex].value=='contract'){
        url+="1";
    }else if(list.options[list.selectedIndex].value=='tenderplan'){
        url+="2";
    }else if(list.options[list.selectedIndex].value=='other'){
        url+="3";
        setAjaxData('','listTenderPlanMaterialDataSpan');
    }
    callAjax(url,'orderSourceTr',null,null);
    list=document.forms['contractForm'].material;
    if(list!=null){
        for(i=list.options.length-1;i>=0;i--){
            list.remove(i);
        }
        list=null;
    }
    removeContractMaterial();
    return false;
}
function orderContractChanged(list){
    if(list.selectedIndex==-1) return false;
    var conId=list.options[list.selectedIndex].value;
    list=null;
    callAjax('orderMaterialListInContract.do?conId='+conId,'listTenderPlanMaterialDataSpan',null,null);
    callAjax('getVendorForOrder.do?conId='+conId,'contractTenderPlanDiv',null,null);
    removeContractMaterial();
    return false;
}

function loadMaterialListInContract(){
//    callAjax('materialInContractPanel.do',null,null,getMaterialInContractData);
    callAjax('materialInContractTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addMaterialInContractTabs();
        loadMaterialInContractPrinciplesPanel();
    });
    return false;
}
function addMaterialInContractTabs(){
    displayTabs("materialInContractTabs","materialInContractTabChild",materialInContractTabHandle);
}
function materialInContractTabHandle(child){
    if(child.isLoaded=='true') return true;
    if(child.id=='incontractprinciplepanel') loadMaterialInContractPrinciplesPanel();
    else if(child.id=='incontractprincipleexpirepanel') loadMaterialInContractPrincipleExpiresPanel();
    else if(child.id=='incontractpanel') loadMaterialInContractsPanel();
    else if(child.id=='inadjustmentcontractpanel') loadMaterialInAdjustmentContractsPanel();
    else if(child.id=='inorderpanel') loadMaterialInOrdersPanel();
    else if(child.id=='notincontractpanel') loadMaterialNotInContractsPanel();
    child.isLoaded='true';
    child=null;
    return false;
}
function getMaterialInContractData(data){
    setAjaxData(data,'ajaxContent');
    loadMaterialInContractPrinciples();
    loadMaterialInContractPrincipleExpires();
    loadMaterialInContracts();
    loadMaterialInOrders();
    loadMaterialNotInContracts();
    return false;
}
function loadMaterialInContractsPanel(params){
    callAjax('materialInContractPanel.do',null,null,function(data){
        setAjaxData(data,'incontractpanel');
        loadMaterialInContracts(params);
    });
    return false;
}
function loadMaterialInAdjustmentContractsPanel(params){
    callAjax('materialInAdjustmentContractPanel.do',null,null,function(data){
        setAjaxData(data,'inadjustmentcontractpanel');
        loadMaterialInAdjustmentContracts(params);
    });
    return false;
}
function loadMaterialInContracts(params){
//    callAjaxExChild("materialInContracts.do","materialInContractList",params);
if(params==null){
    callAjaxExChild("materialInContracts.do","incontract",params);
}
else{
    callAjaxExChild("materialInContracts.do",null,params,function(data){
        setAjaxData(data,'incontract');
        checkMaterialInContract('materialInContractForm');
    });
}
    return false;
}
function loadMaterialInAdjustmentContracts(params){
if(params==null){
    callAjaxExChild("materialInAdjustmentContracts.do","inadjustmentcontract",params);
}
else{
    callAjaxExChild("materialInAdjustmentContracts.do",null,params,function(data){
        setAjaxData(data,'inadjustmentcontract');
        checkMaterialInContract('materialInAdjustmentContractForm');
    });
}
    return false;
}
function loadMaterialInOrdersPanel(params){
    callAjax('materialInOrderPanel.do',null,null,function(data){
        setAjaxData(data,'inorderpanel');
        loadMaterialInOrders(params);
    });
    return false;
}
function loadMaterialInOrders(params){
//    callAjaxExChild("materialInOrders.do","materialInOrderList",params);
if(params==null){
    callAjaxExChild("materialInOrders.do","inorder",params);
}
else{
    callAjaxExChild("materialInOrders.do",null,params,function(data){
        setAjaxData(data,'inorder');
        checkMaterialInContract('materialInOrderForm');
    });
}
    return false;
}
function loadMaterialInContractPrinciplesPanel(params){
    callAjax('materialInContractPrinciplePanel.do',null,null,function(data){
        setAjaxData(data,'incontractprinciplepanel');
        loadMaterialInContractPrinciples(params);
    });
    return false;
}
function loadMaterialInContractPrinciples(params){
//    callAjaxExChild("materialInContractPrinciples.do","materialInContractPrincipleList",params);
if(params==null){
    callAjaxExChild("materialInContractPrinciples.do","incontractprinciple",params);
}
else{
    callAjaxExChild("materialInContractPrinciples.do",null,params,function(data){
        setAjaxData(data,'incontractprinciple');
        checkMaterialInContract('materialInContractPrincipleForm');
    });
}
    return false;
}
function loadMaterialInContractPrincipleExpiresPanel(params){
    callAjax('materialInContractPrincipleExpirePanel.do',null,null,function(data){
        setAjaxData(data,'incontractprincipleexpirepanel');
        loadMaterialInContractPrincipleExpires(params);
    });
    return false;
}
function loadMaterialInContractPrincipleExpires(params){
//    callAjaxExChild("materialInContractPrincipleExpires.do","materialInContractPrincipleExpireList",params);
if(params==null){
    callAjaxExChild("materialInContractPrincipleExpires.do","incontractprincipleexpire",params);
}
else{
    callAjaxExChild("materialInContractPrincipleExpires.do",null,params,function(data){
        setAjaxData(data,'incontractprincipleexpire');
        checkMaterialInContract('materialInContractPrincipleExpireForm');
    });
}
    return false;
}
function loadMaterialNotInContractsPanel(params){
    callAjax('materialNotInContractPanel.do',null,null,function(data){
        setAjaxData(data,'notincontractpanel');
        loadMaterialNotInContracts(params);
    });

    return false;
}
function loadMaterialNotInContracts(params){
//    callAjaxExChild("materialNotInContracts.do","materialNotInContractList",params);
if(params==null){
    callAjaxExChild("materialNotInContracts.do","notincontract",params);
}
else{
    callAjaxExChild("materialNotInContracts.do",null,params,function(data){
        setAjaxData(data,'notincontract');
        checkMaterialInContract('materialNotInContractForm');
    });
}
    return false;
}
function searchMaterialInContract(kind, divId, form, params){
    url="searchMaterialInContractTabs.do?kind="+kind;
    if(params!=null) url+="&"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,divId);
        var s = "_search";
        var n = s.length;
        s = form.name.substring(0,form.name.length-n);
        checkMaterialInContract(s);
//        form=document.forms[s];
//        var reqDetId=eval('form.reqDetId_'+s);
//        var reqId=eval('form.reqId_'+s);
//        var matId=eval('form.matId_'+s);
//        var conId=eval('form.conId_'+s);
//        if(reqDetId!=null) reqDetId.value="";
//        if(reqId!=null) reqId.value="";
//        if(matId!=null) matId.value="";
//        if(conId!=null) conId.value="";
//        reqDetId=null;
//        reqId=null;
//        matId=null;
//        conId=null;
    });
    return false;
}
function exportMaterialInContract(kind, form, params){
    var url="exportMaterialInContractTabs.do?kind="+kind;
    if(params!=null) url+="&"+params;
    url +="&searchid="+form.searchid.value+"&searchvalue="+encodeURIComponent(form.searchvalue.value);
    callServer(url);
    return false;
    return false;
}
function createOrder_old(kind,order){
    var form=null;
    var conId;
    var reqDetailId=null;
    if(kind=='contract'){
        form=document.forms['materialInContractForm'];
        conId=form.conId;
    }
    else if(kind=='notincontract'){
        form=document.forms['materialNotInContractForm'];
        conId=form.reqId;
        reqDetailId=form.reqDetailId;
    }
    else if(kind=='order'){
        form=document.forms['materialInOrderForm'];
        conId=form.conId;
        reqDetailId=form.reqDetailId;
    }
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var matId = form.matId;
    var manyCon=false;
    var con='';
    if(matId==null || conId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                conIds+=','+conId[i].value;
                if(reqDetailId!=null) reqDetailIds+=','+reqDetailId[i].value;
                if(manyCon==false && con!='' && con!=conId[i].value) manyCon=true;
                else con=conId[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        conIds+=','+conId.value;
        if(reqDetailId!=null) reqDetailIds+=','+reqDetailId.value;
        con=conId.value;
    }
    if (ids!='0'){
        if(kind=='contract' || kind=='order'){
            if(manyCon==true){
                alert('\u0110\u01A1n \u0111\u1EB7t h\u00E0ng ch\u1EC9 l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
                return false;
            }
        }
        var url="contractForm.do?kind="+order;
        if(kind!='notincontract') url+='&materialCon='+con;
        if(kind=='contract'){
            url+='&isnotresell=1';
        }
        else if(kind=='notincontract'){
            
        }
        else if(kind=='order'){
            url+='&isnotresell=1';
        }
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            var orderMaterialSource=document.forms['contractForm'].orderMaterialSource;
            if(kind=='contract' || kind=='order'){
                orderMaterialSource.value="contract";
                callAjax('orderMaterialListInContract.do?conId='+con,'listTenderPlanMaterialDataSpan',null,null);
                if(kind=='order') callAjax('getVendorForOrder.do?conId='+con,'contractTenderPlanDiv',null,null);
                url='orderContractMaterial.do?conId='+conIds.substring(2)+'&matId='+ids.substring(2)+'&orderMaterialSource='+orderMaterialSource.value;
                if(reqDetailIds!='0'){
                    reqDetailIds=reqDetailIds.substring(2);
                    url+='&reqDetailIds='+reqDetailIds;
                }
                callAjax(url,null,null,function(data){
                    setAjaxData(data,'contractMaterialHideDiv');
                    var matTable=document.getElementById('orderSourceMaterialTable');
                    var detTable=document.getElementById('contractDetailTable');
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
                    orderFormat();
                });
            }
            else if(kind=='notincontract'){
                orderMaterialSource.value="other";
                setAjaxData('','listTenderPlanMaterialDataSpan');
                callAjax('orderMaterialSource.do?kind=3','orderSourceTr',null,null);
                setSelectedOrderMaterialNotInContract(ids.substring(2),conIds.substring(2));
            }
            orderMaterialSource.disabled="true";
            orderMaterialSource=null;
        });
    }
    matId=null;
    conId=null;
    form=null;
    return false;
}
function createOrder(kind,order){
    var form=null;
    var conId=null;
    var reqDetailId=null;
    var matId=null;
    if(kind=='contract'){
        form=document.forms['materialInContractForm'];
        conId=form.conId;
    }
    else if(kind=='notincontract'){
        form=document.forms['materialNotInContractForm'];
        conId=form.reqId_materialNotInContractForm;
//        conId=form.conId_materialNotInContractForm;
        reqDetailId=form.reqDetId_materialNotInContractForm;
        matId = form.matId_materialNotInContractForm;
    }
    else if(kind=='order'){
        form=document.forms['materialInOrderForm'];
        conId=form.conId_materialInOrderForm;
        reqDetailId=form.reqDetId_materialInOrderForm;
        matId = form.matId_materialInOrderForm;
    }
    var ids = '';
    var conIds='';
    var reqDetailIds='';
    var manyCon=false;
    var con='';
    if(kind=='notincontract'){
        conIds=getReqIdsForCreateOrder();
        reqDetailIds=getReqDetIdsForCreateOrder();
        ids=getMatIdsForCreateOrder();
    }
    if(matId.value!="" && matId.value!="0,"){
        if(conId!=null){
            if(conIds!='') conIds+=conId.value.substring(2);
            else conIds=conId.value.substring(2);
        }
        if(reqDetailIds!='') reqDetailIds+=reqDetailId.value.substring(2);
        else reqDetailIds=reqDetailId.value.substring(2);
        if(ids!='') ids+=matId.value.substring(2);
        else ids=matId.value.substring(2);
    }
    if(ids=="" || ids=="0" || ids=="0,"){
        return false;
    }
    ids=ids.substring(0,ids.length-1);
    conIds=conIds.substring(0,conIds.length-1);
    reqDetailIds=reqDetailIds.substring(0,reqDetailIds.length-1);
    var t=conIds.split(',');
    for(var i=0;i<t.length;i++){
        if(manyCon==false && con!='' && con!=t[i]){
             manyCon=true;
             break;
        }
        else con=t[i];
    }
    
    var k=reqDetailIds.split(',');
    var reqDetailIds_temp='';
    var ind=-1;
    for(i=0;i<k.length;i++){
        ind=k[i].indexOf('_');
        if(ind>-1){
            reqDetailIds_temp+=','+k[i].substring(0,ind);
        }else{
            reqDetailIds_temp+=','+k[i];
        }
    }
    if(reqDetailIds_temp!=''){
        reqDetailIds=reqDetailIds_temp.substring(1);
    }
    
    if (ids!='0'){
        if(kind=='contract' || kind=='order'){
            if(manyCon==true){
                alert('\u0110\u01A1n \u0111\u1EB7t h\u00E0ng ch\u1EC9 l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
                return false;
            }
        }
        var url="contractForm.do?kind="+order+"&formKind="+kind;
        if(kind!='notincontract') url+='&materialCon='+con;
        if(kind=='contract'){
            url+='&isnotresell=1';
        }
        else if(kind=='notincontract'){
            
        }
        else if(kind=='order'){
            url+='&isnotresell=1';
        }
        if(conIds!='0') url+='&conIds='+conIds;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            var orderMaterialSource=document.forms['contractForm'].orderMaterialSource;
            if(kind=='contract' || kind=='order'){
                orderMaterialSource.value="contract";
                callAjax('orderMaterialListInContract.do?conId='+con,'listTenderPlanMaterialDataSpan',null,null);
                if(kind=='order') callAjax('getVendorForOrder.do?conId='+con,'contractTenderPlanDiv',null,null);
                url='orderContractMaterial.do?conId='+conIds+'&matId='+ids+'&orderMaterialSource='+orderMaterialSource.value;
                if(reqDetailIds!='0'){
                    url+='&reqDetailIds='+reqDetailIds;
                }
                callAjax(url,null,null,function(data){
                    setAjaxData(data,'contractMaterialHideDiv');
                    var matTable=document.getElementById('orderSourceMaterialTable');
                    var detTable=document.getElementById('contractDetailTable');
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
                    orderFormat();
                });
            }
            else if(kind=='notincontract'){
                orderMaterialSource.value="other";
                setAjaxData('','listTenderPlanMaterialDataSpan');
                callAjax('orderMaterialSource.do?kind=3','orderSourceTr',null,null);
                setSelectedOrderMaterialNotInContract(ids,conIds);
            }
            orderMaterialSource.disabled="true";
            orderMaterialSource=null;
        });
    }
    matId=null;
    conId=null;
    form=null;
    reqDetailId=null;
    return false;
}
function getReqIdsForCreateOrder(){
    var form=null;
    var reqIds='';
    var result='';
    var reqId=null;
    form=document.forms['materialInContractPrincipleForm'];
    if(form!=null){
        reqId=form.reqId_materialInContractPrincipleForm;
        if(reqId.value!="" && reqId.value!="0" && reqId.value!="0,"){
            reqIds=reqId.value.substring(2);
            if(result!='') result+=reqIds;
            else result+=reqIds;
        }
    }
    form=document.forms['materialInAdjustmentContractForm'];
    if(form!=null){
        reqId=form.reqId_materialInAdjustmentContractForm;
        if(reqId.value!="" && reqId.value!="0" && reqId.value!="0,"){
            reqIds=reqId.value.substring(2);
            if(result!='') result+=reqIds;
            else result+=reqIds;
        }
    }
    form=document.forms['materialInContractPrincipleExpireForm'];
    if(form!=null){
        reqId=form.reqId_materialInContractPrincipleExpireForm;
        if(reqId.value!="" && reqId.value!="0" && reqId.value!="0,"){
            reqIds=reqId.value.substring(2);
            if(result!='') result+=reqIds;
            else result+=reqIds;
        }
    }
    form=document.forms['materialInContractForm'];
    if(form!=null){
        reqId=form.reqId_materialInContractForm;
        if(reqId.value!="" && reqId.value!="0" && reqId.value!="0,"){
            reqIds=reqId.value.substring(2);
            if(result!='') result+=reqIds;
            else result+=reqIds;
        }
    }
    form=document.forms['materialInOrderForm'];
    if(form!=null){
        reqId=form.reqId_materialInOrderForm;
        if(reqId.value!="" && reqId.value!="0" && reqId.value!="0,"){
            reqIds=reqId.value.substring(2);
            if(result!='') result+=reqIds;
            else result+=reqIds;
        }
    }
    form=null;
    reqId=null;
    return result;
}
function getReqDetIdsForCreateOrder(){
    var form=null;
    var reqDetIds='';
    var result='';
    var reqDetId=null;
    form=document.forms['materialInContractPrincipleForm'];
    if(form!=null){
        reqDetId=form.reqDetId_materialInContractPrincipleForm;
        if(reqDetId.value!="" && reqDetId.value!="0" && reqDetId.value!="0,"){
            reqDetIds=reqDetId.value.substring(2);
            if(result!='') result+=reqDetIds;
            else result+=reqDetIds;
        }
    }
    form=document.forms['materialInContractPrincipleExpireForm'];
    if(form!=null){
        reqDetId=form.reqDetId_materialInContractPrincipleExpireForm;
        if(reqDetId.value!="" && reqDetId.value!="0" && reqDetId.value!="0,"){
            reqDetIds=reqDetId.value.substring(2);
            if(result!='') result+=reqDetIds;
            else result+=reqDetIds;
        }
    }
    form=document.forms['materialInContractForm'];
    if(form!=null){
        reqDetId=form.reqDetId_materialInContractForm;
        if(reqDetId.value!="" && reqDetId.value!="0" && reqDetId.value!="0,"){
            reqDetIds=reqDetId.value.substring(2);
            if(result!='') result+=reqDetIds;
            else result+=reqDetIds;
        }
    }
    form=document.forms['materialInOrderForm'];
    if(form!=null){
        reqDetId=form.reqDetId_materialInOrderForm;
        if(reqDetId.value!="" && reqDetId.value!="0" && reqDetId.value!="0,"){
            reqDetIds=reqDetId.value.substring(2);
            if(result!='') result+=reqDetIds;
            else result+=reqDetIds;
        }
    }
    form=null;
    reqDetId=null;
    return result;
}
function getConDetIdsForCreateTenderPlan(){
    var form=null;
    var conDetIds='';
    var result='';
    var conDetId=null;
    form=document.forms['materialInContractPrincipleForm'];
    if(form!=null){
        conDetId=form.conDetId_materialInContractPrincipleForm;
        if(conDetId.value!="" && conDetId.value!="0" && conDetId.value!="0,"){
            conDetIds=conDetId.value.substring(2);
            if(result!='') result+=conDetIds;
            else result+=conDetIds;
        }
    }
    form=document.forms['materialInContractPrincipleExpireForm'];
    if(form!=null){
        conDetId=form.conDetId_materialInContractPrincipleExpireForm;
        if(conDetId.value!="" && conDetId.value!="0" && conDetId.value!="0,"){
            conDetIds=conDetId.value.substring(2);
            if(result!='') result+=conDetIds;
            else result+=conDetIds;
        }
    }
    form=document.forms['materialInContractForm'];
    if(form!=null){
        conDetId=form.conDetId_materialInContractForm;
        if(conDetId.value!="" && conDetId.value!="0" && conDetId.value!="0,"){
            conDetIds=conDetId.value.substring(2);
            if(result!='') result+=conDetIds;
            else result+=conDetIds;
        }
    }
    form=document.forms['materialInOrderForm'];
    if(form!=null){
        conDetId=form.conDetId_materialInOrderForm;
        if(conDetId.value!="" && conDetId.value!="0" && conDetId.value!="0,"){
            conDetIds=conDetId.value.substring(2);
            if(result!='') result+=conDetIds;
            else result+=conDetIds;
        }
    }
//    form=document.forms['materialNotInContractForm'];
//    if(form!=null){
//        conDetId=form.conDetId_materialNotInContractForm;
//        if(conDetId.value!="" && conDetId.value!="0" && conDetId.value!="0,"){
//            conDetIds=conDetId.value.substring(2);
//            if(result!='') result+=conDetIds;
//            else result+=conDetIds;
//        }
//    }
    form=null;
    conDetId=null;
    return result;
}
function getMatIdsForCreateOrder(){
    var form=null;
    var matIds='';
    var result='';
    var matId=null;
    form=document.forms['materialInContractPrincipleForm'];
    if(form!=null){
        matId=form.matId_materialInContractPrincipleForm;
        if(matId.value!="" && matId.value!="0" && matId.value!="0,"){
            matIds=matId.value.substring(2);
            if(result!='') result+=matIds;
            else result+=matIds;
        }
    }
    form=document.forms['materialInAdjustmentContractForm'];
    if(form!=null){
        matId=form.matId_materialInAdjustmentContractForm;
        if(matId.value!="" && matId.value!="0" && matId.value!="0,"){
            matIds=matId.value.substring(2);
            if(result!='') result+=matIds;
            else result+=matIds;
        }
    }
    form=document.forms['materialInContractPrincipleExpireForm'];
    if(form!=null){
        matId=form.matId_materialInContractPrincipleExpireForm;
        if(matId.value!="" && matId.value!="0" && matId.value!="0,"){
            matIds=matId.value.substring(2);
            if(result!='') result+=matIds;
            else result+=matIds;
        }
    }
    form=document.forms['materialInContractForm'];
    if(form!=null){
        matId=form.matId_materialInContractForm;
        if(matId.value!="" && matId.value!="0" && matId.value!="0,"){
            matIds=matId.value.substring(2);
            if(result!='') result+=matIds;
            else result+=matIds;
        }
    }
    form=document.forms['materialInOrderForm'];
    if(form!=null){
        matId=form.matId_materialInOrderForm;
        if(matId.value!="" && matId.value!="0" && matId.value!="0,"){
            matIds=matId.value.substring(2);
            if(result!='') result+=matIds;
            else result+=matIds;
        }
    }
    form=null;
    matId=null;
    return result;
}
function orderFormat(){
    var detTotal=document.forms['contractForm'].detTotal;
    var vat=document.forms['contractForm'].vat;
    var price=document.forms['contractForm'].price;
    var quantity=document.forms['contractForm'].quantity;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                tryNumberFormat(vat[i]);
                tryNumberFormat(price[i]);
                tryNumberFormat(detTotal[i]);
                tryNumberFormat(quantity[i]);
            }
        } else{
            tryNumberFormat(vat);
            tryNumberFormat(price);

            tryNumberFormat(detTotal);
            tryNumberFormat(quantity);
        }
    }
    vat=null;
    price=null;
    quantity=null;
    detTotal=null;
    caculateContract();
}
function createAppendix_old(contract,order){
    var form=null;
    var conId;
    form=document.forms['materialInContractForm'];
    conId=form.conId;
    var reqDetailId=form.reqDetailId;
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var matId = form.matId;
    var manyCon=false;
    var con='';
    if(matId==null || conId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                conIds+=','+conId[i].value;
                if(reqDetailId!=null) reqDetailIds+=','+reqDetailId[i].value;
                if(manyCon==false && con!='' && con!=conId[i].value) manyCon=true;
                else con=conId[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        conIds+=','+conId.value;
        con=conId.value;
        if(reqDetailId!=null) reqDetailIds+=','+reqDetailId.value;
    }
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u01A1n \u0111\u1EB7t h\u00E0ng ch\u1EC9 l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        if (reqDetailIds!='0') reqDetailIds=reqDetailIds.substring(2);
        contractForm(contract,con,null,true,order,ids, reqDetailIds);
    }
    matId=null;
    conId=null;
    reqDetailId=null;
    form=null;
    return false;
}
function createAppendix(contract,order){
    var form=document.forms['materialInContractForm'];
    var conId=form.conId_materialInContractForm;
    var reqDetailId=form.reqDetId_materialInContractForm;
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var matId = form.matId_materialInContractForm;
    var manyCon=false;
    var con='';
    
    
    if(matId.value=="" || matId.value=="0,") return false;
    ids=matId.value.substring(2);
    conIds=conId.value.substring(2);
    reqDetailIds=reqDetailId.value.substring(2);
    ids=ids.substring(0,ids.length-1);
    conIds=conIds.substring(0,conIds.length-1);
    reqDetailIds=reqDetailIds.substring(0,reqDetailIds.length-1);
    var t=conIds.split(',');
    for(var i=0;i<t.length;i++){
        if(manyCon==false && con!='' && con!=t[i]){
             manyCon=true;
             break;
        }
        else con=t[i];
    }
    
    var k=reqDetailIds.split(',');
    var reqDetailIds_temp='';
    var ind=-1;
    for(i=0;i<k.length;i++){
        ind=k[i].indexOf('_');
        if(ind>-1){
            reqDetailIds_temp+=','+k[i].substring(0,ind);
        }else{
            reqDetailIds_temp+=','+k[i];
        }
    }
    if(reqDetailIds_temp!=''){
        reqDetailIds=reqDetailIds_temp.substring(1);
    }
 
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u01A1n \u0111\u1EB7t h\u00E0ng ch\u1EC9 l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        contractForm(contract,con,null,true,order,ids, reqDetailIds);
    }
    matId=null;
    conId=null;
    reqDetailId=null;
    form=null;
    return false;
}
function requestCreateContract(kind){
    var form=document.forms['materialInContractForm'];
    var conId=form.conId_materialInContractForm;
    var reqDetailId=form.reqDetId_materialInContractForm;
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var matId = form.matId_materialInContractForm;
    var manyCon=false;
    var con='';
    
    
    if(matId.value=="" || matId.value=="0,") return false;
    ids=matId.value.substring(2);
    conIds=conId.value.substring(2);
    reqDetailIds=reqDetailId.value.substring(2);
    ids=ids.substring(0,ids.length-1);
    conIds=conIds.substring(0,conIds.length-1);
    reqDetailIds=reqDetailIds.substring(0,reqDetailIds.length-1);
    var t=conIds.split(',');
    for(var i=0;i<t.length;i++){
        if(manyCon==false && con!='' && con!=t[i]){
             manyCon=true;
             break;
        }
        else con=t[i];
    }
    
    var k=reqDetailIds.split(',');
    var reqDetailIds_temp='';
    var ind=-1;
    for(i=0;i<k.length;i++){
        ind=k[i].indexOf('_');
        if(ind>-1){
            reqDetailIds_temp+=','+k[i].substring(0,ind);
        }else{
            reqDetailIds_temp+=','+k[i];
        }
    }
    if(reqDetailIds_temp!=''){
        reqDetailIds=reqDetailIds_temp.substring(1);
    }
 
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u01A1n \u0111\u1EB7t h\u00E0ng ch\u1EC9 l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        contractForm(kind,con,null,true,order,ids, reqDetailIds);
    }
    
    if (ids!='0'){
        var besvVenId=0;    //document.getElementById('besvVenId_'+ids).value;
        var besvTenId=0;    //document.getElementById('besvTenId_'+ids).value;
        var url="contractForm.do?kind="+kind+"&tenId="+0+"&isnotresell=1";
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            form=document.forms['contractForm'];
            var orderMaterialSource=form.orderMaterialSource;
            if(orderMaterialSource!=null){
                orderMaterialSource.value="tenderplan";
                orderMaterialSourceChanged(orderMaterialSource);
                orderMaterialSource.disabled="true";
                orderMaterialSource=null;
                callAjax('orderMaterialSource.do?kind=2',null,null,function(data){
                    setAjaxData(data,'orderSourceTr');
                    tenderPlanCreateContract_GetCombos(besvTenId, besvVenId);
                });
            }else{
                tenderPlanCreateContract_GetCombos(besvTenId, besvVenId);
            }
        });
    }else{
        alert('Vui l\u00F2ng ch\u1ECDn nh\u00E0 cung c\u1EA5p');
        return false;
    }
    matId=null;
    conId=null;
    reqDetailId=null;
    form=null;
    return false;
}
function loadMaterialInTenderPlan(conId,tenId,venId,autoChoose){
    var url='materialListForContract.do?tenId='+tenId+'&venId='+venId;
    if(conId!=null) url+='&conId='+conId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listTenderPlanMaterialDataSpan');
        if(autoChoose==1){
            var list=document.forms['contractForm'].material;
            var ids='0';
            for (i=0;i<list.length;i++){
                ids+=','+list.options[i].value;
            }
            setSelectedContractMaterial(ids);
        }
    });
}
function setSelectedOrderMaterial(){
    var srcList=document.forms['contractForm'].orderMaterialSource;
    if(srcList.selectedIndex==-1 || srcList.selectedIndex==0) return false;
    var source=srcList.options[srcList.selectedIndex].value;
    srcList=null;
    var url='';
    if(source=='contract'){
        var contract=document.forms['contractForm'].parentId;
        if(contract.selectedIndex!=null) url+='orderContractMaterial.do?conId='+contract.options[contract.selectedIndex].value;
        else url+='orderContractMaterial.do?conId='+contract.value;
        contract=null;
    }else if(source=='tenderplan'){
        var tenId=document.forms['contractForm'].tenId;
        var venId=document.forms['contractForm'].venId;
        url+='orderTenderPlanMaterial.do?kind='+document.forms['contractForm'].kind.value;
        if(tenId.selectedIndex!=null) url+='&tenId='+tenId.options[tenId.selectedIndex].value+'&venId='+venId.options[venId.selectedIndex].value;
        else url+='&tenId='+tenId.value+'&venId='+venId.value;
        tenId=null;
        venId=null;
    }else if(source=='other'){
        callAjax('materialListToTenderPlan.do',null,null,function(data){
            showPopupForm(data);
            callAjax('materialNotInContracts.do','tenderPlanMaterialList',null,null);
        });
        return false;
    }
    var list=document.forms['contractForm'].material;
    if(list.selectedIndex==-1) return false;
    var materialId=list.options[list.selectedIndex].value;
    var matId = document.forms['contractForm'].matId;
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
    url+='&matId='+materialId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'contractMaterialHideDiv');
        var matTable=document.getElementById('orderSourceMaterialTable');
        var detTable=document.getElementById('contractDetailTable');
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
        
//        var detTotal=document.forms['contractForm'].detTotal;
//        var vat=document.forms['contractForm'].vat;
//        var price=document.forms['contractForm'].price;
//        var quantity=document.forms['contractForm'].quantity;
//        if(price!=null){
//            if (price.length!=null) {
//                for (i = 0; i < detTotal.length; i++) {
//                    tryNumberFormat(vat[i]);
//                    tryNumberFormat(price[i]);
//                    if(detTotal!=null) tryNumberFormat(detTotal[i]);
//                    if(quantity!=null) tryNumberFormat(quantity[i]);
//                }
//            } else{
//                tryNumberFormat(vat);
//                tryNumberFormat(price);
//                if(detTotal!=null) tryNumberFormat(detTotal);
//                if(quantity!=null) tryNumberFormat(quantity);
//            }
//        }
//        vat=null;
//        price=null;
//        quantity=null;
//        detTotal=null;
        orderFormat();
        
//        caculateContract();
    });
    matId=null;
    list=null;
    return false;
}
function setSelectedOrderMaterialNotInContract(ids,reqIds){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var reqLst=reqIds.split(',');
    var matIds=document.forms['contractForm'].matId;
    if(matIds!=null){
        ids='0';
        reqIds='0';
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
                if(isExisted==false){
                    ids+=","+idLst[i];
                    reqIds+=","+reqLst[i];
                }
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=matIds.value){
                    ids+=","+idLst[i];
                    reqIds+=","+reqLst[i];
                }
            }
        }
        matIds=null;
        if(ids=='0') return;
        ids=ids.substring(2);
        reqIds=reqIds.substring(2);
    }
    callAjax("orderMaterialNotInContract.do?matIds="+ids+'&reqIds='+reqIds+'&detIds='+0+'&conKind=notincontract',null,null,function(data){
        setAjaxData(data,'contractMaterialHideDiv');
        var matTable=document.getElementById('orderSourceMaterialTable');
        var detTable=document.getElementById('contractDetailTable');
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
        
        var detTotal=document.forms['contractForm'].detTotal;
        var vat=document.forms['contractForm'].vat;
        var price=document.forms['contractForm'].price;
        var quantity=document.forms['contractForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < price.length; i++) {
                    tryNumberFormat(vat[i]);


                    tryNumberFormat(price[i]);
                    if(detTotal!=null) tryNumberFormat(detTotal[i]);
                    if(quantity!=null) tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat);
                tryNumberFormat(price);
                if(detTotal!=null) tryNumberFormat(detTotal);
                if(quantity!=null) tryNumberFormat(quantity);
            }
        }
        vat=null;
        price=null;
        quantity=null;
        detTotal=null;
    });
}
function setSelectedOrderMaterialNotInContractA(detIds,ids,reqIds,matId,matIda,detId,kind){  
    //delOrderContractDetails();    
    callAjax("orderMaterialNotInContract.do?matIds="+ids+'&detIds='+detIds+'&detId='+detId+'&reqIds='+reqIds+'&matId='+matId+'&matIda='+matIda+'&kind='+kind,null,null,function(data){
         if(kind == 8){
            adjustmentForm(kind,document.forms['adjustmentForm'].conId.value);
        }else{
            contractForm(kind,document.forms['contractForm'].conId.value);
        }
    });
}

//Contract Follow
function loadContractFollowList(params){
    callAjaxEx('contractFollowList.do','ajaxContent','searchContractFollow.do','contractFollowList',params);
    return false;
}
function loadContractFollowListSort(params){
    callAjaxEx('searchContractFollow.do','contractFollowList',null,null,params);
    return false;
}
function getContractFollowListData(data){
    setAjaxData(data,'ajaxContent');
    loadContractFollows();
    return false;
}
function loadContractFollows(params){
    callAjaxEx('searchContractFollow.do','contractFollowList',null,null,params);
    return false;
}
function addContractFollow(contractFollowId,serviceType){
    var url="contractFollowForm.do";
    if (serviceType>=0) document.forms['contractFollowForm'].serviceType.value= serviceType;
    else serviceType=0;
    if(contractFollowId!=null) url=url+"?folId="+contractFollowId+"&serviceType="+serviceType;
    callAjax(url,"ajaxContent",null,null);
    return false;
}
function saveContractFollow() {
    if(scriptFunction=="saveContractFollow()") return false;
    var folNumber=document.forms[0].folNumber.value;
    var conId=document.forms[0].conId.value;
    var proId=document.forms[0].proId.value;
    var serviceType=document.forms[0].serviceType.value;
    var orgId=document.forms[0].orgId.value;
    if (folNumber.length==0) {
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o s\u1ED1 phi\u1EBFu theo d\u00F5i!");
    }
    else if (conId==0) {
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o s\u1ED1 H\u0110/\u0110\u0110H!");
    }
    else if(proId==0){
        alert("B\u1EA1n ch\u01B0a nh\u1EADp t\u00EAn D\u1EF1 \u00E1n!");
    }
    else if(serviceType==0){
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o Lo\u1EA1i h\u00ECnh d\u1ECBch v\u1EE5/h\u00E0ng h\u00F3a cung c\u1EA5p!");
    }
    else if(orgId==0){
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o B\u1ED9 ph\u1EADn ch\u1EE9c n\u0103ng theo d\u00F5i H\u0110!");
    }
    else {
        //callAjaxCheckError("addContractFollow.do",null,document.forms[0],getContractFollowListData);
        var folId = document.forms['contractFollowForm'].folId.value;
        scriptFunction="saveContractFollow()";
        callAjaxCheckError("addContractFollow.do",null,document.forms['contractFollowForm'],function(data){
            scriptFunction="";
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
            addContractFollow(folId);
    });
    }
    return false;
}
function searchContractFollow(){
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
        callAjax("searchContractFollow.do","contractFollowList",document.forms[0],null);
    }
    return false;
}
function delContractFollows(){
    var checkflag = false;
    var folId = document.forms['contractFollowForm'].folId;
    if (folId.length!=null) {
        for (i = 0; i < folId.length; i++) {
            if (folId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = folId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delContractFollow.do','contractFollowList',document.forms['contractFollowForm'],null);
        }
    }
    folId=null;
    return false;
}
function searchAdvContractFollowForm(){
    callAjax('searchAdvContractFollowForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvContractFollow(){
    callAjax('searchAdvContractFollow.do',null,document.forms['searchContractFollowForm'],getSearchAdvContractFollowData);
    hidePopupForm();
    return false;
}
function getSearchAdvContractFollowData(data){
    setAjaxData(data,'contractFollowList');
}
function contractUseChanged(list){
    if(list.selectedIndex==-1) return false;
    var url="contractIdList.do";
    url+="?conId="+list.options[list.selectedIndex].value;
    callAjax(url,null,null,function(data){
        var obj = eval("("+data+")");
        document.getElementById("createdTime").value=obj.createdTime;
        document.getElementById("startTime").value=obj.startTime;
        document.getElementById("endTime").value=obj.endTime;
    });
    list=null;
    return false;
}
function serviceTypes(){    
    var folId=document.forms['contractFollowForm'].folId.value;
    var serviceType=document.forms['contractFollowForm'].serviceType.value;
    addContractFollow(folId,serviceType);
    return false;
}
//Tender letter
function loadTenderLetterList(){
    callAjax('tenderLetterList.do',null,null,getTenderLetterListData);
    return false;
}
function getTenderLetterListData(data){
    setAjaxData(data,'ajaxContent');
    
    return false;
}
function loadTenderLetters(){
    callAjax("tenderLetters.do","tenderLetterList",null,null);
    return false;
}

function tenderLetterForm(tenId){
    var url="tenderLetterForm.do";
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tenderplanletter');
        url='listTenderLetterDetail.do';
        if(tenId!=null) url=url+"?tenId="+tenId;
        callAjax(url,'listTenderLetterVendorDataDiv',null,null);
        var letId = document.forms['tenderLetterForm'].letId.value;
        if(letId!=null) {
            loadAttachFileSystem(3,letId,'divAttachFileSystem1');
        }
    });
    return false;
}
function saveTenderLetter() {
    if(scriptFunction=="saveTenderLetter()") return false;
    var tenId=document.forms['tenderLetterForm'].tenId.value;
    scriptFunction="saveTenderLetter()";
    callAjaxCheckError("saveTenderLetter.do",null,document.forms['tenderLetterForm'],function(data){
        scriptFunction="";
        url='listTenderLetterDetail.do';
        if(tenId!=null) url=url+"?tenId="+tenId;
        callAjax(url,'listTenderLetterVendorDataDiv',null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        tenderLetterForm(tenId);
    });
    return false;
}
function delTenderLetters(){
    var checkflag = false;
    var reqId = document.forms['tenderLettersForm'].reqId;
    if(reqId==null) return false;
    if (reqId.length!=null) {
        for (i = 0; i < reqId.length; i++) {
            if (reqId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = reqId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTenderLetter.do','tenderLetterList',document.forms['tenderLettersForm'],null);
    reqId=null;
    return false;
}
function delTenderLetterVendor(){
    var checkflag = false;
    var detId = document.forms['tenderLetterForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTenderLetterDetail.do',null,document.forms['tenderLetterForm'],function(data){
        setAjaxData(data,'listTenderLetterVendorDataDiv');
    });
    detId=null;
    return false;
}

function getTenderLetterId(){
    var let=document.forms["tenderLetterForm"].letId;
    var letId='';
    if(let!=null){
        letId=let.value;
        let=null;
    }
    return letId;
}

//PriceComparison
function loadPriceComparisonList(){
    callAjax('priceComparisonList.do',null,null,getPriceComparisonListData);
    return false;
}

function getPriceComparisonListData(data){
    setAjaxData(data,'ajaxContent');
    
    return false;
}
function loadPriceComparisons(){
    callAjax("priceComparisons.do","priceComparisonList",null,null);
    return false;
}

function priceComparisonForm(tenId){
    var url="priceComparisonForm.do";    
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tenderplanpricecomparison');
        var pcId = document.forms['priceComparisonForm'].pcId.value;
        url='listPriceComparisonDetail.do';
        if(tenId!=null) url=url+"?tenId="+tenId+"&pcId="+pcId;
        callAjax(url,'listPriceComparisonVendorDataDiv',null,null);        
        if(pcId!=null) {
            loadAttachFileSystem(3,pcId,'divAttachFileSystem1');
        }
    });
    return false;
}
function savePriceComparison() {
    if(scriptFunction=="savePriceComparison()") return false;
    var tenId=document.forms['priceComparisonForm'].tenId.value;
    var pcId = document.forms['priceComparisonForm'].pcId.value;
    scriptFunction="savePriceComparison()";
    callAjaxCheckError("savePriceComparison.do",null,document.forms['priceComparisonForm'],function(data){
        scriptFunction="";
        url='listPriceComparisonDetail.do';
        if(tenId!=null) url=url+"?tenId="+tenId+"&pcId="+pcId;
        callAjax(url,'listPriceComparisonVendorDataDiv',null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        priceComparisonForm(tenId);
    });
    return false;
}
function delPriceComparisons(){
    var checkflag = false;
    var reqId = document.forms['priceComparisonsForm'].reqId;
    if(reqId==null) return false;
    if (reqId.length!=null) {
        for (i = 0; i < reqId.length; i++) {
            if (reqId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = reqId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPriceComparison.do','priceComparisonList',document.forms['priceComparisonsForm'],null);
    reqId=null;
    return false;
}
function delPriceComparisonVendor(){
    var checkflag = false;
    var detId = document.forms['priceComparisonForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPriceComparisonDetail.do',null,document.forms['priceComparisonForm'],function(data){
        setAjaxData(data,'listPriceComparisonVendorDataDiv');
    });
    detId=null;
    return false;
}

function getPriceComparisonId(){
    var let=document.forms["priceComparisonForm"].pcId;
    var pcId='';
    if(let!=null){
        pcId=let.value;
        let=null;
    }
    return pcId;
}
function printPriceComparison() {
    var tenId=document.forms['priceComparisonForm'].tenId;
    var pcId = document.forms['priceComparisonForm'].pcId;
    if(tenId!=null) callServer('printPriceComparison.do?tenId='+tenId.value+"&pcId="+pcId.value);
    tenId=null;
    return false;
}

//Tech Eval
function loadTechEvalList(){
    callAjax('techEvalList.do',null,null,getTechEvalListData);
    return false;
}
function getTechEvalListData(data){
    setAjaxData(data,'ajaxContent');
    loadTechEvals();
    return false;
}
function loadTechEvals(){
    callAjax("techEvals.do","techEvalList",null,null);
    return false;
}
function techEvalForm(tenId){
    var url="techEvalForm.do";
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tenderplantecheval');
        setEmployeeTechEval(tenId);
        callAjax("techEvalVendors.do?tenId="+tenId,"vendorListTE",null,null);
        var teId = document.forms['techEvalForm'].teId.value;
        if(teId!=null) {
            //Constants.ATTACH_FILE_TECHEVAL
            loadAttachFileSystem(7,teId,'divAttachFileSystem4');
        }
        
    });
    return false;
}
function saveTechEval() {
    var createdDate = document.forms['techEvalForm'].createdDate;
    var tenId = document.forms['techEvalForm'].tenId.value;
    if(createdDate!=null){
        for(var i=0;i<createdDate.length;i++){
            if(createdDate[i].value!=""){
                if(!isDate(createdDate[i].value,"dd/MM/yyyy")){
                    alert('Ng\u00E0y th\u00E1ng n\u0103m kh\u00F4ng ch\u00EDnh x\u00E1c (ng\u00E0y/th\u00E1ng/n\u0103m!!');
                    createdDate[i].focus();
                    createdDate=null;
                    return false;
                }
            }
        }
    }
    callAjaxCheckError("saveTechEval.do",null,document.forms['techEvalForm'],function(data){
        callAjax("techEvalVendors.do?tenId="+tenId,"vendorListTE",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        techEvalForm(tenId);
    });
    return false;
}
function setEmployeeTechEval(tenId) {
    var url="getEmployeeTechEval.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'techEvalEmployeeList',null,null);
    return false;
}
function setTechEvalDetail(terId) {
    var url="getEmployeeTechEval.do";
    if(terId!=null) url+="?terId="+terId;
    callAjax(url,'techEvalEmployeeList',null,null);
    return false;
}
function addTechEvalGroup(){
    var list=document.forms['techEvalForm'].employeeList;
    if(list==null || list.selectedIndex==-1) return false;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('techEvalGroupTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'techEvalEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'evalId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalEmployee';
    el.value=name;
    el.size=35;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalPosition';
    el.size=35;
    cell.appendChild(el);
    
    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalNote';
    el.size=35;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delTechEvalGroup(){
    var checkflag = false;
    var detId = document.forms['techEvalForm'].techEvalEmployeeChk;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTechEvalGroup.do',null,document.forms['techEvalForm'],function(data){
        {
                var tbl=document.getElementById('techEvalGroupTbl');
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
function addTechEvalVendor(terId,venId){
    var url="techEvalDetailForm.do";
    if(terId!=null) url=url+"?terId="+terId+"&venId="+venId;
    callAjax(url,null,null,function(data){
        showPopupFormLoc(data);
        url='listTechEvalDetail.do';
        if(terId!=null) url=url+"?terId="+terId+"&venId="+venId;
        callAjax(url,'listTechEvalDetailDataDiv',null,null);
    });
    return false;
}
function saveTechEvalDetail() {
    var tenId=document.forms['techEvalForm'].tenId.value;
    callAjaxCheckError("saveTechEvalDetail.do",null,document.forms['techEvalDetailForm'],function(data){
        hidePopupForm();
        callAjax("techEvalVendors.do?tenId="+tenId,"vendorListTE",null,null);
    });
    return false;
}

//TechClarification
function loadTechClarificationList(){
    callAjax('techClarificationList.do',null,null,getTechClarificationListData);
    return false;
}
function getTechClarificationListData(data){
    setAjaxData(data,'ajaxContent');
    loadTechClarifications();
    return false;
}
function loadTechClarifications(){
    callAjax("techClarifications.do","techClarificationList",null,null);
    return false;
}
function techClarificationFrm(terId){
    var url="techClarificationForm.do";
    var terId = document.forms['techEvalDetailForm'].terId.value;
    var venId = document.forms['techEvalDetailForm'].venId.value;
    if(terId!=null) url=url+"?terId="+terId+"&venId="+venId;
    callAjax(url,null,null,function(data){
        showPopupFormById("popupDialog",data);
        callAjax("techClarificationDisciplines.do","disciplineList",null,null);
    });
    
    return false;
}
function saveTechClarification() {
    var createdDate = document.forms['techClarificationForm'].createdDate;
    var tenId = document.forms['techClarificationForm'].tenId;
    if(createdDate!=null){
        for(var i=0;i<createdDate.length;i++){
            if(createdDate[i].value!=""){
                if(!isDate(createdDate[i].value,"dd/MM/yyyy")){
                    alert('Ng\u00E0y th\u00E1ng n\u0103m kh\u00F4ng ch\u00EDnh x\u00E1c (ng\u00E0y/th\u00E1ng/n\u0103m!!');
                    createdDate[i].focus();
                    createdDate=null;
                    return false;
                }
            }
        }
    }
    callAjaxCheckError("saveTechClarification.do",null,document.forms['techClarificationForm'],function(data){
        if(data=="success") callAjax("techClarificationDisciplines.do","disciplineList",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}
function delTechClarificationDiscipline(){
    var checkflag = false;
    var tclId = document.forms['techClarificationForm'].tclId;
    if (tclId.length!=null) {
        for (i = 0; i < tclId.length; i++) {
            if (tclId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = tclId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delTechClarificationDiscipline.do',null,document.forms['techClarificationForm'],function(data){
                if(data=="success") callAjax("techClarificationDisciplines.do","disciplineList",null,null);
            });
        }
    }
    tclId=null;
    return false;
}
function setEmployeeTechClarification(tcId) {
    var url="getEmployeeTechClarification.do";
    if(tcId!=null) url+="?tcId="+tcId;
    callAjax(url,'techClarificationEmployeeList',null,null);
    return false;
}
function setTechClarificationDetail(terId) {
    var url="getEmployeeTechClarification.do";
    if(terId!=null) url+="?terId="+terId;
    callAjax(url,'techClarificationEmployeeList',null,null);
    return false;
}
function addTechClarificationDetail(tclId){
    var url="techClarificationDetailForm.do";
    if(tclId!=null) url=url+"?tclId="+tclId;
    callAjax(url,null,null,function(data){
        showPopupFormById("popupDialog",data);
        url='listTechClarificationDetail.do';
        if(tclId!=null) url=url+"?tclId="+tclId;


        callAjax(url,'listTechClarificationDetailDataDiv',null,null);
    });
    
    
    return false;
}
function saveTechClarificationDetail() {
    callAjaxCheckError("saveTechClarificationDetail.do",null,document.forms['techClarificationDetailForm'],function(data){
        if(data=="success"){
            callAjax("listTechClarificationDetail.do","listTechClarificationDetailDataDiv",null,null);
            callAjax("techClarificationDisciplines.do","disciplineList",null,null);
            document.forms['techClarificationDetailForm'].subcategory.value=null;
            document.forms['techClarificationDetailForm'].reference.value=null;
            document.forms['techClarificationDetailForm'].clarification.value=null;
            document.forms['techClarificationDetailForm'].supplierReply.value=null;
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        }
    });
    return false;
}
function delTechClarificationDetail(){
    var checkflag = false;
    var detId = document.forms['techClarificationDetailForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delTechClarificationDetail.do',null,document.forms['techClarificationDetailForm'],function(data){
                if(data=="success") callAjax("listTechClarificationDetail.do","listTechClarificationDetailDataDiv",null,null);
            });
        }
    }
    detId=null;
    return false;
}
function hideWindows(){
    hidePopupForm();
    return false;
}
//TENDER PLAN
function loadTenderPlanList(params){
    callAjaxEx('tenderPlanList.do','ajaxContent','searchTenderPlan.do','tenderPlanList',params);
    return false;
}
function loadTenderPlanListSort(params){
    //phan trang loi: callAjaxEx('tenderplans.do','tenderPlanList',null,null,params);
    callAjaxEx('searchTenderPlan.do','tenderPlanList',null,null,params);
    return false;
}
//function loadTenderPlanListSort(params){
//    callAjaxEx('tenderplans.do','tenderPlanList',null,null,params);
//    return false;
//}
function getTenderPlanListData(data){
    setAjaxData(data,'ajaxContent');
    loadTenderPlans();
    return false;
}
function loadTenderPlans(){
    return callAjax("tenderplans.do","tenderPlanList",null,null);
}
function tenderPlanForm(tenId){
    if(tenId!=null) getTenderPlanTabs(function(){
        loadTenderPlan(tenId);
    });
    else callAjax('tenderPlanForm.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        var currentTime = new Date();
        var date=currentTime.getDate();
        var month=currentTime.getMonth() + 1;
        if(date<10) date='0'+date;
        if(month<10) month='0'+month;
        document.getElementById('createdDateTenderPlan').value=date+'/'+month+'/'+currentTime.getFullYear();
        getTenderPlanDetail();
        setEmployeeTenderPlan();
        setCertificateTenderPlan();
        setVendorTenderPlan();        
    });
    return false;
}
function setEmployeeTenderPlan(tenId) {
    var url="getEmployeeTenderPlan.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'tenderPlanEmployeeList',null,null);
    return false;
}
function setCertificateTenderPlan(tenId) {
    var url="getCertificateTenderPlan.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'tenderPlanCertificateList',null,null);
    return false;
}
function setVendorTenderPlan(tenId) {
    var url="getVendorTenderPlan.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'tenderPlanVendorList',null,null);
    return false;

}
function getTenderPlanDetail(tenId) {
    url='listTenderPlanDetail.do';
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listTenderPlanMaterialDataDiv');
        var matPrice=document.forms['tenderPlanForm'].matPrice;
        var matTotal=document.forms['tenderPlanForm'].matTotal;
        var requestQuantity=document.forms['tenderPlanForm'].requestQuantity;
        var matQuantity=document.forms['tenderPlanForm'].matQuantity;
        if(matPrice!=null){
            if (matPrice.length!=null) {
                for (i = 0; i < matPrice.length; i++) {
                    tryNumberFormat(matPrice[i]);
                    tryNumberFormat(matTotal[i]);
                    tryNumberFormat(requestQuantity[i],"VND");
                    tryNumberFormat(matQuantity[i],"VND");
                }
            } else{
                tryNumberFormat(matPrice);
                tryNumberFormat(matTotal);
                tryNumberFormat(requestQuantity,"VND");
                tryNumberFormat(matQuantity,"VND");
            }
        }
        matPrice=null;
        matTotal=null;
    });
    return false;
}
function saveTenderPlan() {
    if(scriptFunction=="saveTenderPlan()") return false;
    var tenderNumber = document.forms['tenderPlanForm'].tenderNumber;
    var tenId = document.forms['tenderPlanForm'].tenId.value;
    if(tenderNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 k\u1EBF ho\u1EA1ch");
        tenderNumber.focus();
        tenderNumber=null;
        return false;
    }
    tenderNumber=null;
    var createdDate = document.forms['tenderPlanForm'].createdDate;
    if(createdDate.value==''){
        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y l\u1EADp");
        createdDate.focus();
        createdDate=null;
        return false;
    }
    var handleEmp=document.forms['tenderPlanForm'].handleEmp;
    if(handleEmp.selectedIndex==-1){
        alert("Vui l\u00F2ng ch\u1ECDn nh\u00E2n vi\u00EAn \u0111\u1EB7t h\u00E0ng");
        handleEmp.focus();
        handleEmp=null;
        return false;
    }
    handleEmp=null;
    //callAjaxCheckError("saveTenderPlan.do",null,document.forms['tenderPlanForm'],getTenderPlanListData);
    var financialPreTax=document.forms['tenderPlanForm'].financialPreTax;
    var matPrice=document.forms['tenderPlanForm'].matPrice;
    var matTotal=document.forms['tenderPlanForm'].matTotal;
    if(matPrice!=null){
        if (matPrice.length!=null) {
            for (i = 0; i < matPrice.length; i++) {
                reformatNumberMoney(matPrice[i]);
                reformatNumberMoney(matTotal[i]);
            }
        } else{
            reformatNumberMoney(matPrice);
            reformatNumberMoney(matTotal);
        }
    }
    if(financialPreTax!=null) reformatNumberMoney(financialPreTax);
    financialPreTax=null;
    matPrice=null;
    matTotal=null;
    scriptFunction="saveTenderPlan()";
    callAjaxCheckError("saveTenderPlan.do",null,document.forms['tenderPlanForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        tenderPlanForm(tenId);
    });
    return false;
}
function getTenderPlanTabs(handle){
    callAjax('tenderPlanTabs.do',null,null,function(data){
        setAjaxData(data,'ajaxContent');
        addTenderPlanTabs();
        handle();
    });
}
function addTenderPlanTabs(){
    displayTabs("tenderPlanTabs","tenderPlanTabChild",tenderPlanTabHandle);
}
function tenderPlanTabHandle(child){
    //    if(child.isLoaded=='true') return true;
    var tenId=getTenderPlanId();
    if(tenId=='') return true;
    if(child.id=='tenderplanTabChild'){
        loadTenderPlan(tenId);
    }else if(child.id=='tenderplanbidclosing'){
        loadBidClosing(tenId);
    }else if(child.id=='tenderplanbidopening'){
        loadBidOpening(tenId);
    }else if(child.id=='tenderplanletter'){
        tenderLetterForm(tenId);
    }else if(child.id=='tenderplantecheval'){
        techEvalForm(tenId);
    }else if(child.id=='tenderplancomeval'){
        comEvalForm(tenId);
    }else if(child.id=='tenderplancomevalmaterial'){
        comEvalMaterialForm(tenId);
    }else if(child.id=='tenderplanpricecomparison'){
        priceComparisonForm(tenId);
    }else if(child.id=='tenderplanbidevalsum'){
        bidEvalSumForm(tenId);
    }
    child.isLoaded='true';
    child=null;
}
function getTenderPlanId(){
    var ten=document.getElementById('tenIdHidden');
    var tenId='';
    if(ten!=null){
        tenId=ten.value;
        ten=null;
    }
    return tenId;
}
function loadTenderPlan(tenId){
    callAjax('tenderPlanForm.do?tenId='+tenId,null,null,function(data){        
        setAjaxData(data,'tenderplandetail');
        var tenIds=document.forms['tenderPlanForm'].tenId.value;
        if (tenIds>0)tenId = tenIds;
        showhide2('tenderPlanFormTitle',true);
        setTenderPlanId(tenId);
        getTenderPlanDetail(tenId);
        setEmployeeTenderPlan(tenId);
        setCertificateTenderPlan(tenId);
        setVendorTenderPlan(tenId);
        loadAttachFileSystem(2,tenId);
        
        var financialPreTax=document.forms['tenderPlanForm'].financialPreTax;
        if(financialPreTax!=null){
            tryNumberFormat(financialPreTax);
            financialPreTax=null;
        }
        
        var kind=document.forms['tenderPlanForm'].evalKind;
        if(kind!=null){
            if(kind.value=='0') hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplancomevalmaterial');
            else hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplancomeval');
            kind=null;
        }
        var bidDeadline=document.forms['tenderPlanForm'].bidDeadline.value;
        var currentTime = new Date();
        var date=currentTime.getDate();
        var month=currentTime.getMonth() + 1;
        var year=currentTime.getFullYear();
        if(date<10) date='0'+date;
        if(month<10) month='0'+month;
        var dif=compareDates(date+'/'+month+'/'+currentTime.getFullYear(),"dd/MM/yyyy",bidDeadline,"dd/MM/yyyy");
        if(dif==3){
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidclosing');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidopening');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplantecheval');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplancomeval');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplancomevalmaterial');            
            //hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidevalsum');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanpricecomparison');
        }else{
            var form=document.forms['tenderPlanForm'].form.value;
            if(form==2){//chao hang canh tranh = fax
                hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidclosing');
                hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidopening');
            }
            // Them moi Bao cao danh gia
            //hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanletter');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidclosing');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanbidopening');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplantecheval');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplancomeval');
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplancomevalmaterial'); 
            hideTab('tenderPlanTabs','tenderPlanTabChild','tenderplanpricecomparison');
            //
        }
    });
}
function setTenderPlanId(tenId){
    var tender=document.getElementById('tenIdHidden');
    if(tender!=null){
        tender.value=tenId;
        tender=null;
    }
}
function addTenderPlanEvalGroup(){
    var list=document.forms['tenderPlanForm'].employeeList;
    if(list==null || list.selectedIndex==-1) return false;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    var pos_org=document.getElementById('emp_'+id);
    if(pos_org!=null) pos_org=pos_org.value
    else pos_org='';
    list=null;
    var tbl=document.getElementById('tenderPlanEvalGroupTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'tenderPlanEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'evalId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalEmployee';
    el.value=name;
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalPosition';
    el.value=pos_org;
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalNote';
    el.size=30;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function addTenderPlanCertificate(){
    var list=document.forms['tenderPlanForm'].certificateList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    if(document.getElementById('cerId_'+id)!=null){
        alert('Ch\u1EE9ng ch\u1EC9 \u0111\u00E3 t\u1ED3n t\u1EA1i');
        return false;
    }
    var tbl=document.getElementById('tenderPlanCertificateTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'tenderPlanCertificateChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'tcId';
    el.value='0';
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'cerId';
    el.value=id;
    el.id="cerId_"+id;
    cell.appendChild(el);
    
    var cell = row.insertCell(1);
    cell.align="center";
    var span = document.createElement('span');
    span.innerHTML=name;
    cell.appendChild(span);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
//function addTenderPlanVendor(){
//    var list=document.forms['tenderPlanForm'].vendorList;
//    if(list==null) return;
//    var id=list.options[list.selectedIndex].value;
//    list=null;
//    var selId=document.forms['tenderPlanForm'].vendor;
//    if(selId!=null){
//        if(selId.length!=null){
//            for (i=0;i<selId.length;i++) {
//                if(id==selId[i].value){
//                    alert('Nh\u00E0 cung c\u1EA5p \u0111\u00E3 t\u1ED3n t\u1EA1i');
//                    return false;
//                }
//            }
//        }else if(id==selId.value){
//            alert('Nh\u00E0 cung c\u1EA5p \u0111\u00E3 t\u1ED3n t\u1EA1i');
//            return false;
//        }
//    }
//    selId=null;
//    callAjax("getVendorForTenderPlan.do?venId="+id,null,null,function(data){
//        setAjaxData(data,'tenderPlanVendorHideDiv');
//        var venTable=document.getElementById('tenderPlanVendorDetailTbl');
//        var detTable=document.getElementById('tenderPlanVendorTbl');
//        if(venTable.tBodies[0]==null || detTable.tBodies[0]==null){
//            venTable=null;
//            detTable=null;
//            return;
//        }
//        for( var i=venTable.tBodies[0].rows.length-1;i>=0;i--) {
//            detTable.tBodies[0].appendChild(venTable.tBodies[0].rows[i]);
//        }
//        venTable=null;
//        detTable=null;
//    });
//    return false;
//}
function materialToTenderPlan(title){
    popupName=title;
    callAjax('materialListToTenderPlan.do',null,null,function(data){
        showPopupForm(data);
        var list=document.forms['tenderPlanForm'].cbxMaterialSource;
        document.getElementById('materialSource').value=list.options[list.selectedIndex].value;
        list=null;
//        callAjax('materialNotInContracts.do?reload=0','tenderPlanMaterialList',null,null);
        searchMaterialTenderPlan();
    });
    return false;
}
function setMaterialTenderPlanSelected(){
    var matId = document.forms['tenderplanmaterialForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['tenderplanmaterialForm'].matNameHidden;
    var matCodeHidden = document.forms['tenderplanmaterialForm'].matCodeHidden;
    var reqId = document.forms['tenderplanmaterialForm'].reqId;
    var conDetId = document.forms['tenderplanmaterialForm'].conDetId;
    var tbl=document.getElementById('materialTenderPlanSelectedTbl');
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
                
                el = document.createElement('input');
                el.type = 'hidden';
                el.name = 'materialReqId';
                el.id="materialReqId";
                el.value=reqId[i].value;
                cell.appendChild(el);
                
                el = document.createElement('input');
                el.type = 'hidden';
                el.name = 'materialConDetId';
                el.id="materialConDetId";
                el.value=conDetId[i].value;
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
        

            el = document.createElement('input');
            el.type = 'hidden';
            el.name = 'materialReqId';
            el.id="materialReqId";
            el.value=reqId.value;
            cell.appendChild(el);
            
            el = document.createElement('input');
            el.type = 'hidden';
            el.name = 'materialConDetId';
            el.id="materialConDetId";
            el.value=conDetId.value;
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
function chooseMaterialTenderPlanSelected(){
    var selId=document.forms['materialTenderPlanSelectedForm'].materialSelectedChk;
    var materialReqId=document.forms['materialTenderPlanSelectedForm'].materialReqId;
    var materialConDetId=document.forms['materialTenderPlanSelectedForm'].materialConDetId;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var ids='0';
    var reqIds='0';
    var conDetIds='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            ids+=','+selId[i].value;
            reqIds+=','+materialReqId[i].value;
            conDetIds+=','+materialConDetId[i].value;
        }
    }else{
        ids+=','+selId.value;
        reqIds+=','+materialReqId.value;
        conDetIds+=','+materialConDetId.value;
    }
    if(ids!='0'){
        ids=ids.substring(2);
        reqIds=reqIds.substring(2);
        conDetIds=conDetIds.substring(2);
    }
    if(document.getElementById('tenderPlanMaterialHideDiv')!=null) setSelectedTenderPlanMaterial(ids,reqIds,conDetIds);
    else if(document.getElementById('contractMaterialHideDiv')!=null) setSelectedOrderMaterialNotInContract(ids,reqIds);
    hidePopupForm();
}
function setSelectedTenderPlanMaterial(ids,reqIds,conDetIds){    
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var reqLst=reqIds.split(',');
    var matIds=document.forms['tenderPlanForm'].matId;
    var reqMatIds=document.forms['tenderPlanForm'].reqId;
    if(matIds!=null){
        ids='0';
        reqIds='0';
        if(matIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<matIds.length;j++){
                    if(idLst[i]==matIds[j].value && reqLst[i]==reqMatIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false){
                    ids+=","+idLst[i];
                    reqIds+=","+reqLst[i];
                }
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]==matIds.value && idLst[i]==reqMatIds.value){
                    continue;
                }else{
                    ids+=","+idLst[i];
                    reqIds+=","+reqLst[i];
                }
            }
        }
        matIds=null;
        reqMatIds=null;
        if(ids=='0') return;
        ids=ids.substring(2);
        reqIds=reqIds.substring(2);
    }
    var url = "listTenderPlanMaterial.do?matIds="+ids+'&reqIds='+reqIds;
    if(conDetIds!=null && conDetIds!="" && conDetIds!="0") url+= '&conDetIds='+conDetIds;
    callAjax(url,null,null,function(data){
        var matPrice=document.forms['tenderPlanForm'].matPrice;
        var matTotal=document.forms['tenderPlanForm'].matTotal;
        if(matPrice!=null){
            if (matPrice.length!=null) {
                for (i = 0; i < matPrice.length; i++) {
                    tryNumberFormat(matPrice[i]);
                    tryNumberFormat(matTotal[i]);
                }
            } else{
                tryNumberFormat(matPrice);
                tryNumberFormat(matTotal);
            }
        }
        
        setAjaxData(data,'tenderPlanMaterialHideDiv');
        var matTable=document.getElementById('tenderPlanMaterialTable');
        var detTable=document.getElementById('tenderPlanDetailTable');
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
        
        matPrice=document.forms['tenderPlanForm'].matPrice;
        matTotal=document.forms['tenderPlanForm'].matTotal;
        if(matPrice!=null){
            if (matPrice.length!=null) {
                for (i = 0; i < matPrice.length; i++) {
                    tryNumberFormat(matPrice[i]);
                    tryNumberFormat(matTotal[i]);
                }
            } else{
                tryNumberFormat(matPrice);
                tryNumberFormat(matTotal);
            }
        }
        matPrice=null;
        matTotal=null;
        caculateTenderPlanPreTax();
    });
}
function caculateTenderPlanPreTax(){
    var financialPreTax=document.forms['tenderPlanForm'].financialPreTax;
    var matTotal=document.forms['tenderPlanForm'].matTotal;
    var sum=0;
    if(matTotal!=null){
        if (matTotal.length!=null) {
            for (i = 0; i < matTotal.length; i++) {
                sum+=reformatNumberMoneyString(matTotal[i].value)*1;
            }
        } else{
            sum+=reformatNumberMoneyString(matTotal.value)*1;
        }
    }
    financialPreTax.value=sum;
    tryNumberFormat(financialPreTax);
    financialPreTax=null;
    return false;
}
function delTenderPlanVendor(){
    var checkflag = false;
    var detId = document.forms['tenderPlanForm'].tenderPlanVendorChk;

    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTenderPlanVendor.do',null,document.forms['tenderPlanForm'],function(data){
        setAjaxData(data,'tenderPlanVendorList');
    });
    detId=null;
    return false;
}
function delTenderPlanEvalGroup(){
    var checkflag = false;
    var detId = document.forms['tenderPlanForm'].tenderPlanEmployeeChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
                

            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTenderPlanEvalGroup.do',null,document.forms['tenderPlanForm'],function(data){
        setAjaxData(data,'tenderPlanEmployeeList');
    });
    detId=null;
    return false;
}
function delTenderPlanCertificate(){
    var checkflag = false;
    var detId = document.forms['tenderPlanForm'].tenderPlanCertificateChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
                

            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTenderPlanCertificate.do',null,document.forms['tenderPlanForm'],function(data){
        setAjaxData(data,'tenderPlanCertificateList');
    });
    detId=null;
    return false;
}
function deleteTenderPlanMaterial(){
    var checkflag = false;
    var detId = document.forms['tenderPlanForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) 
            callAjaxCheckError('delTenderPlanDetail.do',null,document.forms['tenderPlanForm'],function(data){
            //            setAjaxData(data,'listTenderPlanMaterialDataDiv');
            if(data=="deleted"){
//                var tenId=document.forms['tenderPlanForm'].tenId;
//                tenderPlanForm(tenId.value);
//                tenId=null;
                var tbl=document.getElementById('tenderPlanDetailTable');
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
                caculateTenderPlanPreTax();
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function searchMaterialTenderPlan(params){
    var form=document.forms['selectMaterialTenderPlanForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    var materialSource=document.getElementById('materialSource').value;
    url="searchMaterialTenderPlan.do?"+params+"&materialSource="+materialSource;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'tenderPlanMaterialList');
        var matId = document.forms['tenderplanmaterialForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialTenderPlanSelectedForm'].materialSelectedChk;
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
function delMaterialTenderPlan(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialTenderPlanSelectedTbl');
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
    var matId = document.forms['tenderplanmaterialForm'].matId;
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
function searchTenderPlan(){
    var checkflag = true;
    var form=document.forms['searchSimpleTenderPlanForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchTenderPlan.do","tenderPlanList",form,null);
    form=null;

    return false;
}
function exportTenderPlan(){
    var form=document.forms['searchSimpleTenderPlanForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    var url="tenderPlanReportPrint.do?searchid="+form.searchid.value+"&searchvalue="+encodeURIComponent(form.searchvalue.value);
    form=null;
    callServer(url);
    return false;
}
function delTenderPlans(){
    var checkflag = false;
    var tenId = document.forms['tenderplansForm'].tenId;
    if(tenId==null) return false;
    if (tenId.length!=null) {
        for (i = 0; i < tenId.length; i++) {
            if (tenId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = tenId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delTenderPlan.do',null,document.forms['tenderplansForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else setAjaxData(data,'tenderPlanList');
    });
    tenId=null;
    return false;
}
function searchAdvTenderPlanForm(){
    callAjax('searchAdvTenderPlanForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvTenderPlan(){
    callAjax('searchAdvTenderPlan.do','tenderPlanList',document.forms['searchTenderPlanForm'],null);
    hidePopupForm();
    return false;
}
function printTenderPlan(){
    var tenId=getTenderPlanId();
    callServer('tenderPlanPrint.do?tenId='+tenId);
    tenId=null;
}
function printTenderPlanMaterial(){
    var tenId=getTenderPlanId();
    callServer('tenderPlanMaterialPrint.do?tenId='+tenId);
    tenId=null;
}
function createTenderPlan_old(source){
    var form=null;
    if(source=='contract'){
        form=document.forms['materialInContractForm'];
    }else if(source=='order'){
        form=document.forms['materialInOrderForm'];
    }else if(source=='notincontract'){
        form=document.forms['materialNotInContractForm'];
    }else if(source=='principleexpire'){
        form=document.forms['materialInContractPrincipleExpireForm'];
    }else if(source=='principle'){
        form=document.forms['materialInContractPrincipleForm'];
    }
    var ids = '0';
    var reqIds='0';
    var matId = form.matId;
    var reqId = form.reqId;
    if(matId==null || reqId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                reqIds+=','+reqId[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        reqIds+=','+reqId.value;
    }
    if (ids!='0'){
        callAjax('tenderPlanForm.do?reqIds='+reqIds,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            form=document.forms['tenderPlanForm'];
            var materialSource=form.cbxMaterialSource;
            if(source=='contract'){
                materialSource.value="2";
            }else if(source=='order'){
                materialSource.value="3";
            }else if(source=='notincontract'){
                materialSource.value="4";
            }else if(source=='principleexpire'){
                materialSource.value="1";
            }else if(source=='principle'){
                materialSource.value="5";
            }
//            materialSource.disabled="true";
            materialSource=null;
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateTenderPlan').value=date+'/'+month+'/'+currentTime.getFullYear();
            setEmployeeTenderPlan();
            setCertificateTenderPlan();
            setVendorTenderPlan();
            url='listTenderPlanDetail.do';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listTenderPlanMaterialDataDiv');
                setSelectedTenderPlanMaterial(ids.substring(2),reqIds.substring(2));
            });
        });
    }
    matId=null;
    reqId=null;
    form=null;
    return false;
}
function createTenderPlan_old1(source){
    var form=null;
    var matId = null;
    var reqId = null;
    var conId = null;
    var conDetId = null;
    if(source=='contract'){
        form=document.forms['materialInContractForm'];
        matId = form.matId_materialInContractForm;
        reqId = form.reqId_materialInContractForm;
        conDetId = form.conDetId_materialInContractForm;
    }else if(source=='order'){
        form=document.forms['materialInOrderForm'];
        matId = form.matId_materialInOrderForm;
        reqId = form.reqId_materialInOrderForm;
        conDetId = form.conDetId_materialInOrderForm;
    }else if(source=='notincontract'){
        form=document.forms['materialNotInContractForm'];
        matId = form.matId_materialNotInContractForm;
        reqId = form.reqId_materialNotInContractForm;
        conDetId = form.conDetId_materialNotInContractForm;
    }else if(source=='principleexpire'){
        form=document.forms['materialInContractPrincipleExpireForm'];
        matId = form.matId_materialInContractPrincipleExpireForm;
        reqId = form.reqId_materialInContractPrincipleExpireForm;
        conDetId = form.conDetId_materialInContractPrincipleExpireForm;
    }else if(source=='principle'){
        form=document.forms['materialInContractPrincipleForm'];
        matId = form.matId_materialInContractPrincipleForm;
        reqId = form.reqId_materialInContractPrincipleForm;
        conId = form.conId_materialInContractPrincipleForm.value.substring(2);
        conId = conId.substring(0,conId.length-1);
        conDetId = form.conDetId_materialInContractPrincipleForm;
    }
    var ids = '0';
    var reqIds='0';
    var conDetIds='0';
    if(matId==null || reqId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                reqIds+=','+reqId[i].value;
                conDetIds+=','+conDetIds[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        reqIds+=','+reqId.value;
        conDetIds+=','+conDetIds.value;
    }
    
    if(matId.value=="" || matId.value=="0,") return false;
    ids=matId.value.substring(2);
    reqIds=reqId.value.substring(2);
    conDetIds=conDetId.value.substring(2);
    ids=ids.substring(0,ids.length-1);
    reqIds=reqIds.substring(0,reqIds.length-1);
    conDetIds=conDetIds.substring(0,conDetIds.length-1);
    
    if (ids!='0'){
        callAjax('tenderPlanForm.do?reqIds='+reqIds,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            form=document.forms['tenderPlanForm'];
            var materialSource=form.cbxMaterialSource;
            if(source=='contract'){
                materialSource.value="2";
            }else if(source=='order'){
                materialSource.value="3";
            }else if(source=='notincontract'){
                materialSource.value="5";
            }else if(source=='principleexpire'){
                materialSource.value="1";
            }else if(source=='principle'){
                materialSource.value="4";
            }
//            materialSource.disabled="true";
            materialSource=null;
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateTenderPlan').value=date+'/'+month+'/'+currentTime.getFullYear();
            setEmployeeTenderPlan();
            setCertificateTenderPlan();
            setVendorTenderPlan();
            url='listTenderPlanDetail.do';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listTenderPlanMaterialDataDiv');
                var idtemp = ids.split(',');
                ids='0';
                for(i=idtemp.length-1;i>=0;i--){
                    ids+=','+idtemp[i];
                }   
                if(ids!='0') ids=ids.substring(2);
                setSelectedTenderPlanMaterial(ids,reqIds,conDetIds);
            });
        });
    }
    matId=null;
    reqId=null;
    form=null;
    return false;
}
function createTenderPlan(source){
    var form=null;
    var matId = null;
    var reqId = null;
    var conId = null;
    var conDetId = null;
    var ids = '0';
    var reqIds='0';
    var conDetIds='0';

    reqIds+=','+getReqIdsForCreateOrder();
    conDetIds+=','+getConDetIdsForCreateTenderPlan();
    ids+=','+getMatIdsForCreateOrder();
    
    if(source=='contract'){
        form=document.forms['materialInContractForm'];
        matId = form.matId_materialInContractForm;
        reqId = form.reqId_materialInContractForm;
        conDetId = form.conDetId_materialInContractForm;
    }else if(source=='order'){
        form=document.forms['materialInOrderForm'];
        matId = form.matId_materialInOrderForm;
        reqId = form.reqId_materialInOrderForm;
        conDetId = form.conDetId_materialInOrderForm;
    }else if(source=='notincontract'){
        form=document.forms['materialNotInContractForm'];
        matId = form.matId_materialNotInContractForm;
        reqId = form.reqId_materialNotInContractForm;
//        conDetId = form.conDetId_materialNotInContractForm;
        
        if(reqIds!='0') reqIds=reqIds.substring(0,reqIds.length-1);
//        if(conDetIds!='0') conDetIds=conDetIds.substring(0,conDetIds.length-1);
        if(ids!='0') ids=ids.substring(0,ids.length-1);
        if(matId==null || reqId==null) return false;
        if (matId.length!=null) {
            for (i = 0; i < matId.length; i++) {
//                if (matId[i].checked == true){
                    ids+=','+matId[i].value;
                    reqIds+=','+reqId[i].value;
//                    conDetIds+=','+conDetIds[i].value;
//                }
            }
//        } else if(matId.checked == true){
        } else{
            ids+=','+matId.value;
            reqIds+=','+reqId.value;
//            conDetIds+=','+conDetIds.value;
        }
//        if(ids!="0") ids = ids.substring(0,ids.length-1);
    }else if(source=='principleexpire'){
        form=document.forms['materialInContractPrincipleExpireForm'];
        matId = form.matId_materialInContractPrincipleExpireForm;
        reqId = form.reqId_materialInContractPrincipleExpireForm;
        conDetId = form.conDetId_materialInContractPrincipleExpireForm;
    }else if(source=='principle'){
        form=document.forms['materialInContractPrincipleForm'];
        matId = form.matId_materialInContractPrincipleForm;
        reqId = form.reqId_materialInContractPrincipleForm;
        conId = form.conId_materialInContractPrincipleForm.value.substring(2);
        conId = conId.substring(0,conId.length-1);
        conDetId = form.conDetId_materialInContractPrincipleForm;
    }else if(source=='adjustmentcontract'){
        form=document.forms['materialInAdjustmentContractForm'];
        matId = form.matId_materialInAdjustmentContractForm;
        reqId = form.reqId_materialInAdjustmentContractForm;
        conId = form.conId_materialInAdjustmentContractForm.value.substring(2);
        conId = conId.substring(0,conId.length-1);
        conDetId = form.conDetId_materialInAdjustmentContractForm;
    }
    
//    if(reqIds!='0') reqIds=reqIds.substring(0,reqIds.length-1);
//    if(conDetIds!='0') conDetIds=conDetIds.substring(0,conDetIds.length-1);
//    if(ids!='0') ids=ids.substring(0,ids.length-1);
    
//    if(matId==null || reqId==null) return false;
//    if (matId.length!=null) {
//        for (i = 0; i < matId.length; i++) {
//            if (matId[i].checked == true){
//                ids+=','+matId[i].value;
//                reqIds+=','+reqId[i].value;
//                conDetIds+=','+conDetIds[i].value;
//            }
//        }
//    } else if(matId.checked == true){
//        ids+=','+matId.value;
//        reqIds+=','+reqId.value;
//        conDetIds+=','+conDetIds.value;
//    }
    
    if(matId.value=="" || matId.value=="0,") return false;
//    ids=matId.value.substring(2);
//    reqIds=reqId.value.substring(2);
//    conDetIds=conDetId.value.substring(2);
    ids=ids.substring(2);
    reqIds=reqIds.substring(2);
    if(source!='notincontract'){
        conDetIds=conDetIds.substring(2);
    }
    if(ids!="") ids=ids.substring(0,ids.length-1);
    if(reqIds!="") reqIds=reqIds.substring(0,reqIds.length-1);
    if(conDetIds!="") conDetIds=conDetIds.substring(0,conDetIds.length-1);

    if (ids!='0'){
        callAjax('tenderPlanForm.do?reqIds='+reqIds,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            form=document.forms['tenderPlanForm'];
            var materialSource=form.cbxMaterialSource;
            if(source=='contract'){
                materialSource.value="2";
            }else if(source=='order'){
                materialSource.value="3";
            }else if(source=='notincontract'){
                materialSource.value="5";
            }else if(source=='principleexpire'){
                materialSource.value="1";
            }else if(source=='principle'){
                materialSource.value="4";
            }else if(source=='adjustmentcontract'){
                materialSource.value="4";
            }
//            materialSource.disabled="true";
            materialSource=null;
            var currentTime = new Date();
            var date=currentTime.getDate();
            var month=currentTime.getMonth() + 1;
            if(date<10) date='0'+date;
            if(month<10) month='0'+month;
            document.getElementById('createdDateTenderPlan').value=date+'/'+month+'/'+currentTime.getFullYear();
            setEmployeeTenderPlan();
            setCertificateTenderPlan();
            setVendorTenderPlan();
            url='listTenderPlanDetail.do';
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listTenderPlanMaterialDataDiv');
//                var idtemp = ids.split(',');
//                ids='0';
//                for(i=idtemp.length-1;i>=0;i--){
//                    ids+=','+idtemp[i];
//                }   
//                if(ids!='0') ids=ids.substring(2);

                setSelectedTenderPlanMaterial(ids,reqIds,conDetIds);
            });
        });
    }
    matId=null;
    reqId=null;
    form=null;
    return false;
}

// BID CLOSING
function loadBidClosing(tenId){
    callAjax('tenderPlanBidClosingForm.do?tenId='+tenId,null,null,function(data) {
        setAjaxData(data,'tenderplanbidclosing');
        var bcrId = document.forms['bidClosingForm'].bcrId.value;
        if(bcrId!=null) {
            //Constants.ATTACH_FILE_BIDCLOSE
            loadAttachFileSystem(5,bcrId,'divAttachFileSystem2');
        }
    });
}
function saveTenderPlanBidClosing() {
    var reportNumber = document.forms['bidClosingForm'].reportNumber;
    var tenId = document.forms['bidClosingForm'].tenId.value;
    if(reportNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 bi\u00EAn b\u1EA3n");
        reportNumber.focus();
        reportNumber=null;
        return false;
    }
    reportNumber=null;
    var closingDate = document.forms['bidClosingForm'].closingDate;
    if(closingDate.value==''){
        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y h\u1EE3p l\u1EC7");
        closingDate.focus();
        closingDate=null;
        return false;
    }
    closingDate=null;
    callAjaxCheckError("saveTenderPlanBidClosing.do",null,document.forms['bidClosingForm'],function(data){
        alert('L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng');
        loadBidClosing(tenId);
    });
    return false;
}
function addBidClosingGroup(){
    var list=document.forms['bidClosingForm'].employeeList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('bidClosingEmployeeTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'bidClosingEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'bcgId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'bidClosingEmployee';
    el.value=name;
    el.size=35;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'bidClosingPosition';
    el.size=30;
    cell.appendChild(el);

    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'bidClosingNote';
    el.size=30;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delBidClosingGroup(){
    var checkflag = false;
    var detId = document.forms['bidClosingForm'].bidClosingEmployeeChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delBidClosingGroup.do',null,document.forms['bidClosingForm'],function(data){
        loadBidClosing(getTenderPlanId());
    });
    detId=null;
    return false;
}

//BID OPENING
function loadBidOpening(tenId){
    callAjax('tenderPlanBidOpeningForm.do?tenId='+tenId,null,null,function(data) {
        setAjaxData(data,'tenderplanbidopening');
        var borId = document.forms['bidOpeningForm'].borId;
        if(borId!=null) {
            //Constants.ATTACH_FILE_BIDCLOSE
            borId=borId.value;
            loadAttachFileSystem(6,borId,'divAttachFileSystem3');
        }
    });
}
function saveTenderPlanBidOpening() {
    var reportNumber = document.forms['bidOpeningForm'].reportNumber;
    var tenId = document.forms['bidOpeningForm'].tenId.value;
    if(reportNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 bi\u00EAn b\u1EA3n");
        reportNumber.focus();
        reportNumber=null;
        return false;
    }
    reportNumber=null;
    var date = document.forms['bidOpeningForm'].startDate;
    if(date.value==''){
        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y bi\u00EAn b\u1EA3n");
        date.focus();
        date=null;
        return false;
    }
    date = document.forms['bidOpeningForm'].endDate;
    if(date.value==''){
        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y bi\u00EAn b\u1EA3n");
        date.focus();
        date=null;
        return false;
    }
    date=null;
    callAjaxCheckError("saveTenderPlanBidOpening.do",null,document.forms['bidOpeningForm'],function(data){
        alert('L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng');
        loadBidOpening(tenId);
    });
    return false;
}
function addBidOpeningGroup(){
    var list=document.forms['bidOpeningForm'].employeeList;
    if(list==null) return;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('bidOpeningEmployeeTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'bidOpeningEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'bogId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'bidOpeningEmployee';
    el.value=name;
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'bidOpeningPosition';
    el.size=30;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delBidOpeningGroup(){
    var checkflag = false;
    var detId = document.forms['bidOpeningForm'].bidOpeningEmployeeChk;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delBidOpeningGroup.do',null,document.forms['bidOpeningForm'],function(data){
        loadBidOpening(getTenderPlanId());
    });
    detId=null;
    return false;
}

function getTenderPlanBidOpeningId(){


    var bor=document.forms["bidOpeningForm"].borId;
    var borId='';
    if(bor!=null){
        borId=bor.value;
        bor=null;
    }
    return borId;
}

function getTenderPlanBidClosingId(){
    var bcr=document.forms["bidClosingForm"].bcrId;
    var bcrId='';
    if(bcr!=null){
        bcrId=bcr.value;
        bcr=null;
    }
    return bcrId;
}

//DELIVERY REQUEST
function loadDeliveryRequestList(params){
    callAjaxEx('deliveryRequestList.do','ajaxContent','searchDeliveryRequest.do','deliveryRequestList',params);
    return false;
}
function loadDeliveryRequestListSort(params){
    //phan trang loi: callAjaxEx('deliveryRequests.do','deliveryRequestList',null,null,params);
    callAjaxEx('searchDeliveryRequest.do','deliveryRequestList',null,null,params);
    return false;
}
function loadDeliveryRequestListSort2(params){
    callAjaxEx('deliveryRequests.do','deliveryRequestList',null,null,params);
    return false;
}
function getDeliveryRequestListData(data){
    setAjaxData(data,'ajaxContent');
    loadDeliveryRequests();
    return false;
}
function loadDeliveryRequests(){
    callAjax("deliveryRequests.do","deliveryRequestList",null,null);
    return false;
}
function deliveryRequestForm(reqId){
    contractForm(kind,reqId,tenId,isAutoCreate,appKind,matIds,reqDetailIds,appId);
    return false;
    var url="deliveryRequestForm.do";
    if(reqId!=null) url=url+"?reqId="+reqId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(reqId!=null){
            //            callAjax('listDeliveryRequestDetail.do?reqId='+reqId,null,null,function(data){
            //            setAjaxData(data,'listDeliveryRequestMaterialDataDiv');
            //            });
            var total=document.forms['contractForm'].total;
            var totalNotVAT=document.forms['contractForm'].totalNotVAT;
            var sumVAT=document.forms['contractForm'].sumVAT;
            var detTotal=document.forms['contractForm'].detTotal;
            var vat=document.forms['contractForm'].vat;
            var price=document.forms['contractForm'].price;
            var quantity=document.forms['contractForm'].quantity;
            if(price!=null){
                if (price.length!=null) {
                    for (i = 0; i < detTotal.length; i++) {
                        tryNumberFormat(vat[i]);
                        tryNumberFormat(price[i]);
                        tryNumberFormat(detTotal[i]);
                        tryNumberFormat(quantity[i]);
                    }
                } else{
                    tryNumberFormat(vat);
                    tryNumberFormat(price);
                    tryNumberFormat(detTotal);
                    tryNumberFormat(quantity);
                }
            }
            if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
            if(sumVAT!=null) tryNumberFormat(sumVAT);
            if(total!=null) tryNumberFormat(total);
            vat=null;
            price=null;
            quantity=null;
            total=null;
            detTotal=null;
            sumVAT=null;
            totalNotVAT=null;
        }
        var venId=document.forms['contractForm'].venId;
        callAjax("deliveryRequestVendorList.do?venId="+venId.value,'deliveryRequestContractSpan',null,null);
        venId=null;
    });
    return false;
}
function deliveryRequestVendorChanged(list,parentId,matIds,reqDetailIds,exParam){
    if(list.selectedIndex==-1) return false;
    var url="deliveryRequestVendorList.do?venId="+list.options[list.selectedIndex].value;
    if(exParam!=null) url+="&"+exParam;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'deliveryRequestContractSpan');
        if(parentId!=null){
            var con=document.forms['contractForm'].parentId;
            con.value=parentId;
            deliveryRequestContractChanged(con,matIds,reqDetailIds);
            con=null;
        }
    });
    list=null;
    removeDeliveryMaterial();
    return false;
}
function deliveryRequestContractChanged(list,matIds,reqDetailIds){
    if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    var conId=list.options[list.selectedIndex].value;
    loadMaterialInContract(conId,matIds);
    getContractPrincipleDetail(conId,matIds,reqDetailIds);
    list=null;
    return false;
}
function loadMaterialInContract(conId,matIds){
    var url='materialListForDeliveryRequest.do?conId='+conId;
    if(matIds!=null) url+="&matIds="+matIds;
    callAjax(url,'listContractMaterialDataSpan',null,null);
}
function getContractPrincipleDetail(conId,matIds,reqDetailIds){
    var url='contractDetail.do?conId='+conId;
    callAjax(url,null,null,function(data){
        var obj=eval('('+data+')');
        var date=obj.deliveryDate;
        var certificate=obj.certificate;
        var delivery=obj.delivery;
        if (date == "null") {document.forms['contractForm'].deliveryDate.value="";}
        else{
        document.forms['contractForm'].deliveryDate.value=date;
        }
        if (certificate == "null") {document.forms['contractForm'].certificate.value="";}
        else{
        document.forms['contractForm'].certificate.value=certificate;
        }
        if (delivery == "null") {document.forms['contractForm'].delivery.value="";}
        else{
        document.forms['contractForm'].delivery.value=delivery;
        }
        //them VT tu dong trong DNGH   
        if (matIds != null)
            {
                    var idLst=matIds.split(',');
                    var reqDetLst=reqDetailIds.split(',');
                    for(i=0;i<idLst.length;i++){
//                        setSelectedDeliveryMaterials(idLst[i],reqDetailIds);
                        setSelectedDeliveryMaterials(idLst[i],reqDetLst[i]);
                    }         
            }
                    //
    });
}
function removeDeliveryMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('deliveryRequestDetailTable');
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
function setSelectedDeliveryMaterial(){
    var list=document.forms['contractForm'].material;
    if(list.selectedIndex==-1) return false;
    var materialId=list.options[list.selectedIndex].value;
    var matId = document.forms['contractForm'].matId;
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
    var contract=document.forms['contractForm'].parentId;
    var url='deliveryRequestMaterial.do?conId='+contract.options[contract.selectedIndex].value;
    contract=null;
//    var materialId=list.options[list.selectedIndex].value;
    url+='&matId='+materialId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'deliveryMaterialHideDiv');
        var matTable=document.getElementById('deliveryRequestMaterialTable');
        var detTable=document.getElementById('deliveryRequestDetailTable');
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
        caculateContract();
//        deliveryRequestFormat();
    });
    list=null;
    return false;
}
function setSelectedDeliveryMaterials(materialId,reqDetailIds){
    var contract=document.forms['contractForm'].parentId;
    var url='deliveryRequestMaterial.do?conId='+contract.options[contract.selectedIndex].value;
    contract=null;
//    var materialId=list.options[list.selectedIndex].value;
    url+='&matId='+materialId;    
    if(reqDetailIds!=null) url+="&reqDetailIds="+reqDetailIds;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'deliveryMaterialHideDiv');
        var matTable=document.getElementById('deliveryRequestMaterialTable');
        var detTable=document.getElementById('deliveryRequestDetailTable');
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
        var vat=document.forms['contractForm'].vat;
        var detTotal=document.forms['contractForm'].detTotal;
        var price=document.forms['contractForm'].price;                    
        var quantity=document.forms['contractForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(vat[i]);
                    tryNumberFormat(price[i]);
                    tryNumberFormat(detTotal[i]);
                    tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat);
                tryNumberFormat(price);
                tryNumberFormat(detTotal);
                tryNumberFormat(quantity);
            }
        }

        vat=null;
        price=null;
        quantity=null;
        detTotal=null;
        caculateContract();
//        deliveryRequestFormat();
    });
    return false;
}
function deliveryRequestFormat(){
    var detTotal=document.forms['contractForm'].detTotal;
    var vat=document.forms['contractForm'].vat;
    var price=document.forms['contractForm'].price;
    var quantity=document.forms['contractForm'].quantity;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                tryNumberFormat(vat[i]);
                tryNumberFormat(price[i]);
                tryNumberFormat(detTotal[i]);
                tryNumberFormat(quantity[i]);
            }
        } else{
            tryNumberFormat(vat);
            tryNumberFormat(price);
            tryNumberFormat(detTotal);
            tryNumberFormat(quantity);
        }
    }
    vat=null;
    price=null;
    quantity=null;
    detTotal=null;
    caculateDeliveryRequest();
}
function caculateDeliveryRequest(){
    var total=document.forms['contractForm'].total;
    if(total==null) return;
    var detTotal=document.forms['contractForm'].detTotal;
    var vat=document.forms['contractForm'].vat;
    var price=document.forms['contractForm'].price;
    var quantity=document.forms['contractForm'].quantity;
    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
    var sumVAT=document.forms['contractForm'].sumVAT;
    var sum=0;
    var totalvat=0;
    if(detTotal!=null){
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
                totalvat+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value)*reformatNumberMoneyString(vat[i].value)*0.01;
            }
        } else {
            sum+=reformatNumberMoneyString(detTotal.value)*1;
            totalvat+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*reformatNumberMoneyString(vat.value)*0.01;
        }        
        vat=null;
        price=null;
        quantity=null;
        sumVAT.value=totalvat;
        totalNotVAT.value=sum-totalvat;
    }
    total.value=sum;
    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
    if(sumVAT!=null) tryNumberFormat(sumVAT);
    if(total!=null) tryNumberFormat(total);
    total=null;
    detTotal=null;
    sumVAT=null;
    totalNotVAT=null;
    return false;
}
function saveDeliveryRequest(){
    if(scriptFunction=="saveDeliveryRequest()") return false;
    var deliveryNumber = document.forms['contractForm'].deliveryNumber;
    if(deliveryNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 \u0111\u1EC1 ngh\u1ECB giao h\u00E0ng!");
        deliveryNumber.focus();
        deliveryNumber=null;
        return false;
    }
    deliveryNumber=null;
    
    var total=document.forms['contractForm'].total;
    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
    var sumVAT=document.forms['contractForm'].sumVAT;
    var detTotal=document.forms['contractForm'].detTotal;
    var vat=document.forms['contractForm'].vat;
    var price=document.forms['contractForm'].price;
    var quantity=document.forms['contractForm'].quantity;
    if(price!=null){
        if (price.length!=null) {
            for (i = 0; i < price.length; i++) {
                reformatNumberMoney(vat[i]);
                reformatNumberMoney(price[i]);
                reformatNumberMoney(detTotal[i]);
                reformatNumberMoney(quantity[i]);
            }
        } else{
            reformatNumberMoney(vat);
            reformatNumberMoney(price);
            reformatNumberMoney(detTotal);
            reformatNumberMoney(quantity);
        }
    }
    if(totalNotVAT!=null) reformatNumberMoney(totalNotVAT);
    if(sumVAT!=null) reformatNumberMoney(sumVAT);
    if(total!=null) reformatNumberMoney(total);
    vat=null;
    price=null;
    quantity=null;
    total=null;
    detTotal=null;
    sumVAT=null;
    totalNotVAT=null;
    
    //callAjaxCheckError("saveDeliveryRequest.do",null,document.forms['contractForm'],getDeliveryRequestListData);
    scriptFunction="saveDeliveryRequest()";
    callAjaxCheckError("saveDeliveryRequest.do",null,document.forms['contractForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        deliveryRequestForm(0)
    });

    return false;
}
function searchDeliveryRequest(){
    var checkflag = true;
    var form=document.forms['searchSimpleDeliveryRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;


        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchDeliveryRequest.do","deliveryRequestList",form,null);
    form=null;
    return false;
}
function createDeliveryRequest_old(kind){
    /*var form=document.forms['materialInContractPrincipleForm'];
    var conId=form.conId;
    var venId=form.venId;
    var reqDetailId=form.reqDetailId;
    if(venId==null) venId=0;
    else venId=venId.value
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var matId = form.matId;
    var manyCon=false;
    var con='';
    if(matId==null || conId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                conIds+=','+conId[i].value;
                if(reqDetailId!=null) reqDetailIds+=','+reqDetailId[i].value;



                if(manyCon==false && con!='' && con!=conId[i].value) manyCon=true;
                else con=conId[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        conIds+=','+conId.value;
        if(reqDetailId!=null) reqDetailIds+=','+reqDetailId.value;
    }
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u1EC1 ngh\u1ECB giao h\u00E0ng ph\u1EA3i \u0111\u01B0\u1EE3c l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        if (reqDetailIds!='0') reqDetailIds=reqDetailIds.substring(2);
        callAjax("contractForm.do?conId="+con+"&venId="+venId+"&kind="+kind,null,null,function(data){
            setAjaxData(data,'ajaxContent');
//            var venId=document.forms['contractForm'].venId
            callAjax("deliveryRequestVendorList.do?venId="+venId+"&select=1",'deliveryRequestContractSpan',null,null);
            venId=null;
            callAjax('deliveryRequestMaterial.do?conId='+conIds.substring(2)+'&matId='+ids.substring(2)+'&reqDetailIds='+reqDetailIds,null,null,function(data){
                setAjaxData(data,'deliveryMaterialHideDiv');
                var matTable=document.getElementById('deliveryRequestMaterialTable');
                var detTable=document.getElementById('deliveryRequestDetailTable');
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
                deliveryRequestFormat();
            });
        });
    }
    matId=null;
    conId=null;
    reqDetailId=null;
    form=null;
    return false;*/
    var form=null;
    var conId;
    var parentId;
    form=document.forms['materialInContractPrincipleForm'];
    conId=form.conId;
    var ven=form.venId;
    var v;
    var reqDetailId=form.reqDetailId;
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var matId = form.matId;
    var manyCon=false;
    var con='';
    if(matId==null || conId==null) return false;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true){
                ids+=','+matId[i].value;
                conIds+=','+conId[i].value;
                parentId=conId[i].value;
                v=ven[i].value;
                if(reqDetailId!=null) reqDetailIds+=','+reqDetailId[i].value;
                if(manyCon==false && con!='' && con!=conId[i].value) manyCon=true;
                else con=conId[i].value;
            }
        }
    } else if(matId.checked == true){
        ids+=','+matId.value;
        conIds+=','+conId.value;
        con=conId.value;
        parentId=conId.value;
        v=ven.value;
        if(reqDetailId!=null) reqDetailIds+=','+reqDetailId.value;
    }
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u1EC1 ngh\u1ECB giao h\u00E0ng ph\u1EA3i \u0111\u01B0\u1EE3c l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        if (reqDetailIds!='0') reqDetailIds=reqDetailIds.substring(2);
        var url="contractForm.do?kind="+kind;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            if(ids!=null){  
                var matIds = ids;
//                document.forms['contractForm'].reqDetailIds.value=reqDetailIds;
                url='orderMaterialListInContract.do?conId='+parentId+'&matIds='+ids;
                callAjax(url,null,null,function(data){
                    setAjaxData(data,'listAppendixMaterialDataSpan');
                    var total=document.forms['contractForm'].total;
                    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
                    var sumVAT=document.forms['contractForm'].sumVAT;
                    var otherFee=document.forms['contractForm'].otherFee;
                    var detTotal=document.forms['contractForm'].detTotal;
                    var vat=document.forms['contractForm'].vat;
                    var price=document.forms['contractForm'].price;                    
                    var quantity=document.forms['contractForm'].quantity;
                    if(price!=null){
                        if (price.length!=null) {
                            for (i = 0; i < detTotal.length; i++) {
                                tryNumberFormat(vat[i]);
                                tryNumberFormat(price[i]);
                                tryNumberFormat(detTotal[i]);
                                tryNumberFormat(quantity[i]);
                            }
                        } else{
                            tryNumberFormat(vat);
                            tryNumberFormat(price);
                            tryNumberFormat(detTotal);
                            tryNumberFormat(quantity);
                        }
                    }
                    if(otherFee!=null) tryNumberFormat(otherFee);
                    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
                    if(sumVAT!=null) tryNumberFormat(sumVAT);
                    if(total!=null) tryNumberFormat(total);
                    vat=null;
                    price=null;
                    quantity=null;
                    total=null;
                    detTotal=null;
                    otherFee=null;
                    sumVAT=null;
                    totalNotVAT=null;                    
                    var list=document.forms['contractForm'].material;
                    var ids='';
                    var conIds='';
                    for(i=0;i<list.options.length;i++){
                        ids+=','+list.options[i].value;
                        conIds+=','+parentId;
                    }
                    if(ids!=''){
                        ids=ids.substring(1);
                        conIds=conIds.substring(1);
                    }
                    var venId=document.forms['contractForm'].venId;
                    venId.value=v;
                    deliveryRequestVendorChanged(venId,parentId,matIds,reqDetailIds);
                    venId=null;                    
//                    setSelectedAppendixMaterial(ids,conIds);
                });
//                callAjax('getVendorByContract.do?conId='+parentId,'appendixVendorName',null,null);
            }
        });
    }
    matId=null;
    conId=null;
    reqDetailId=null;
    form=null;    
    return false;
}
function createDeliveryRequest(kind,formName){
    var form=null;
    var conId=null;
    var parentId=null;
    var venId=null;
    var reqDetailId=null;
    var matId=null;
    form=document.forms[formName];
    if(formName=='materialInContractPrincipleForm'){
        conId=form.conId_materialInContractPrincipleForm;
        venId=form.venId_materialInContractPrincipleForm;
        reqDetailId=form.reqDetId_materialInContractPrincipleForm;
        matId = form.matId_materialInContractPrincipleForm;
    }else if(formName=='materialInContractPrincipleExpireForm'){
        conId=form.conId_materialInContractPrincipleExpireForm;
        venId=form.venId_materialInContractPrincipleExpireForm;
        reqDetailId=form.reqDetId_materialInContractPrincipleExpireForm;
        matId = form.matId_materialInContractPrincipleExpireForm;
    }else if(formName=='materialInAdjustmentContractForm'){
        conId=form.conId_materialInAdjustmentContractForm;
        venId=form.venId_materialInAdjustmentContractForm;
        reqDetailId=form.reqDetId_materialInAdjustmentContractForm;
        matId = form.matId_materialInAdjustmentContractForm;
    }
    var v;
    var ids = '0';
    var conIds='0';
    var reqDetailIds='0';
    var venIds='0';
    var manyCon=false;
    var con='';
    
    if(matId==null || matId.value=="" || matId.value=="0,") return false;
    ids=matId.value.substring(2);
    conIds=conId.value.substring(2);
    reqDetailIds=reqDetailId.value.substring(2);
    venIds=venId.value.substring(2);
    ids=ids.substring(0,ids.length-1);
    conIds=conIds.substring(0,conIds.length-1);
    reqDetailIds=reqDetailIds.substring(0,reqDetailIds.length-1);
    venIds=venIds.substring(0,venIds.length-1);
    
    var t=conIds.split(',');
    for(var i=0;i<t.length;i++){
        if(manyCon==false && con!='' && con!=t[i]){
             manyCon=true;
             break;
        }
        else{
            con=t[i];   
        }
        parentId=t[i];
    }
    
    var h=venIds.split(',');
    for(i=0;i<h.length;i++){
        v=h[i];
    }
    
    var k=reqDetailIds.split(',');
    var reqDetailIds_temp='';
    var ind=-1;
    for(i=0;i<k.length;i++){
        ind=k[i].indexOf('_');
        if(ind>-1){
            reqDetailIds_temp+=','+k[i].substring(0,ind);
        }else{
            reqDetailIds_temp+=','+k[i];
        }
    }
    if(reqDetailIds_temp!=''){
        reqDetailIds=reqDetailIds_temp.substring(1);
    }
    
    if (ids!='0'){
        if(manyCon==true){
            alert('\u0110\u1EC1 ngh\u1ECB giao h\u00E0ng ph\u1EA3i \u0111\u01B0\u1EE3c l\u1EADp t\u1EEB m\u1ED9t h\u1EE3p \u0111\u1ED3ng');
            return false;
        }
        var url="contractForm.do?kind="+kind;
        if(formName=='materialInContractPrincipleExpireForm'){
            url += "&expire=1";
        }
        if(conIds!='0') url+='&conIds='+conIds;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            if(ids!=null){  
                var matIds = ids;
//                document.forms['contractForm'].reqDetailIds.value=reqDetailIds;
                url='orderMaterialListInContract.do?conId='+parentId+'&matIds='+ids;
                callAjax(url,null,null,function(data){
//                    setAjaxData(data,'listAppendixMaterialDataSpan');
                    setAjaxData(data,'listContractMaterialDataSpan');
                    var total=document.forms['contractForm'].total;
                    var totalNotVAT=document.forms['contractForm'].totalNotVAT;
                    var sumVAT=document.forms['contractForm'].sumVAT;
                    var otherFee=document.forms['contractForm'].otherFee;
                    var detTotal=document.forms['contractForm'].detTotal;
                    var vat=document.forms['contractForm'].vat;
                    var price=document.forms['contractForm'].price;                    
                    var quantity=document.forms['contractForm'].quantity;
                    if(price!=null){
                        if (price.length!=null) {
                            for (i = 0; i < detTotal.length; i++) {
                                tryNumberFormat(vat[i]);
                                tryNumberFormat(price[i]);
                                tryNumberFormat(detTotal[i]);
                                tryNumberFormat(quantity[i]);
                            }
                        } else{
                            tryNumberFormat(vat);
                            tryNumberFormat(price);
                            tryNumberFormat(detTotal);
                            tryNumberFormat(quantity);
                        }
                    }
                    if(otherFee!=null) tryNumberFormat(otherFee);
                    if(totalNotVAT!=null) tryNumberFormat(totalNotVAT);
                    if(sumVAT!=null) tryNumberFormat(sumVAT);
                    if(total!=null) tryNumberFormat(total);
                    vat=null;
                    price=null;
                    quantity=null;
                    total=null;
                    detTotal=null;
                    otherFee=null;
                    sumVAT=null;
                    totalNotVAT=null;                    
                    var list=document.forms['contractForm'].material;
                    var ids='';
                    var conIds='';
                    for(i=0;i<list.options.length;i++){
                        ids+=','+list.options[i].value;
                        conIds+=','+parentId;
                    }
                    var venId=document.forms['contractForm'].venId;
                    venId.value=v;
                    if(formName=='materialInContractPrincipleExpireForm'){
                        deliveryRequestVendorChanged(venId,parentId,matIds,reqDetailIds,"expire=1");
                    }else{
                        deliveryRequestVendorChanged(venId,parentId,matIds,reqDetailIds);
                    }
                    venId=null;                    
//                    setSelectedAppendixMaterial(ids,conIds);
                });
//                callAjax('getVendorByContract.do?conId='+parentId,'appendixVendorName',null,null);
            }
        });
    }
    matId=null;
    conId=null;
    reqDetailId=null;
    form=null;    
    return false;
}
function quantityDeliveryRequestDetailChanged(detId){
    var reqDetQuantity=reformatNumberMoneyString(document.getElementById("reqDetQuantity"+detId).value)*1;
    var quantity=reformatNumberMoneyString(document.getElementById("detquantity"+detId).value)*1;
    if(quantity>reqDetQuantity){
        alert('S\u1ED1 l\u01B0\u1EE3ng \u0111\u1EB7t kh\u00F4ng \u0111\u01B0\u1EE3c v\u01B0\u1EE3t qu\u00E1 s\u1ED1 l\u01B0\u1EE3ng ban \u0111\u1EA7u');
        document.getElementById("detquantity"+detId).value=document.getElementById("reqDetQuantity"+detId).value;
        tryNumberFormat(document.getElementById("detquantity"+detId));
        return false;
    }
    caculateDeliveryRequestDetail(detId);
    return false;
}
function caculateDeliveryRequestDetail(detId){
    var quantity=document.getElementById("detquantity"+detId);
    var price=document.getElementById("detprice"+detId);
    var vat=document.getElementById("detvat"+detId);
    var detTotal=document.getElementById("detTotal"+detId);
    if(quantity==null || price==null || vat==null || detTotal==null) return;
    detTotal.value=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*(100+reformatNumberMoneyString(vat.value)*1)*0.01;
    tryNumberFormat(detTotal);
    quantity=null;
    price=null;
    vat=null;
    detTotal=null;
    caculateDeliveryRequest();
    return false;
}
function delDeliveryRequests(){
    var checkflag = false;
    var drId = document.forms['deliveryRequestsForm'].conId;
    if(drId==null) return false;
    if (drId.length!=null) {
        for (i = 0; i < drId.length; i++) {
            if (drId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = drId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delDeliveryRequest.do','deliveryRequestList',document.forms['deliveryRequestsForm'],null);
    drId=null;
    return false;
}
function delDeliveryRequestDetails(){
    var checkflag = false;
    var detId = document.forms['contractForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delDeliveryRequestDetail.do',null,document.forms['contractForm'],function(data){
//        var tbl=document.getElementById('deliveryRequestDetailTable');
//        var parentNode;
//        if(detId.length!=null){
//            for (i=detId.length-1;i>=0;i--) {
//                if(detId[i].checked==true){
//                    parentNode=detId[i].parentNode;
//                    parentNode=parentNode.parentNode;
//                    tbl.deleteRow(parentNode.rowIndex);
//                }
//            }
//        }else if(detId.checked==true) tbl.deleteRow(1);
//        parentNode=null;
//        tbl=null;
//        detId=null;
//        caculateContract();
        var conId = document.forms['contractForm'].conId.value;
        var kind = document.forms['contractForm'].kind.value;
        contractForm(kind,conId);
    });
    return false;
}
//PAYMENT
function loadPaymentList(params){
    callAjaxEx('paymentList.do','ajaxContent','searchPayment.do','paymentList',params);
    return false;
}
function loadPaymentListSort(params){
    //phan trang loi: callAjaxEx('payments.do','paymentList',null,null,params);
    callAjaxExChild('searchPayment.do','paymentList',params);
    return false;
}
function getPaymentListData(data){
    setAjaxData(data,'ajaxContent');
    loadPayments();
    return false;
}
function loadPayments(){
    callAjax("payments.do","paymentList",null,null);
    return false;
}
function paymentForm(payId){
    var url="paymentForm.do";
    if(payId!=null) url=url+"?payId="+payId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
//        if(payId==null){
//            var currentTime = new Date();
//            var date=currentTime.getDate();
//            var month=currentTime.getMonth() + 1;
//            if(date<10) date='0'+date;
//            if(month<10) month='0'+month;
//            document.getElementById('createdDatePayment').value=date+'/'+month+'/'+currentTime.getFullYear();
//        }
        var total=document.forms['paymentForm'].total;
        var totalPay=document.forms['paymentForm'].totalPay;
        var punish=document.forms['paymentForm'].punish;
        if(total!=null) tryNumberFormat(total);
        if(totalPay!=null) tryNumberFormat(totalPay);
        if(punish!=null) tryNumberFormat(punish);
        total=null;
        totalPay=null;
        punish=null;
        var amount=document.forms['paymentForm'].amount;
        if(amount!=null){
            if (amount.length!=null) {
                for (i = 0; i < amount.length; i++) {
                    tryNumberFormat(amount[i]);
                }
            } else tryNumberFormat(amount);
            amount=null;
        }
    });
    return false;
}
function savePayment() {
    if(scriptFunction=="savePayment()") return false;
    var payNumber = document.forms['paymentForm'].payNumber;
    if(payNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 thanh to\u00E1n");
        payNumber.focus();
        payNumber=null;
        return false;
    }
    payNumber=null;
    var conId = document.forms['paymentForm'].conId;
    if(conId.selectedIndex==-1){
        alert("Vui l\u00F2ng ch\u1ECDn h\u1EE3p \u0111\u1ED3ng");
        conId=null;
        return false;
    }
    conId=null;
    var handleEmp = document.forms['paymentForm'].handleEmp;
    if(handleEmp!=null){
        if(handleEmp.selectedIndex==-1){
            alert("Vui l\u00F2ng ch\u1ECDn nh\u00E2n vi\u00EAn qu\u1EA3n l\u00FD h\u1EE3p \u0111\u1ED3ng");
            handleEmp.focus();
            handleEmp=null;
            return false;
        }
        handleEmp=null;
    }
    var total=document.forms['paymentForm'].total;
    var totalPay=document.forms['paymentForm'].totalPay;
    var punish=document.forms['paymentForm'].punish;
    if(total!=null) reformatNumberMoney(total);
    if(totalPay!=null) reformatNumberMoney(totalPay);
    if(punish!=null) reformatNumberMoney(punish);
    total=null;
    totalPay=null;
    punish=null;
    var amount=document.forms['paymentForm'].amount;
    if(amount!=null){
        if (amount.length!=null) {
            for (i = 0; i < amount.length; i++) {
                reformatNumberMoney(amount[i]);
            }
        } else{
            reformatNumberMoney(amount);
        }
    }
    amount=null;
    //callAjaxCheckError("savePayment.do",null,document.forms['paymentForm'],getPaymentListData);
    var payId = document.forms['paymentForm'].payId.value;
    scriptFunction="savePayment()";
    callAjaxCheckError("savePayment.do",null,document.forms['paymentForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        paymentForm(payId);
    });
    return false;
}
function paymentContractChanged(list){
    if(list!=null && list.selectedIndex!=-1) list=list.options[list.selectedIndex].value;
    else list='0';
    callAjax('getVendorByContract.do?isPayment=1&conId='+list,null,null,function(data){
        setAjaxData(data,'handleEmpSpan');
        var handleEmp=document.forms['paymentForm'].handleEmp;
        if(handleEmp!=null){
            handleEmp.value=document.forms['paymentForm'].followEmpId.value;
            handleEmp=null;
        }
    });
    callAjax('getInvoiceByContract.do?isPayment=1&conId='+list,'spanPaymentInvoices',null,null);
    return false;
}
function paymentVendorChanged(list){
    if(list!=null && list.selectedIndex!=-1) list=list.options[list.selectedIndex].value;
    else list='0';
    document.forms['paymentForm'].invoice.length = 0;
    removePaymentInvoice();
    callAjax('getContractOfVendor.do?venId='+list,'invoiceContractDiv',null,null);
    return false;
}
function removePaymentInvoice(){
    var selId=document.getElementsByName('payBillId');
    if(selId==null) return false;
    var tbl=document.getElementById('paymentBillTable');
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
function searchPayment(){
    var checkflag = true;
    var form=document.forms['searchSimplePaymentForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchPayment.do","paymentList",form,null);
    form=null;
    return false;
}
function delPayments(){
    var checkflag = false;
//    var payId = document.forms['paymentsForm'].payId;
//    if(payId==null) return false;
//    if (payId.length!=null) {
//        for (i = 0; i < payId.length; i++) {
//            if (payId[i].checked == true) {
//                checkflag = true;
//                break;
//            }
//        }
//    } else checkflag = payId.checked;
    checkflag = true;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPayment.do',null,document.forms['paymentForm'],function(data){
        loadPaymentList();
    });
    return false;
}
//ComEval
function loadComEvalList(){
    callAjax('comEvalList.do',null,null,getComEvalListData);
    return false;
}
function getComEvalListData(data){
    setAjaxData(data,'ajaxContent');
    loadComEvals();
    return false;
}
function loadComEvals(){
    callAjax("comEvals.do","comEvalList",null,null);
    return false;
}
function comEvalForm(tenId){
    var url="comEvalForm.do";
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tenderplancomeval');
        setEmployeeComEval(tenId);
        callAjax("comEvalVendors.do?tenId="+tenId,null,null,function(data){
            setAjaxData(data,'vendor1List');
        });
        var ceId = document.forms['comEvalForm'].ceId.value;
        if(ceId!=null) {
            //Constants.ATTACH_FILE_COMEVAL
            loadAttachFileSystem(8,ceId,'divAttachFileSystem5');
        }
    });
    
    return false;
}
function saveComEval() {
    var createdDate = document.forms['comEvalForm'].createdDate;
    var tenId = document.forms['comEvalForm'].tenId.value;
    if(createdDate!=null){
        for(var i=0;i<createdDate.length;i++){
            if(createdDate[i].value!=""){
                if(!isDate(createdDate[i].value,"dd/MM/yyyy")){
                    alert('Ng\u00E0y th\u00E1ng n\u0103m kh\u00F4ng ch\u00EDnh x\u00E1c (ng\u00E0y/th\u00E1ng/n\u0103m!!');
                    createdDate[i].focus();
                    createdDate=null;
                    return false;
                }
            }
        }
    }
    callAjaxCheckError("saveComEval.do",null,document.forms['comEvalForm'],function(data){
        callAjax("comEvalVendors.do?tenId="+tenId,"vendor1List",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        comEvalForm(tenId);
    });
    return false;
}
function setEmployeeComEval(tenId) {
    var url="getEmployeeComEval.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'comEvalEmployeeList',null,null);
    return false;
}
function setComEvalDetail(terId) {
    var url="getEmployeeComEval.do";
    if(terId!=null) url+="?terId="+terId;
    callAjax(url,'comEvalEmployeeList',null,null);
    return false;
}
function addComEvalGroup(){
    var list=document.forms['comEvalForm'].employeeList;
    if(list==null || list.selectedIndex==-1) return false;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('comEvalGroupTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'comEvalEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'evalId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalEmployee';
    el.value=name;
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalPosition';
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalNote';
    el.size=30;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delComEvalGroup(){
    var checkflag = false;
    var detId = document.forms['comEvalForm'].comEvalEmployeeChk;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delComEvalGroup.do',null,document.forms['comEvalForm'],function(data){
        {
                var tbl=document.getElementById('comEvalGroupTbl');
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
function addComEvalVendor(cevId,venId){
    var url="comEvalDetailForm.do";
    if(cevId!=null) url=url+"?cevId="+cevId+"&venId="+venId;
    callAjax(url,null,null,function(data){
	
        showPopupFormLoc(data);
        url='listComEvalDetail.do';
        if(cevId!=null) url=url+"?cevId="+cevId+"&venId="+venId;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'listComEvalDetailDataDiv');
            tryNumberFormat(document.forms['comEvalDetailForm'].rates);
            tryNumberFormat(document.forms['comEvalDetailForm'].ratesAfter);
            var total=document.forms['comEvalDetailForm'].total;
            if(total!=null){
                if (total.length!=null) {
                    for (i = 0; i < total.length; i++) {
                        tryNumberFormat(document.forms['comEvalDetailForm'].price[i]);
                        tryNumberFormat(document.forms['comEvalDetailForm'].priceAfter[i]);
                        tryNumberFormat(document.forms['comEvalDetailForm'].total[i]);
                    }
                }else {
                    tryNumberFormat(document.forms['comEvalDetailForm'].price);
                    tryNumberFormat(document.forms['comEvalDetailForm'].priceAfter);
                    tryNumberFormat(document.forms['comEvalDetailForm'].total);
                }
            }
        });
    });
    return false;
}
function saveComEvalDetail() {
    var tenId=document.forms['comEvalDetailForm'].tenId.value;
    reformatNumberMoney(document.forms['comEvalDetailForm'].rates);
    reformatNumberMoney(document.forms['comEvalDetailForm'].ratesAfter);
    reformatNumberMoney(document.forms['comEvalDetailForm'].customTax);
    reformatNumberMoney(document.forms['comEvalDetailForm'].costTransport);
    reformatNumberMoney(document.forms['comEvalDetailForm'].otherTax);
    reformatNumberMoney(document.forms['comEvalDetailForm'].otherCost);
    var total=document.forms['comEvalDetailForm'].total;
    if(total!=null){
        if (total.length!=null) {
            for (i = 0; i < total.length; i++) {
                reformatNumberMoney(document.forms['comEvalDetailForm'].price[i]);
                reformatNumberMoney(document.forms['comEvalDetailForm'].priceAfter[i]);
                reformatNumberMoney(document.forms['comEvalDetailForm'].total[i]);
            }
        }else {
            reformatNumberMoney(document.forms['comEvalDetailForm'].price);
            reformatNumberMoney(document.forms['comEvalDetailForm'].priceAfter);
            reformatNumberMoney(document.forms['comEvalDetailForm'].total);
        }
    }
    callAjaxCheckError("saveComEvalDetail.do",null,document.forms['comEvalDetailForm'],function(data){
        hidePopupForm();
        callAjax("comEvalVendors.do?tenId="+tenId,"vendor1List",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}
function caculateComEvalDetail(detId){
    var quantity=document.getElementById("detQuantity"+detId);
    var price=document.getElementById("detPrice"+detId);
    var detTotal=document.getElementById("detTotal"+detId);
    var priceAfter=document.getElementById("priceAfter"+detId);
//    priceAfter.value = formatMoney(price.value+'*'+document.forms['comEvalDetailForm'].rates.value);
//    tryNumberFormat(priceAfter);
//    detTotal.value=formatMoney(quantity.value+'*'+priceAfter.value);
    priceAfter.value=reformatNumberMoneyString(price.value)*reformatNumberMoneyString(document.forms['comEvalDetailForm'].rates.value);
    detTotal.value=reformatNumberMoneyString(quantity.value)*priceAfter.value;
    tryNumberFormat(priceAfter);
    tryNumberFormat(detTotal);
    caculateComEvalDetail2();
    return false;
}
function caculateComEvalDetail2(){    
    //Tong gia tri sau quy doi
    var sum=0;
    var quantity2=document.forms['comEvalDetailForm'].quantity;
    var price2=document.forms['comEvalDetailForm'].price;
    var total=document.forms['comEvalDetailForm'].total;
    var priceAfter2=document.forms['comEvalDetailForm'].priceAfter;
    var sum2=document.forms['comEvalDetailForm'].sum;
    var customTax=document.forms['comEvalDetailForm'].customTax;
    var costTransport=document.forms['comEvalDetailForm'].costTransport;
    var otherTax=document.forms['comEvalDetailForm'].otherTax;
    var otherCost=document.forms['comEvalDetailForm'].otherCost;
    var price_certificate=document.forms['comEvalDetailForm'].priceCertificate;
    var price_to_port=document.forms['comEvalDetailForm'].priceToPort;
    if(total!=null){
        if (total.length!=null) {
            for (i = 0; i < total.length; i++) {
//                priceAfter2[i].value = formatMoney(price2[i].value+'*'+document.forms['comEvalDetailForm'].rates.value);
//                tryNumberFormat(priceAfter2[i]);
//                total[i].value=formatMoney(quantity2[i].value+'*'+priceAfter2[i].value);
//                sum+=total[i].value*1;
                priceAfter2[i].value=reformatNumberMoney(price2[i])*reformatNumberMoney(document.forms['comEvalDetailForm'].rates);
                total[i].value=reformatNumberMoney(quantity2[i])*priceAfter2[i].value;
                sum+=total[i].value*1;
                tryNumberFormat(priceAfter2[i]);
                tryNumberFormat(total[i]);
            }            
        }
        else{
//            priceAfter2.value = formatMoney(price2.value+'*'+document.forms['comEvalDetailForm'].rates.value);
//            tryNumberFormat(priceAfter2);
//            total.value=formatMoney(quantity2.value+'*'+priceAfter2.value);
            priceAfter2.value = reformatNumberMoney(price2)*+reformatNumberMoney(document.forms['comEvalDetailForm'].rates);
            total.value=reformatNumberMoney(quantity2)*priceAfter2.value;
            sum+=total.value*1;
            tryNumberFormat(priceAfter2);
            tryNumberFormat(total);
        }
        document.forms['comEvalDetailForm'].ratesAfter.value=sum;
        sum2.value=sum+reformatNumberMoney(customTax)*1+reformatNumberMoney(costTransport)*1+reformatNumberMoney(otherTax)*1+reformatNumberMoney(otherCost)*1+reformatNumberMoney(price_certificate)*1+reformatNumberMoney(price_to_port)*1;
        tryNumberFormat(document.forms['comEvalDetailForm'].ratesAfter);
        tryNumberFormat(sum2);
    }
    return false;
}
//ComEvalMaterial
function loadComEvalMaterialList(){
    callAjax('comEvalMaterialList.do',null,null,getComEvalMaterialListData);
    return false;
}
function getComEvalMaterialListData(data){
    setAjaxData(data,'ajaxContent');
    loadComEvalMaterials();
    return false;
}
function loadComEvalMaterials(){
    callAjax("comEvalMaterials.do","comEvalMaterialList",null,null);
    return false;
}
function comEvalMaterialForm(tenId){
    var url="comEvalMaterialForm.do";
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tenderplancomevalmaterial');
        setEmployeeComEvalMaterial(tenId);
        callAjax("comEvalMaterialVendors.do?tenId="+tenId,"materialList",null,null);
        var ceId = document.forms['comEvalMaterialForm'].ceId.value;
        if(ceId!=null) {
            //Constants.ATTACH_FILE_COMEVAL
            loadAttachFileSystem(8,ceId,'divAttachFileSystem5');
        }
    });
    
    return false;
}
function saveComEvalMaterial() {
    var createdDate = document.forms['comEvalMaterialForm'].createdDate;
    var tenId = document.forms['comEvalMaterialForm'].tenId.value;
    if(createdDate!=null){
        for(var i=0;i<createdDate.length;i++){
            if(createdDate[i].value!=""){
                if(!isDate(createdDate[i].value,"dd/MM/yyyy")){
                    alert('Ng\u00E0y th\u00E1ng n\u0103m kh\u00F4ng ch\u00EDnh x\u00E1c (ng\u00E0y/th\u00E1ng/n\u0103m!!');
                    createdDate[i].focus();
                    createdDate=null;
                    return false;
                }
            }
        }
    }
    callAjaxCheckError("saveComEvalMaterial.do",null,document.forms['comEvalMaterialForm'],function(data){
        callAjax("comEvalMaterialVendors.do?tenId="+tenId,"materialList",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        comEvalMaterialForm(tenId);
    });
    return false;
}
function setEmployeeComEvalMaterial(tenId) {
    var url="getEmployeeComEvalMaterial.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'comEvalMaterialEmployeeList',null,null);
    return false;
}
function setComEvalMaterialDetail(terId) {
    var url="getEmployeeComEvalMaterial.do";
    if(terId!=null) url+="?terId="+terId;
    callAjax(url,'comEvalMaterialEmployeeList',null,null);
    return false;
}
function addComEvalMaterialGroup(){
    var list=document.forms['comEvalMaterialForm'].employeeList;
    if(list==null || list.selectedIndex==-1) return false;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('comEvalMaterialGroupTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'comEvalMaterialEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'evalId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalEmployee';
    el.value=name;
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalPosition';
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalNote';
    el.size=30;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delComEvalMaterialGroup(){
    var checkflag = false;
    var detId = document.forms['comEvalMaterialForm'].comEvalMaterialEmployeeChk;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delComEvalMaterialGroup.do',null,document.forms['comEvalMaterialForm'],function(data){
        {
                var tbl=document.getElementById('comEvalMaterialGroupTbl');
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
function addComEvalMaterialVendor(cemId,venId,terId){
    var form = document.forms['tenderPlanForm'].form.value;
    var url="comEvalMaterialDetailForm.do";
    if(cemId!=null) url=url+"?cemId="+cemId+"&venId="+venId+"&form="+form;
    callAjax(url,null,null,function(data){
        showPopupFormLoc(data);
        url='listComEvalMaterialDetail.do';
        if(cemId!=null) url=url+"?cemId="+cemId+"&terId="+terId;
        callAjax(url,null,null,function(data){
            setAjaxData(data,'listComEvalMaterialDetailDataDiv');
            tryNumberFormat(document.forms['comEvalMaterialDetailForm'].rates);
            tryNumberFormat(document.forms['comEvalMaterialDetailForm'].ratesAfter);            
            var total=document.forms['comEvalMaterialDetailForm'].total;
            if(total!=null){
                if (total.length!=null) {
                    for (i = 0; i < total.length; i++) {
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].price[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceCustom[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceTransport[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceTax[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceOtherCost[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].pricePtscmc[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceAfter[i]);
                        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].total[i]);
                    }
                }else {
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].price);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceCustom);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceTransport);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceTax);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceOtherCost);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].pricePtscmc);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].priceAfter);
                    tryNumberFormat(document.forms['comEvalMaterialDetailForm'].total);
                }
            }
        });
    });
    return false;
}
function saveComEvalMaterialDetail() {
    var tenId = document.forms['comEvalMaterialForm'].tenId.value;
    reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].rates);
    reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].ratesAfter);
    var total=document.forms['comEvalMaterialDetailForm'].total;
    if(total!=null){
        if (total.length!=null) {
            for (i = 0; i < total.length; i++) {
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].price[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceCustom[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceTransport[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceTax[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceOtherCost[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].pricePtscmc[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceAfter[i]);
                reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].total[i]);
            }
        }else {
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].price);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceCustom);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceTransport);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceTax);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceOtherCost);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].pricePtscmc);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].priceAfter);
            reformatNumberMoney(document.forms['comEvalMaterialDetailForm'].total);
        }
    }
    callAjaxCheckError("saveComEvalMaterialDetail.do",null,document.forms['comEvalMaterialDetailForm'],function(data){
        hidePopupForm();
        callAjax("comEvalMaterialVendors.do?tenId="+tenId,"materialList",null,null);        
    });
    return false;
}
function caculateComEvalMaterialDetail(detId){
    var a=document.getElementById("1"+detId);
    var b=document.getElementById("2"+detId);
    var c=document.getElementById("3"+detId);
    var d=document.getElementById("4"+detId);
    var e=document.getElementById("5"+detId);
    var f=document.getElementById("6"+detId);
    var g=document.getElementById("7"+detId);
    var i=document.getElementById("8"+detId);    
    var h=document.getElementById("9"+detId);
    var k=document.getElementById("11"+detId);
    var l=document.getElementById("12"+detId);
    f.value=reformatNumberMoneyString(a.value)*1+reformatNumberMoneyString(b.value)*1+reformatNumberMoneyString(c.value)*1+reformatNumberMoneyString(d.value)*1+reformatNumberMoneyString(e.value)*1;
    i.value=f.value*reformatNumberMoneyString(document.forms['comEvalMaterialDetailForm'].rates.value);
    g.value=i.value*h.value;
    tryNumberFormat(f);
    tryNumberFormat(i);
    tryNumberFormat(g);
    a=null;
    b=null;
    c=null;
    d=null;
    e=null;
    f=null;
    g=null;
    h=null;
    caculateComEvalMaterialDetail2();
    return false;    
}
function caculateComEvalMaterialDetail2(){
    //Tong gia tri sau quy doi
    var sum=0;
    var quantity=document.forms['comEvalMaterialDetailForm'].quantity;


    var price=document.forms['comEvalMaterialDetailForm'].price;
    var priceCustom=document.forms['comEvalMaterialDetailForm'].priceCustom;
    var priceTransport=document.forms['comEvalMaterialDetailForm'].priceTransport;
    var priceTax=document.forms['comEvalMaterialDetailForm'].priceTax;
    var priceOtherCost=document.forms['comEvalMaterialDetailForm'].priceOtherCost;
    var pricePtscmc=document.forms['comEvalMaterialDetailForm'].pricePtscmc;
    var priceAfter=document.forms['comEvalMaterialDetailForm'].priceAfter;
    var total=document.forms['comEvalMaterialDetailForm'].total;
    var ratesAfter=document.forms['comEvalMaterialDetailForm'].ratesAfter;
    var rates=document.forms['comEvalMaterialDetailForm'].rates;
    if(total!=null){
        if (total.length!=null) {
            for (i = 0; i < total.length; i++) {
                priceAfter[i].value = reformatNumberMoneyString(pricePtscmc[i].value)*reformatNumberMoneyString(document.forms['comEvalMaterialDetailForm'].rates.value);
                total[i].value=reformatNumberMoneyString(quantity[i].value)*priceAfter[i].value;
                sum+=total[i].value*1;
                tryNumberFormat(priceAfter[i]);
                tryNumberFormat(total[i]);
            }
        }else {            
            priceAfter.value = reformatNumberMoneyString(pricePtscmc.value)*reformatNumberMoneyString(document.forms['comEvalMaterialDetailForm'].rates.value);            
            total.value=reformatNumberMoneyString(quantity.value)*priceAfter.value;
            sum+=total.value*1;            
            tryNumberFormat(priceAfter);
            tryNumberFormat(total);
           
        }
        document.forms['comEvalMaterialDetailForm'].ratesAfter.value=sum;
        tryNumberFormat(document.forms['comEvalMaterialDetailForm'].ratesAfter);
    }
    return false;
}
//ComClarification
function loadComClarificationList(){
    callAjax('comClarificationList.do',null,null,getComClarificationListData);
    return false;
}
function getComClarificationListData(data){
    setAjaxData(data,'ajaxContent');
    loadComClarifications();
    return false;
}
function loadComClarifications(){
    callAjax("comClarifications.do","comClarificationList",null,null);
    return false;
}
function comClarificationForm(tenId){

    var url="comClarificationForm.do";
    var tenId = document.forms['comEvalDetailForm'].tenId.value;
    var venId = document.forms['comEvalDetailForm'].venId.value;
    if(tenId!=null) url=url+"?tenId="+tenId+"&venId="+venId;
    callAjax(url,null,null,function(data){
        showPopupFormById("popupDialog",data);
        callAjax("comClarificationDisciplines.do","comClarificationList",null,null);
    });
    
    return false;
}
function comClarificationFormMaterial(tenId){
    var url="comClarificationForm.do";
    var tenId = document.forms['comEvalMaterialDetailForm'].tenId.value;
    var venId = document.forms['comEvalMaterialDetailForm'].venId.value;
    if(tenId!=null) url=url+"?tenId="+tenId+"&venId="+venId;
    callAjax(url,null,null,function(data){
        showPopupFormById("popupDialog",data);
        callAjax("comClarificationDisciplines.do","comClarificationList",null,null);
    });

    return false;
}
function saveComClarification() {
    var createdDate = document.forms['comClarificationForm'].createdDate;
    var tenId = document.forms['comClarificationForm'].tenId;
    if(createdDate!=null){
        for(var i=0;i<createdDate.length;i++){
            if(createdDate[i].value!=""){
                if(!isDate(createdDate[i].value,"dd/MM/yyyy")){
                    alert('Ng\u00E0y th\u00E1ng n\u0103m kh\u00F4ng ch\u00EDnh x\u00E1c (ng\u00E0y/th\u00E1ng/n\u0103m!!');
                    createdDate[i].focus();
                    createdDate=null;
                    return false;
                }
            }
        }
    }


    callAjaxCheckError("saveComClarification.do",null,document.forms['comClarificationForm'],function(data){
        if(data=="success") callAjax("comClarificationDisciplines.do","comClarificationList",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}
function delComClarificationDiscipline(){
    var checkflag = false;
    var tclId = document.forms['comClarificationForm'].tclId;
    if (tclId.length!=null) {
        for (i = 0; i < tclId.length; i++) {
            if (tclId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = tclId.checked;
    }
    if (checkflag == true) {
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delComClarificationDiscipline.do',null,document.forms['comClarificationForm'],function(data){
                if(data=="success") callAjax("comClarificationDisciplines.do","disciplineList",null,null);
            });
        }
    }
    tclId=null;
    return false;
}
function setEmployeeComClarification(tcId) {
    var url="getEmployeeComClarification.do";
    if(tcId!=null) url+="?tcId="+tcId;
    callAjax(url,'comClarificationEmployeeList',null,null);
    return false;
}
function setComClarificationDetail(terId) {
    var url="getEmployeeComClarification.do";
    if(terId!=null) url+="?terId="+terId;
    callAjax(url,'comClarificationEmployeeList',null,null);
    return false;
}
function addComClarificationDetail(tclId){
    var url="comClarificationDetailForm.do";
    if(tclId!=null) url=url+"?tclId="+tclId;
    callAjax(url,null,null,function(data){
        showPopupFormById("popupDialog",data);
        url='listComClarificationDetail.do';
        if(tclId!=null) url=url+"?tclId="+tclId;
        callAjax(url,'listComClarificationDetailDataDiv',null,null);
    });
    
    
    return false;
}
function saveComClarificationDetail() {
    callAjaxCheckError("saveComClarificationDetail.do",null,document.forms['comClarificationDetailForm'],function(data){
        if(data=="success"){
            callAjax("listComClarificationDetail.do","listComClarificationDetailDataDiv",null,null);
            callAjax("comClarificationDisciplines.do","comClarificationList",null,null);
            document.forms['comClarificationDetailForm'].subcategory.value=null;
            document.forms['comClarificationDetailForm'].reference.value=null;
            document.forms['comClarificationDetailForm'].clarification.value=null;
            document.forms['comClarificationDetailForm'].supplierReply.value=null;
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        }
    });
    return false;
}
function delComClarificationDetail(){
    var checkflag = false;
    var detId = document.forms['comClarificationDetailForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')){
            callAjaxCheckError('delComClarificationDetail.do',null,document.forms['comClarificationDetailForm'],function(data){
                if(data=="success") callAjax("listComClarificationDetail.do","listComClarificationDetailDataDiv",null,null);
            });
        }
    }
    detId=null;
    return false;
}
//BidEvalSum
function printBidEvalSum(){
    var besId=document.forms['bidEvalSumForm'].besId;
    if(besId!=null) callServer('bidEvalSumPrint.do?besId='+besId.value);
    besId = null;
}
function loadBidEvalSumList(){
    callAjax('bidEvalSumList.do',null,null,getBidEvalSumListData);
    return false;
}
function getBidEvalSumListData(data){
    setAjaxData(data,'ajaxContent');
    loadBidEvalSums();
    return false;
}
function loadBidEvalSums(){
    callAjax("bidEvalSums.do","bidEvalSumList",null,null);
    return false;
}
function bidEvalSumForm(tenId){
    var url="bidEvalSumForm.do";
    if(tenId!=null) url=url+"?tenId="+tenId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'tenderplanbidevalsum');
        setEmployeeBidEvalSum(tenId);
        setMaterialBidEvalSum(tenId);
        callAjax("bidEvalSumVendors.do?tenId="+tenId,"vendor2List",null,null);
        var besId = document.forms['bidEvalSumForm'].besId.value;
        if(besId!=null) {
            //Constants.ATTACH_FILE_BIDSUM
            loadAttachFileSystem(9,besId,'divAttachFileSystem6');
        }
    });
    
    return false;
}
function saveBidEvalSum() {
    var createdDate = document.forms['bidEvalSumForm'].createdDate;
    var tenId = document.forms['bidEvalSumForm'].tenId.value;
    var besNumber = document.forms['bidEvalSumForm'].besNumber.value;
    if(besNumber.length==0){
        alert('Vui l\u00F2ng nh\u1EADp v\u00E0o s\u1ED1 B\u00E1o c\u00E1o!');document.forms['bidEvalSumForm'].besNumber.focus();return false;
    }
    if(createdDate!=null){
        for(var i=0;i<createdDate.length;i++){
            if(createdDate[i].value!=""){
                if(!isDate(createdDate[i].value,"dd/MM/yyyy")){
                    alert('Ng\u00E0y th\u00E1ng n\u0103m kh\u00F4ng ch\u00EDnh x\u00E1c! (ng\u00E0y/th\u00E1ng/n\u0103m)');
                    createdDate[i].focus();
                    createdDate=null;
                    return false;
                }
            }
        }
    }
    callAjaxCheckError("saveBidEvalSum.do",null,document.forms['bidEvalSumForm'],function(data){
        callAjax("bidEvalSumVendors.do?tenId="+tenId,"vendor2List",null,null);
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        bidEvalSumForm(tenId);
    });
    return false;
}
function setEmployeeBidEvalSum(tenId) {
    var url="getEmployeeBidEvalSum.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'bidEvalSumEmployeeList',null,null);
    return false;
}
function setMaterialBidEvalSum(tenId) {
    var url="listMaterialBidEvalSum.do";
    if(tenId!=null) url+="?tenId="+tenId;
    callAjax(url,'bidEvalSumMaterialList',null,null);
    return false;
}
function checkAllBidEvalSumDetail(form, name){
    var object = eval("document.forms['"+form+"']."+name);
    if(object!=null){
        if(object.length!=null){
            for(var i=0;i<object.length;i++){
                object[i].checked = true
            }
        }
    }
    object=null;
    return false;
}
function selectMaterialTechEval(handle,title,detId,nameVn){
    if(handle==null) return false;
    var tenId = document.forms['bidEvalSumForm'].tenId.value;
    popupName=title;
    callAjax('chooseMaterialTechEvalForm.do?detId='+detId+'&tenId='+tenId,null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        document.forms['selectMaterialTechEvalForm'].searchid.value = 2;
        nameVn=document.getElementById('bidEvalSumTempName'+nameVn);
        if(nameVn!=null){
            nameVn=nameVn.value;
            document.forms['selectMaterialTechEvalForm'].searchvalue.value = nameVn.replace("^"," ");
        }
        searchMaterialTechEval();
    });
    return false;
}
function newMaterialTechEval(handle,id,title,detId,tenId,nameVn,detIdTp){
    popupName=title;
    if(handle==null){
        callAjax('materialForm.do?matId='+id,null,null,function(data){
            showPopupForm(data);
        });
    }
    else{
        callAjax('addMaterialTechEvalForm.do?detId='+detId+'&tenId='+tenId+'&detIdTp='+detIdTp,null,null,function(data){
            showPopupForm(data);
            nameVn=document.getElementById('bidEvalSumTempName'+nameVn);
            if(nameVn!=null){
                nameVn=nameVn.value;
                document.forms['addMaterialTechEval'].nameVn.value = nameVn.replace("^"," ");
            }
            document.getElementById('callbackFunc').value=handle;
        });
    }
    return false;
}
function saveMaterialTechEval(detId,tenId) {
    var nameVn = document.forms['addMaterialTechEval'].nameVn;
    var nameEn = document.forms['addMaterialTechEval'].nameEn;
    if (nameVn.value.length==0 && nameEn.value.length==0){
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn=null;
        return false;
    }
    var uniId = document.forms['addMaterialTechEval'].uniId;
    if(uniId.selectedIndex==0){
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId=null;
        return false;
    }
    callAjaxCheckError("addMaterialTechEval.do?detId="+detId+'&tenId='+tenId,null,document.forms['addMaterialTechEval'],function(data){        
        hidePopupForm();
        setMaterialBidEvalSum(document.forms['addMaterialTechEval'].tenId.value);
    });
    hidePopupForm();
    return false;
}
function setMaterialTechEvalDetail(matId){
    var url='materialDetail.do?matId='+matId;
    callAjax(url,null,null,function(data){
        var obj=eval('('+data+')');
        document.getElementById('code_'+matId).innerHTML=obj.matCode;
        document.getElementById('name_'+matId).innerHTML=obj.matName;
        document.getElementById('unit_'+matId).innerHTML=obj.matUnit;
    });
}
function setSelectedMaterialTechEval(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['requestForm'].matId;
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


function searchMaterialTechEval(params){
    var form=document.forms['selectMaterialTechEvalForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    url="searchMaterialTechEval.do";
    if(params!=null) url+="?"+params;
    callAjax(url,null,form,function(data){
        setAjaxData(data,'materialTechEvalList');
        var matId = document.forms['materialTechEvalForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialTechEvalSelectedForm'].materialSelectedChk;
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
function setMaterialTechEvalSelected(){
    var matId = document.forms['materialTechEvalForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialTechEvalForm'].matNameHidden;
    var matCodeHidden = document.forms['materialTechEvalForm'].matCodeHidden;
    var tbl=document.getElementById('materialTechEvalSelectedTbl');
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
    chooseMaterialTechEvalSelected();
}
function delMaterialTechEval(){
    var selId=document.getElementsByName('materialSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('materialTechEvalSelectedTbl');
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
    var matId = document.forms['materialTechEvalForm'].matId;
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
function chooseMaterialTechEvalSelected(){
    var selId=document.forms['materialTechEvalSelectedForm'].materialSelectedChk;
    var detId=document.forms['materialTechEvalForm'].detId.value;
    var tenId=document.forms['materialTechEvalForm'].tenId.value;
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
    callAjaxCheckError("saveBidEvalSumMaterial.do?matId="+ids+"&detId="+detId+"&tenId="+tenId,null,null,function(data){
        hidePopupForm();
        setMaterialBidEvalSum(tenId);
    });
    hidePopupForm();
}
function setBidEvalSumDetail(terId) {
    var url="getEmployeeBidEvalSum.do";
    if(terId!=null) url+="?terId="+terId;
    callAjax(url,'bidEvalSumEmployeeList',null,null);
    return false;
}
function addBidEvalSumGroup(){
    var list=document.forms['bidEvalSumForm'].employeeList;
    if(list==null || list.selectedIndex==-1) return false;
    var id=list.options[list.selectedIndex].value;
    var name=list.options[list.selectedIndex].text;
    list=null;
    var tbl=document.getElementById('bidEvalSumGroupTbl');
    var lastRow = tbl.rows.length;
    var row=document.createElement("tr");
    tbl.tBodies[0].appendChild(row);
    var cell = row.insertCell(0);
    var el = document.createElement('input');
    el.type = 'checkbox';
    el.name = 'bidEvalSumEmployeeChk';
    el.value=id;
    cell.appendChild(el);
    el = document.createElement('input');
    el.type = 'hidden';
    el.name = 'evalId';
    el.value='0';
    cell.appendChild(el);
    
    cell = row.insertCell(1);
    cell.align="center";
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalEmployee';
    el.value=name;
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(2);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalPosition';
    el.size=30;
    cell.appendChild(el);
    
    cell = row.insertCell(3);
    el = document.createElement('input');
    el.type = 'text';
    el.name = 'evalNote';
    el.size=30;
    cell.appendChild(el);
    
    el=null
    cell=null;
    row=null;
    tbl=null;
    return false;
}
function delBidEvalSumGroup(){

    var checkflag = false;
    var detId = document.forms['bidEvalSumForm'].bidEvalSumEmployeeChk;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delBidEvalSumGroup.do',null,document.forms['bidEvalSumForm'],function(data){
        {
                var tbl=document.getElementById('bidEvalSumGroupTbl');
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
function addBidEvalSumVendor(besvId,tenId){
    var url="bidEvalSumDetailForm.do";
    if(besvId!=null) url=url+"?besvId="+besvId+"&tenId="+tenId;
    callAjax(url,null,null,function(data){
        showPopupFormLoc(data);
        url='listBidEvalSumDetail.do';
        if(besvId!=null) url=url+"?besvId="+besvId+"&tenId="+tenId;
        callAjax(url,'listBidEvalSumDetailDataDiv',null,null);
    });
    return false;
}
function saveBidEvalSumDetail() {
    var tenId=document.forms['bidEvalSumDetailForm'].tenId.value;
    callAjaxCheckError("saveBidEvalSumDetail.do",null,document.forms['bidEvalSumDetailForm'],function(data){
        hidePopupForm();
        callAjax("bidEvalSumVendors.do?tenId="+tenId,"vendor2List",null,null);
    });
    return false;
}
function printRequest(reqId,kind){
    if (reqId==null) {
        reqId=document.forms['requestForm'].reqId;
        if (reqId!=null) reqId = reqId.value;        
    }
    var url='requestPrint.do?t=1';
    if(reqId!=null){
        url+='&reqId='+reqId;
        if(kind!=null) url+='&kind='+kind;
        callServer(url);
    }
    reqId=null;
    return false;
}
function printComEval(){
    var ceId=document.forms['comEvalForm'].ceId;
    if(ceId!=null) callServer('comEvalPrint.do?ceId='+ceId.value);
    ceId=null;
}
function printTechEval(){
    var teId=document.forms['techEvalForm'].teId;
    var kind=document.forms['tenderPlanForm'].evalKind;
    if(teId!=null) callServer('techEvalPrint.do?teId='+teId.value+'&kind='+kind.value);
    teId=null;
    kind=null;
}
function printComEvalMaterial(){
    var ceId=document.forms['comEvalMaterialForm'].ceId;
    if(ceId!=null) callServer('comEvalMaterialPrint.do?ceId='+ceId.value);
    ceId=null;
}

function printContractFollow(){
    var folId=document.forms['contractFollowForm'].folId;
    if(folId!=null) callServer('contractFollowPrint.do?folId='+folId.value);
    folId=null;
}

function printTenderPlanBidOpening(){
    var borId=getTenderPlanBidOpeningId();
    callServer('bidOpeningReportPrint.do?borId='+borId);
    borId=null;
}

function printTenderPlanBidClosing(){
    var bcrId=getTenderPlanBidClosingId();
    callServer('bidClosingReportPrint.do?bcrId='+bcrId);
    bcrId=null;
}

function printTenderLetterPrint(letId){
    if(letId!=null) callServer('tenderLetterPrint.do?letId='+letId);
}

function printContract(conId){
    //phuongtu
    if(conId==null) conId=document.forms['contractForm'].conId.value;
    if(conId!=null) callServer('contractPrint.do?conId='+conId);
}
function printVendorEval(){
    var venEvalId=document.forms['venEvalForm'].evalId;
    if(venEvalId!=null) callServer('venEvalPrint.do?venEvalId='+venEvalId.value);
    venEvalId=null;
}
function printOrder(conId){
    if(conId==null){
        conId=document.forms['contractForm'].conId;
        if(conId!=null) conId=conId.value;
    }
    callServer('orderPrint.do?conId='+conId);
    conId=null;
}
function printDeliveryRequest(){
    var drId=document.forms['contractForm'].conId;
    if(drId!=null) callServer('deliveryRequestPrint.do?drId='+drId.value);
    drId=null;
}
function printDXKDeliveryRequest(){
    var drId=document.forms['contractForm'].conId;
    if(drId!=null) callServer('dXKDeliveryRequestPrint.do?drId='+drId.value);
    drId=null;
}
function printPrinciple(conId){
    //phuongtu
    if(conId==null) conId=document.forms['contractForm'].conId.value;
    if(conId!=null) callServer('principlePrint.do?conId='+conId);
}
function printContractAppendix(){
    var conId=document.forms['appendixForm'].conId;
    if(conId!=null) callServer('contractAppendixPrint.do?conId='+conId.value);
    conId=null;
}
function closeContract(){
    var conId=document.forms['contractForm'].conId;
    if(conId!=null){
        if(confirm('H\u1EE3p \u0111\u1ED3ng \u0111\u00E3 \u0111\u00F3ng s\u1EBD kh\u00F4ng th\u1EC3 ch\u1EC9nh s\u1EEDa. B\u1EA1n c\u00F3 mu\u1ED1n ti\u1EBFp t\u1EE5c ?')) callAjax('closeContract.do?conId='+conId.value,null,null,loadContractList);
        //phuongtu
        callAjax('saveContractMaterialPrice.do?conId='+conId.value,null,null,null);
    }
    conId=null;
}
function saveContractMaterialPrice(){
    var conId=document.forms['contractForm'].conId;
    if(conId!=null) if(confirm('B\u1EA1n mu\u1ED1n ghi nh\u1EADn gi\u00E1 VTTB v\u00E0o c\u01A1 s\u1EDF d\u1EEF li\u1EC7u ?')) callAjax('saveContractMaterialPrice.do?conId='+conId.value,null,null,loadContractList);
    conId=null;
}
function printContractProcessFollow(){
    var contractNumber=document.forms['searchContractForm'].contractNumber.value;
    var effectedFromDate=document.forms['searchContractForm'].effectedFromDate.value;
    var effectedToDate=document.forms['searchContractForm'].effectedToDate.value;
    var moveAccountingFromDate=document.forms['searchContractForm'].moveAccountingFromDate.value;
    var moveAccountingToDate=document.forms['searchContractForm'].moveAccountingToDate.value;
    var deliveryDateAsContractFromDate=document.forms['searchContractForm'].deliveryDateAsContractFromDate.value;
    var deliveryDateAsContractToDate=document.forms['searchContractForm'].deliveryDateAsContractToDate.value;
    //var expireFromDate=document.forms['searchContractForm'].expireFromDate.value;
    //var expireToDate=document.forms['searchContractForm'].expireToDate.value;
    var venId=document.forms['searchContractForm'].venId.value;
    var vendorName=document.forms['searchContractForm'].vendorName.value;
    //var totalFrom=document.forms['searchContractForm'].totalFrom.value;
    //var totalTo=document.forms['searchContractForm'].totalTo.value;
    var paymentStatus=document.forms['searchContractForm'].paymentStatus.value;
    var kind=document.forms['searchContractForm'].kind.value;
    var handleEmp=document.forms['searchContractForm'].handleEmp.value;
    var followEmp=document.forms['searchContractForm'].followEmp.value;
    var handleEmpName=document.forms['searchContractForm'].handleEmpName.value;
    var followEmpName=document.forms['searchContractForm'].followEmpName.value;
    var proId=document.forms['searchContractForm'].proId.value;
    var url='printContractProcessFollow.do?contractNumber='+contractNumber;
    url+='&kind='+kind;
    url+='&effectedFromDate='+effectedFromDate;
    url+='&effectedToDate='+effectedToDate;
    url+='&moveAccountingFromDate='+moveAccountingFromDate;
    url+='&moveAccountingToDate='+moveAccountingToDate;
    url+='&deliveryDateAsContractFromDate='+deliveryDateAsContractFromDate;
    url+='&deliveryDateAsContractToDate='+deliveryDateAsContractToDate;
//    url+='&expireFromDate='+expireFromDate;
//    url+='&expireToDate='+expireToDate;
    url+='&venId='+venId;
    url+='&vendorName='+encodeURIComponent(vendorName);
    url+='&handleEmpName='+encodeURIComponent(handleEmpName);
    url+='&followEmpName='+encodeURIComponent(followEmpName);
//    url+='&totalFrom='+totalFrom;
//    url+='&totalTo='+totalTo;
    url+='&paymentStatus='+paymentStatus;
    url+='&proId='+proId;
    url+='&handleEmp='+handleEmp;
    url+='&followEmp='+followEmp;
    callServer(url);
    hidePopupForm();
    return false;
}
function printContractPaymentSum(){
    var conId=document.forms['contractForm'].conId;
    var kind=document.forms['contractForm'].kind;
    if(conId!=null) callServer('contractPaymentSumPrint.do?conId='+conId.value+'&kind='+kind.value);
    conId=null;
    kind=null;
}

//Delivery Notice
function loadDnList(params){
    callAjaxEx('dnList.do?kind=1','ajaxContent','searchDn.do?kind=1','dnList',params);
    return false;
}
function loadDnListSort(params){
    callAjaxEx('searchDn.do?kind=1','dnList',null,null,params);
    return false;
}
function loadDnTestList(params){
    callAjaxEx('dnList.do?kind=5','ajaxContent','searchDn.do?kind=5','dnList',params);
    return false;
}
function getDnListData(data){
    setAjaxData(data,'ajaxContent');
    loadDns();
    return false;
}
function loadDns(){
    callAjax("dns.do","dnList",null,function(data){
        setAjaxData(data);
        list=document.forms['dnForm'].whichUse;
        alert(list)
        if(list!=null)
            whichUseChangedDn(list);
        list=null;
    });
    return false;
}
function whichUseChangedDn(list,conId){
    if(list.selectedIndex==-1) return false;
    var dnId=null;
    removeDnContractMaterial('dnDetailTable');
    if(document.forms['dnForm']!=null) dnId=document.forms['dnForm'].dnId;
    getWhichUseDn(dnId.value,list.options[list.selectedIndex].value,conId);
    list=null;
    dnId=null;
    return false;
}
function removeDnContractMaterial(name){
    var tbl=document.getElementById(name);
    if(tbl.rows.length!=null){
        var lastRow = tbl.rows.length;
        for (i=lastRow-1;i>=1;i--) {
            tbl.deleteRow(i);
        }
    }else{
        tbl.deleteRow(1);
    }
    tbl=null;
}
function getWhichUseDn(dnId,whichUse,conId){
    var url="whichUseDnList.do?whichUse="+whichUse;
    if(dnId!=null) url+="&dnId="+dnId;
    if(conId!=null) url+="&conId="+conId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'whichUseSpan');
        if(document.forms['dnForm'].conId!=null) document.forms['dnForm'].conId.value = conId;
        if(document.forms['dnForm'].drId!=null) document.forms['dnForm'].drId.value = conId;
    });
}
function searchContractForDn(searchValue, span){
    var whichUse=document.forms['dnForm'].whichUse;
    var dnId=null;
    if(document.forms['dnForm']!=null) dnId=document.forms['dnForm'].dnId;
    whichUse=whichUse.options[whichUse.selectedIndex].value;
    var url="whichUseDnList.do?whichUse="+whichUse;
    if(dnId!=null) url+="&dnId="+dnId;
    if(searchValue!=null) url += "&searchValue="+searchValue;
    callAjax(url,null,null,function(data){
        setAjaxData(data,span);
    });
}
function addDn(dnId, kind){
    var url="dnForm.do";    
    if (kind == 0 ) kind = document.forms['dnsForm'].kind.value;
    if(dnId!=null) url=url+"?dnId="+dnId+"&kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        dnd=document.forms['dnForm'].dnId;
        if(dnd!=null) dnd=dnd.value;
        list=document.forms['dnForm'].whichUse;
        if(dnId!=null){
            callAjax('listDnDetail.do?dnId='+document.forms['dnForm'].dnId.value,null,null,function(data){
                setAjaxData(data,'listDnMaterialDataDiv');
            });
        }
        var conId_hid=document.forms['dnForm'].conId_hid;
        if(list!=null)
            whichUseChangedDn(list,conId_hid.value);
        list=null;
    //loadRequestInMsv(document.forms['dnForm'].stoId.value);
    });
    return false;
}
function dnForm(reqId){
    var url="dnForm.do";
    var kind = document.forms['dnsForm'].kind.value;
    if(reqId!=null) url=url+"?reqId="+reqId;
    if(kind!=null) url=url+"?kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        //loadRequestInMsv(document.forms['dnForm'].stoId.value);
        if(reqId!=null){
            callAjax('listDnDetail.do?reqId='+reqId,null,null,function(data){
                setAjaxData(data,'listDnMaterialDataDiv');                
            });
        }
    });
    return false;
}
function dnVendorChanged(list){
    if(list.selectedIndex==-1) return false;
    var url="dnVendorList.do?venId="+list.options[list.selectedIndex].value;
    callAjax(url,'dnContractSpan',null,null);
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
    var url='materialListForDn.do?reqId='+reqId;
    callAjax(url,'listMaterialDataSpan',null,null);
}
function stoChanged(list){
    //if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    //loadRequestInMsv(list.options[list.selectedIndex].value);
    list=null;
    return false;
}
function loadRequestInMsv(stoId){
    var url='requestListForDn.do?stoId='+stoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listRequestDataSpan');
        if (document.getElementById('reqId')!=null) loadMaterialInMsv(document.getElementById('reqId').value);
    });
}
function removeDnMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('dnDetailTable');
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
function dnCheckQt(detId){
    var detquantity=document.getElementById("detquantity"+detId).value;
    var qt=document.getElementById("detqt"+detId).value;
    var qtTemp=document.getElementById("qtTemp"+detId).value;
    var qtDn=document.getElementById("qtDn"+detId).value;

    if (detquantity/1<0) {
        document.getElementById("detquantity"+detId).value=qt/1;
        alert("SL nh\u1EADp v\u00E0o ph\u1EA3i l\u1EDBn h\u01A1n 0!");
    }
    if (detquantity/1>(qtDn/1 + qtTemp/1)) {
        document.getElementById("detquantity"+detId).value=qtDn/1 + qtTemp/1;
        alert("SL nh\u1EADp v\u00E0o ph\u1EA3i <= " + document.getElementById("detquantity"+detId).value);
    }
    return false;
}
function selectDnMaterial(handle){
    if(handle==null) return false;
    var conId = document.forms['dnForm'].conId;
    var drId = document.forms['dnForm'].drId;
    var whichUse = document.forms['dnForm'].whichUse;
    if((whichUse.selectedIndex==0 && conId.selectedIndex==0) || (whichUse.selectedIndex==0 && drId.selectedIndex==0)){
        alert("S\u1ED1 H\u0110/\u0110NGH?");
        whichUse.focus();
        whichUse=null;
        return false;
    }
    callAjax('chooseDnMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        var id=document.forms['dnForm'].matId;
        var ids='';
        if(id!=null){
            if(id.length!=null){
                for (i = 0; i < id.length; i++) {
                    ids+=','+id[i].value;
                }
            }else ids+=','+id.value;
        }
        document.getElementById('parentFormMaterial').value=ids;
        document.getElementById('callbackFunc').value=handle;
        searchDnMaterialRequest();
    });
    return false;
}
function chooseDnMaterialSelected(){
    var selId=document.forms['materialDnRequestSelectedForm'].materialSelectedChk;    
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
function checkAllDnMaterial(form, name){
    var object = eval("document.forms['"+form+"']."+name);
    if(object!=null){
        if(object.length!=null){
            for(var i=0;i<object.length;i++){
                object[i].checked = true
            }
        }
    }
    object=null;
    return false;
}
function searchDnMaterialRequest(params){
    var form=document.forms['selectDnMaterialForm'];
    var conId=document.forms['dnForm'].conId;
    var drId=document.forms['dnForm'].drId;
    if(conId!=null) conId=conId.value;
    else conId='';
    if(drId!=null) drId=drId.value;
    else drId='';
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchDnMaterialRequest.do?"+params+"&conId="+conId+"&drId="+drId,null,form,function(data){
        setAjaxData(data,'materialDnRequestList');
        var detId = document.forms['materialDnRequestForm'].detId;
        var matId = document.forms['materialDnRequestForm'].matId;
        if(matId==null) return false;
        var ids='0'+document.getElementById('parentFormMaterial').value;
        var selId=document.forms['materialDnRequestSelectedForm'].materialSelectedChk;
        //if(selId==null) return false;        
        //var ids='0';
         if(selId!=null){
        if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
        }else ids+=','+selId.value;
        selId=null;
        }ids+=',0';
        if (matId.length!=null){
            for (i = 0; i < matId.length; i++) {
                if (ids.indexOf(','+matId[i].value+',')>-1){
                    detId[i].disabled=true;
                    detId[i].checked=true;
                }
            }
            matId=null;
        }else if (ids.indexOf(','+matId.value+',')>-1){
            detId.disabled=true;
            detId.checked=true;
        }
        matId=null;
    });
    form=null;
    return false;
}
function setDnMaterialSelected(){
    var detId = document.forms['materialDnRequestForm'].detId;
    if(detId==null) return false;
    var matNameHidden = document.forms['materialDnRequestForm'].matNameHidden;
    var matCodeHidden = document.forms['materialDnRequestForm'].matCodeHidden;
    var matUnitHidden = document.forms['materialDnRequestForm'].matUnitHidden;
    var matQuantityHidden = document.forms['materialDnRequestForm'].matQuantityHidden;
    var requestNumberHidden = document.forms['materialDnRequestForm'].requestNumberHidden;
    var tbl=document.getElementById('materialRequestSelectedTbl');
    var lastRow = tbl.rows.length;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true && detId[i].disabled==false) {
                detId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'materialSelectedChk';
                el.id="materialSelectedChk";
                el.value=detId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(matNameHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(2);
                textNode = document.createTextNode(matCodeHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(3);
                textNode = document.createTextNode(matUnitHidden[i].value);
                cell.appendChild(textNode);

                cell = row.insertCell(4);
                textNode = document.createTextNode(matQuantityHidden[i].value);
                cell.appendChild(textNode);
                
                cell = row.insertCell(5);
                textNode = document.createTextNode(requestNumberHidden[i].value);
                cell.appendChild(textNode);
                
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (detId.checked == true && detId.disabled==false) {
            detId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'materialSelectedChk';
            el.id="materialSelectedChk";
            el.value=detId.value;
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
            textNode = document.createTextNode(requestNumberHidden.value);
            cell.appendChild(textNode);
                       
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    detId=null;
    matNameHidden=null;
    matCodeHidden=null;
    tbl=null;
}
function delDnMaterialRequest(){
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
    var matId = document.forms['materialDnRequestForm'].matId;
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
function setSelectedDnMaterial(ids,conId1){
    //alert(ids)
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var detIds=document.forms['dnForm'].detId;
    var reqDetailIds=document.forms['dnForm'].reqDetailId;
    var conId=document.forms['dnForm'].conId;
    var drId=document.forms['dnForm'].drId;
    if(conId!=null) conId=conId.value;
    if(drId!=null) drId=drId.value;
    if (conId1 != null ) conId = conId1;
    if(detIds!=null){
        ids='0';
        if(detIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<detIds.length;j++){
                    if(idLst[i]==detIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=detIds.value) ids+=","+idLst[i];
            }
        }
        detIds=null;
        if(ids=='0') return;
    }
    callAjax("dnMaterial.do?detIds="+ids+"&conId="+conId+"&drId="+drId,null,null,function(data){
        setAjaxData(data,'dnMaterialHideDiv');
        var matTable=document.getElementById('dnMaterialTable');
        var detTable=document.getElementById('dnDetailTable');
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
function setSelectedDnAdjustmentMaterial(ids,conId1){
    //alert(ids)
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var detIds=document.forms['dnForm'].detId;
    var reqDetailIds=document.forms['dnForm'].reqDetailId;
    var conId=document.forms['dnForm'].conId;
    var drId=document.forms['dnForm'].drId;
    if(conId!=null) conId=conId.value;
    if(drId!=null) drId=drId.value;
    if (conId1 != null ) conId = conId1;
    if(detIds!=null){
        ids='0';
        if(detIds.length!=null){
            var isExisted;
            for(var i=0;i<idLst.length;i++){
                isExisted=false;
                for(var j=0;j<detIds.length;j++){
                    if(idLst[i]==detIds[j].value){
                        isExisted=true;
                        break;
                    }
                }
                if(isExisted==false) ids+=","+idLst[i];
            }
        }else{
            for(var i=0;i<idLst.length;i++){
                if(idLst[i]!=detIds.value) ids+=","+idLst[i];
            }
        }
        detIds=null;
        if(ids=='0') return;
    }
    callAjax("dnAdjustmentMaterial.do?detIds="+ids+"&conId="+conId+"&drId="+drId,null,null,function(data){
        setAjaxData(data,'dnMaterialHideDiv');
        var matTable=document.getElementById('dnMaterialTable');
        var detTable=document.getElementById('dnDetailTable');
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
function saveDn(){
    if(scriptFunction=="saveDn()") return false;
    var dnNumber = document.forms['dnForm'].dnNumber;
    var createdOrg = document.forms['dnForm'].createdOrg;
    var conId = document.forms['dnForm'].conId;
    var drId = document.forms['dnForm'].drId;
//    var whichUse = document.forms['dnForm'].whichUse;
    var kind = document.forms['dnForm'].kind.value;
    if(dnNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 th\u00F4ng b\u00E1o!");
        dnNumber.focus();
        dnNumber=null;
        return false;
    }
    if((conId!=null && conId.selectedIndex==-1) && (drId!=null && drId.selectedIndex==-1)){
        alert("S\u1ED1 H\u0110/\u0110NGH?");
        createdOrg.focus();
        createdOrg=null;
        return false;
    }
    if(createdOrg.selectedIndex==-1){
        alert("B\u1ED9 ph\u1EADn?");
        createdOrg.focus();
        createdOrg=null;
        return false;
    }
    dnNumber=null;
    var dnId = document.forms['dnForm'].dnId.value;
    if (kind==1){
        //callAjaxCheckError("saveDn.do?kind="+kind,null,document.forms['dnForm'],loadDnList);\
        scriptFunction="saveDn()";
        callAjaxCheckError("saveDn.do?kind="+kind,null,document.forms['dnForm'],function(data){
            scriptFunction="";
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
            addDn(dnId,kind)
        });
    } else {
        //callAjaxCheckError("saveDn.do?kind="+kind,null,document.forms['dnForm'],loadDnTestList);
        scriptFunction="saveDn()";
        callAjaxCheckError("saveDn.do?kind="+kind,null,document.forms['dnForm'],function(data){
            scriptFunction="";
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
            addDn(dnId,kind)
        });
    }
    return false;
}
function searchDn(){
    var kind = document.getElementById('kind').value;    
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchDn.do?kind="+kind,"dnList",form,null);
    form=null;
    return false;
    }
function searchAdvDnForm(){
    var kind = document.getElementById('kind').value;
    callAjax('searchAdvDnForm.do?kind='+kind,null,null,function(data){
        //setAjaxData(data,'ajaxContent');
        showPopupForm(data);
        //loadRequestInMsv(document.forms['searchDnForm'].stoId.value);
        });

    return false;
    }
function searchAdvDn(){
    var kind = document.getElementById('kind').value;
    callAjax('searchAdvDn.do?kind='+kind,null,document.forms['searchDnForm'],getSearchAdvDnData);
    hidePopupForm();
    return false;
    }
function getSearchAdvDnData(data){
    setAjaxData(data,'dnList');
    }
function delDns(){
    var checkflag = false;
    var dnId = document.forms['dnsForm'].dnId;
    if(dnId==null) return false;
    if (dnId.length!=null) {
    for (i = 0; i < dnId.length; i++) {
    if (dnId[i].checked == true) {
    checkflag = true;
    break;
    }
    }
    } else checkflag = dnId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delDn.do',null,document.forms['dnsForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else setAjaxData(data,'dnList');
    });
    dnId=null;
    return false;
    }
function delDnDetails(){
    var checkflag = false;
    var detId = document.forms['dnForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
    for (i = 0; i < detId.length; i++) {
    if (detId[i].checked == true) {
    checkflag = true;
    break;
    }
    }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delDnDetail.do',null,document.forms['dnForm'],function(data){
        var tbl=document.getElementById('dnDetailTable');
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
    //removeDnMaterial();
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
    if(kind=='0') callAjaxExChild("materialInStore.do?kind="+kind,"materialInStoreList",params);
    else callAjaxExChild("materialInStore1.do?kind="+kind,"materialInStoreList",params);
    return false;
}
function searchDnMaterialInStore(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {

            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchDnMaterialInStore.do?kind=0","materialInStoreList",form,null);
    form=null;
    return false;
}
function dnCheckQuantity(matId){
    var detquantity=document.getElementById("detquantity"+matId).value;
    var qt=document.getElementById("detqt"+matId).value;
    if (detquantity/1<0 || detquantity/1>qt/1) {
        document.getElementById("detquantity"+matId).value=document.getElementById("detqt"+matId).value;
        alert("SL nh\u1EADp v\u00E0o b\u1ECB sai!");
    }
    return false;
}
function printDn(dnId){
    if (dnId==null) {
        dnId=document.forms['dnForm'].dnId;
        if(dnId!=null) dnId=dnId.value;
    }
    if(dnId!=null) callServer('dnPrint.do?dnId='+dnId);
    dnId=null;
}

//Edelivery Notice
function loadEdnList(params){
    callAjaxEx('ednList.do?','ajaxContent','searchEdn.do?','ednList',params);
    return false;
}
function loadEdnListSort(params){
    callAjaxEx('searchEdn.do','ednList',null,null,params);
    return false;
}
function getEdnListData(data){
    setAjaxData(data,'ajaxContent');
    loadEdns();
    return false;
}
function loadEdns(){
    callAjax("edns.do","ednList",null,function(data){
        setAjaxData(data);
        list=document.forms['ednForm'].whichUse;
        alert(list)
        if(list!=null)
            whichUseChangedEdn(list);
        list=null;
    });
    return false;
}
function whichUseChangedEdn(list){
    if(list.selectedIndex==-1) return false;
    var dnId=null;
    if(document.forms['ednForm']!=null) dnId=document.forms['ednForm'].dnId;
    getWhichUseEdn(dnId.value,list.options[list.selectedIndex].value);
    list=null;
    dnId=null;
    return false;
}
function getWhichUseEdn(dnId,whichUse){
    var url="whichUseEdnList.do?whichUse="+whichUse;
    if(dnId!=null) url+="&dnId="+dnId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'whichUseSpan');
    });
}
function addEdn(dnId,kind){
    var url="ednForm.do";
    if(dnId!=null) url=url+"?dnId="+dnId+"&kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        list=document.forms['ednForm'].whichUse;
        if(list!=null)
            whichUseChangedEdn(list);
        list=null;
    //loadRequestInMsv(document.forms['ednForm'].stoId.value);
    });
    return false;
}
function ednForm(reqId){
    var url="ednForm.do";
    var kind = document.forms['ednsForm'].kind.value;
    if(reqId!=null) url=url+"?reqId="+reqId;
    if(kind!=null) url=url+"?kind="+kind;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        //loadRequestInMsv(document.forms['ednForm'].stoId.value);
        if(reqId!=null){
            callAjax('listEdnDetail.do?reqId='+reqId,null,null,function(data){
                setAjaxData(data,'listEdnMaterialDataDiv');
            });
        }
    });
    return false;
}
function ednVendorChanged(list){
    if(list.selectedIndex==-1) return false;
    var url="ednVendorList.do?venId="+list.options[list.selectedIndex].value;
    callAjax(url,'ednContractSpan',null,null);
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
    var url='materialListForEdn.do?reqId='+reqId;
    callAjax(url,'listMaterialDataSpan',null,null);
}
function stoChanged(list){
    //if(list.selectedIndex==-1 || list.selectedIndex==0) return false;
    //loadRequestInMsv(list.options[list.selectedIndex].value);
    list=null;
    return false;
}
function loadRequestInMsv(stoId){
    var url='requestListForEdn.do?stoId='+stoId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'listRequestDataSpan');
        if (document.getElementById('reqId')!=null) loadMaterialInMsv(document.getElementById('reqId').value);
    });
}
function removeEdnMaterial(){
    var selId=document.getElementsByName('matId');
    if(selId==null) return false;
    var tbl=document.getElementById('ednDetailTable');
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
function selectEdnMaterial(handle){
    if(handle==null) return false;
    callAjax('chooseEdnMaterialForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        searchEdnMaterialRequest();
    });
    return false;
}
function chooseEdnMaterialSelected(){
    var selId=document.forms['materialEdnRequestSelectedForm'].materialSelectedChk;
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
function searchEdnMaterialRequest(params){
    var form=document.forms['selectEdnMaterialForm'];    
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchEdnMaterialRequest.do?"+params,null,form,function(data){
        setAjaxData(data,'materialEdnRequestList');
        var matId = document.forms['materialEdnRequestForm'].matId;
        if(matId==null) return false;
        var selId=document.forms['materialEdnRequestSelectedForm'].materialSelectedChk;
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
function setEdnMaterialSelected(){
    var matId = document.forms['materialEdnRequestForm'].matId;
    if(matId==null) return false;
    var matNameHidden = document.forms['materialEdnRequestForm'].matNameHidden;
    var matCodeHidden = document.forms['materialEdnRequestForm'].matCodeHidden;
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
function delEdnMaterialRequest(){
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
    var matId = document.forms['materialEdnRequestForm'].matId;
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
function setSelectedEdnMaterial(ids){
    if(ids=='' || ids==null) return;
    var idLst=ids.split(',');
    var matIds=document.forms['ednForm'].matId;
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
    callAjax("ednMaterial.do?matIds="+ids,null,null,function(data){
        setAjaxData(data,'ednMaterialHideDiv');
        var matTable=document.getElementById('ednMaterialTable');
        var detTable=document.getElementById('ednDetailTable');
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
function saveEdn(){
    var dnNumber = document.forms['ednForm'].dnNumber;
    var createdOrg = document.forms['ednForm'].createdOrg;
    var proId = document.forms['ednForm'].proId;
    var contractNumber = document.forms['ednForm'].contractNumber;
    if(dnNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp S\u1ED1 th\u00F4ng b\u00E1o!");
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
    if(contractNumber.value==''){
        alert("S\u1ED1 H\u0110/\u0110NGH?");
        contractNumber.focus();
        contractNumber=null;
        return false;
    }
    if(createdOrg.selectedIndex==0){
        alert("B\u1ED9 ph\u1EAdn?");
        createdOrg.focus();
        createdOrg=null;
        return false;
    }
    dnNumber=null;
    contractNumber=null;
    //callAjaxCheckError("saveEdn.do",null,document.forms['ednForm'],loadEdnList);
    var dnId = document.forms['dnForm'].dnId.value;
    callAjaxCheckError("saveEdn.do",null,document.forms['ednForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        addEdn(dnId)
        });
    return false;
}
function searchEdn(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchEdn.do?kind=0","ednList",form,null);
    form=null;
    return false;
}
function searchAdvEdnForm(){
    callAjax('searchAdvEdnForm.do?kind=0',null,null,function(data){
        //setAjaxData(data,'ajaxContent');
        showPopupForm(data);
    //loadRequestInMsv(document.forms['searchEdnForm'].stoId.value);
    });

    return false;
}
function searchAdvEdn(){
    callAjax('searchAdvEdn.do',null,document.forms['searchEdnForm'],getSearchAdvEdnData);
    hidePopupForm();
    return false;
}
function getSearchAdvEdnData(data){
    setAjaxData(data,'ednList');
}
function delEdns(){
    var checkflag = false;
    var dnId = document.forms['ednsForm'].dnId;
    if(dnId==null) return false;
    if (dnId.length!=null) {
        for (i = 0; i < dnId.length; i++) {
            if (dnId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = dnId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delEdn.do','ednList',document.forms['ednsForm'],null);
    dnId=null;
    return false;
}
function delEdnDetails(){
    var checkflag = false;
    var detId = document.forms['ednForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delEdnDetail.do',null,document.forms['ednForm'],function(data){
        var tbl=document.getElementById('ednDetailTable');
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
    //removeEdnMaterial();
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
    if(kind=='0') callAjaxExChild("materialInStore.do?kind="+kind,"materialInStoreList",params);
    else callAjaxExChild("materialInStore1.do?kind="+kind,"materialInStoreList",params);
    return false;
}


function searchEdnMaterialInStore(){
    var checkflag = true;
    var form=document.forms['searchSimpleForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchEdnMaterialInStore.do?kind=0","materialInStoreList",form,null);
    form=null;
    return false;
}
function ednCheckQuantity(matId){
    var detquantity=document.getElementById("detquantity"+matId).value;
    var qt=document.getElementById("detqt"+matId).value;
    if (detquantity/1<0 || detquantity/1>qt/1) {
        document.getElementById("detquantity"+matId).value=document.getElementById("detqt"+matId).value;
        alert("SL nh\u1EADp v\u00E0o b\u1ECB sai!");
    }
    return false;
}
function printEdn(dnId){
    if (dnId==null) {
        var dnId=document.forms['ednForm'].dnId;
        if(dnId!=null) dnId=dnId.value;
    }    
    if(dnId!=null) callServer('ednPrint.do?dnId='+dnId);
    dnId=null;
}
function setSelectedPaymentBill(){
    var list=document.forms['paymentForm'].invoice;
//    if(list.selectedIndex==-1) return false;
    var invoiceId=0;
    if(list.selectedIndex>-1){
        invoiceId=list.options[list.selectedIndex].value;
        var invId = document.forms['paymentForm'].invId;
        var existed=false;
        if(invId!=null){
            if (invId.length!=null) {
                for (i = 0; i < invId.length; i++) {
                    if (invId[i].value == invoiceId) {
                        existed = true;
                        break;
                    }
                }
            } else if(invId.value==invoiceId) existed=true;
        }
        if(existed==true){
            alert('Ho\u00E1 \u0111\u01A1n \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
        invId=null;
        list=null;
    }
    
    list=document.forms['paymentForm'].conId;
    var conId=0;
    if(list.selectedIndex>-1){
        conId=list.options[list.selectedIndex].value;
        var conIds = document.forms['paymentForm'].payConId;
        existed=false;
        if(conIds!=null){
            if (conIds.length!=null) {
                for (i = 0; i < conIds.length; i++) {
                    if(invoiceId==0){
                        if (conIds[i].value == conId) {
                            existed = true;
                            break;
                        }
                    }
                }
            } else{
                if(invoiceId==0){
                    if(conIds.value==conId) existed=true;
                }
            }
        }
        if(existed==true){
            alert('H\u1EE3p \u0111\u1ED3ng \u0111\u00E3 t\u1ED3n t\u1EA1i');
            return false;
        }
        conIds=null;
        list=null;
    }
    callAjax("listPaymentInvoice.do?invId="+invoiceId+"&conId="+conId,null,null,function(data){
        setAjaxData(data,'paymentInvoiceHideDiv');
        var matTable=document.getElementById('billTable');
        var detTable=document.getElementById('paymentBillTable');
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
        
        var amount=document.forms['paymentForm'].amount;
        if(amount!=null){
            if (amount.length!=null) {
                for (i = 0; i < amount.length; i++) {
                    tryNumberFormat(amount[i]);
                }
            } else{
                tryNumberFormat(amount);
            }
        }
        amount=null;
        
        caculatePayment();
    });   
    return false;
}
function caculatePayment(){
    var amount=document.forms['paymentForm'].amount;
    var total=document.forms['paymentForm'].total;
    var totalPay=document.forms['paymentForm'].totalPay;
    var punish=document.forms['paymentForm'].punish;
    var sum=0;
    if(amount!=null){
        if (amount.length!=null) {
            for (i = 0; i < amount.length; i++) {
                sum+=reformatNumberMoneyString(amount[i].value)*1;
            }
        } else sum+=reformatNumberMoneyString(amount.value)*1;
        amount=null;
    }


    total.value=sum;
    totalPay.value=sum - reformatNumberMoneyString(punish.value)*1;
    tryNumberFormat(total);
    tryNumberFormat(totalPay);
    tryNumberFormat(punish);
    total=null;
    totalPay=null;
    punish=null;
    return false;
}

function printContractEx(conId){   
    if(conId!=null) callServer('printContractEx.do?conId='+conId);

    conId=null;
    return false;
}
//INVOICE
function loadInvoiceList(params){
    callAjaxEx('invoiceList.do','ajaxContent','searchInvoice.do','invoiceList',params);
    return false;
}
function loadInvoiceListSort(params){
    //phan trang loi: callAjaxEx('invoices.do','invoiceList',null,null,params);
    callAjaxEx('searchInvoice.do','invoiceList',null,null,params);
    return false;
}
//function loadInvoiceListSort(params){
//    callAjaxEx('invoices.do','invoiceList',null,null,params);
//    return false;
//}
function getInvoiceListData(data){
    setAjaxData(data,'ajaxContent');
    loadInvoices();
    return false;
}
function loadInvoices(){
    callAjax("searchInvoice.do","invoiceList",null,null);
    return false;
}
function invoiceForm(invId){
    var url="invoiceForm.do";
    if(invId!=null) url=url+"?invId="+invId;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'ajaxContent');
        if(invId!=null){
            var conId=document.forms['invoiceForm'].conId;
            var url='invoiceMaterialInContracts.do?conId='+conId.value;
            callAjax(url,'listInvoiceMaterialDataSpan',null,null);
        }
        var total=document.forms['invoiceForm'].amount;
        var sumVAT=document.forms['invoiceForm'].sumVAT;
        var invoiceContractAmount=document.forms['invoiceForm'].invoiceContractAmount;
        var invoiceContractVAT=document.forms['invoiceForm'].invoiceContractVAT;
        var invoiceContractTotalAmount=document.forms['invoiceForm'].invoiceContractTotalAmount;
        var rates=document.forms['invoiceForm'].rates;
        invoiceContractTotalAmount.value=total.value*1;
        invoiceContractVAT.value=sumVAT.value*1;
        invoiceContractAmount.value=total.value*1 - sumVAT.value*1;
        var currency = document.forms['invoiceForm'].currency.value;
        if(total!=null) tryNumberFormat(total,currency);
        if(sumVAT!=null) tryNumberFormat(sumVAT,currency);
        if(rates!=null) tryNumberFormat(rates,currency);
        tryNumberFormat(invoiceContractAmount,currency);
        tryNumberFormat(invoiceContractVAT,currency);
        tryNumberFormat(invoiceContractTotalAmount,currency);
        tryNumberFormat(document.forms['invoiceForm'].otherAmount,currency);
        tryNumberFormat(document.forms['invoiceForm'].transportAmount,currency);
        total=null;
        sumVAT=null;
        rates=null;
        invoiceContractAmount=null;
        invoiceContractVAT=null;
        invoiceContractTotalAmount=null;
        
        var detTotal=document.forms['invoiceForm'].detTotal;
        var vat=document.forms['invoiceForm'].vat;
        var price=document.forms['invoiceForm'].price;
        var quantity=document.forms['invoiceForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(vat[i],currency);
                    tryNumberFormat(price[i],currency);
                    if(detTotal!=null) tryNumberFormat(detTotal[i],currency);
                    if(quantity!=null) tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat,currency);
                tryNumberFormat(price,currency);
                if(detTotal!=null) tryNumberFormat(detTotal,currency);
                if(quantity!=null) tryNumberFormat(quantity);
            }
        }
        vat=null;
        price=null;
        quantity=null;
        detTotal=null;
    });
    return false;
}
function invoiceContractChanged(list){
    removeInvoiceContractMaterial();
    var url='';
    if(list!=null && list.selectedIndex!=-1) list=list.options[list.selectedIndex].value;
    else list='0';
    callAjax('invoiceMaterialInContracts.do?conId='+list,null,null,function(data){
        setAjaxData(data,'listInvoiceMaterialDataSpan');
        var choose=document.forms['invoiceForm'].chooseAll;
        choose.checked=true;
        choose=null;
        setSelectedInvoiceMaterial();
    });
    url='invoiceContractDetail.do?conId='+list;
    callAjax(url,null,null,function(data){
        var obj=eval('('+data+')');
        document.forms['invoiceForm'].currency.value=obj.currency;
        document.forms['invoiceForm'].rates.value=obj.rates;
        document.forms['invoiceForm'].otherAmount.value=obj.otherAmount;
        document.forms['invoiceForm'].transportAmount.value=obj.transportAmount;
//        document.forms['invoiceForm'].invoiceContractTotalAmount.value=reformatNumberMoney(obj.totalAmount);
//        document.forms['invoiceForm'].invoiceContractAmount.value=obj.amount;
//        document.forms['invoiceForm'].invoiceContractVAT.value=obj.VAT;
//        document.forms['invoiceForm'].invoiceContractTotalAmount.value=obj.totalAmount;
    });
    return false;
}
function removeInvoiceContractMaterial(){

    var selId=document.forms['invoiceForm'].material;
    if(selId==null) return false;
    var tbl=document.getElementById('invoiceDetailTable');
    var parentNode;
    if(selId.length!=null){
        for (i=selId.length-1;i>=1;i--) {
            parentNode=selId[i].parentNode;
            selId[i].value=0;
            parentNode.removeChild(selId[i]);
            selId[i]=null;
            parentNode=parentNode.parentNode;
            parentNode=parentNode.parentNode;
        //            tbl.deleteRow(parentNode.rowIndex);
        }
    }else{
        parentNode=selId.parentNode;
        selId.value=0;
        if(parentNode!=null) parentNode.removeChild(selId);
    //        tbl.deleteRow(0);
    }
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
    selId=null;
    return false;
}
function delInvoiceDetails(){
    var checkflag = false;
    var detId = document.forms['invoiceForm'].detId;
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
        if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delInvoiceDetail.do',null,document.forms['invoiceForm'],function(data){
            if(data=="deleted"){
                var tbl=document.getElementById('invoiceDetailTable');
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
                caculateInvoice();
            }else{
                alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
            }
            detId=null;
        });
    }else detId=null;
    return false;
}
function setSelectedInvoiceMaterial(){
    var choose=document.forms['invoiceForm'].chooseAll;
    var list=document.forms['invoiceForm'].material;
    var contract=document.forms['invoiceForm'].conId;
    var conId='';
    if(contract.selectedIndex!=null){
        if(contract.selectedIndex==-1 || list.selectedIndex==-1) return false;
        conId=contract.options[contract.selectedIndex].value;
    }else{
        conId=contract.value;
    }
    var matId = document.forms['invoiceForm'].matId;
    var existed=false;
    var matIds='0';
    var conIds='0';
    if(matId!=null){
        if(choose.checked==false){
            var materialId=list.options[list.selectedIndex].value;
            matIds+=','+materialId;
            conIds+=','+conId;
            if (matId.length!=null) {
                for (i = 0; i < matId.length; i++) {
                    if (matId[i].value == materialId) {
                        existed = true;
                        break;
                    }
                }
            } else if(matId.value==materialId) existed=true;
        }else{
            if (matId.length!=null) {
                for (k=0;k<list.length;k++){
                    for (i = 0; i < matId.length; i++) {
                        if (matId[i].value == list.options[k].value) {
                            break;
                        }
                    }
                    if(i==matId.length){
                        matIds+=','+list.options[k].value;
                        conIds+=','+conId;
                    }
                }
            } else {
                for (k=0;k<list.length;k++){
                    if (matId.value != list.options[k].value) {
                        matIds+=','+list.options[k].value;
                        conIds+=','+conId;
                    }
                }
            }
        }
    }else{
        if(choose.checked==false){
            matIds+=','+list.options[list.selectedIndex].value;
            conIds+=','+conId;
        }else{
            for (k=0;k<list.length;k++){
                matIds+=','+list.options[k].value;
                conIds+=','+conId;
            }
        }
    }
    if(existed==true){
        alert('V\u1EADt t\u01B0 \u0111\u00E3 t\u1ED3n t\u1EA1i');
        return false;
    }
    
    var url='invoiceContractMaterialRemain.do?conId='+ conIds + '&matId='+matIds;
    callAjax(url,null,null,function(data){
        setAjaxData(data,'invoiceMaterialHideDiv');
        var matTable=document.getElementById('orderSourceMaterialTable');
        var detTable=document.getElementById('invoiceDetailTable');
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
        
        var detTotal=document.forms['invoiceForm'].detTotal;
        var vat=document.forms['invoiceForm'].vat;
        var price=document.forms['invoiceForm'].price;
        var quantity=document.forms['invoiceForm'].quantity;
        if(price!=null){
            if (price.length!=null) {
                for (i = 0; i < detTotal.length; i++) {
                    tryNumberFormat(vat[i]);
                    tryNumberFormat(price[i]);
                    if(detTotal!=null) tryNumberFormat(detTotal[i]);
                    if(quantity!=null) tryNumberFormat(quantity[i]);
                }
            } else{
                tryNumberFormat(vat);
                tryNumberFormat(price);
                if(detTotal!=null) tryNumberFormat(detTotal);
                if(quantity!=null) tryNumberFormat(quantity);
            }

        }
        vat=null;
        price=null;
        quantity=null;
        detTotal=null;
        caculateInvoice();
    });
    matId=null;
    return false;
}
function caculateInvoice(){
    var total=document.forms['invoiceForm'].amount;
    if(total==null) return;
//    var otherAmount=parseFloat(reformatNumberMoneyString(document.forms['invoiceForm'].otherAmount.value));
    var otherAmount=document.forms['invoiceForm'].otherAmount;
    var transportAmount=document.forms['invoiceForm'].transportAmount;
    var totalVAT=document.forms['invoiceForm'].sumVAT;
    var detTotal=document.forms['invoiceForm'].detTotal;
    var vat=document.forms['invoiceForm'].vat;
    var quantity=document.forms['invoiceForm'].quantity;
    var price=document.forms['invoiceForm'].price;
    var rates=document.forms['invoiceForm'].rates;
    var sum=0;
    var sumVAT=0;
    if(detTotal!=null){
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                sum+=reformatNumberMoneyString(detTotal[i].value)*1;
                sumVAT+=reformatNumberMoneyString(quantity[i].value)*reformatNumberMoneyString(price[i].value)*reformatNumberMoneyString(vat[i].value)/100;
            }
        } else{
            sum+=reformatNumberMoneyString(detTotal.value)*1;
            sumVAT+=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*reformatNumberMoneyString(vat.value)/100;
        }
    }
    total.value=sum/1 + reformatNumberMoneyString(otherAmount.value)/1 + reformatNumberMoneyString(transportAmount.value)/1;
    totalVAT.value=sumVAT;
//    var invoiceContractAmount=document.forms['invoiceForm'].invoiceContractAmount;
//    var invoiceContractVAT=document.forms['invoiceForm'].invoiceContractVAT;
//    var invoiceContractTotalAmount=document.forms['invoiceForm'].invoiceContractTotalAmount;
//    invoiceContractTotalAmount.value=total.value*1;
//    invoiceContractVAT.value=sumVAT*1;
//    invoiceContractAmount.value=sum + otherAmount - sumVAT;);
    tryNumberFormat(total);
    tryNumberFormat(totalVAT);
    tryNumberFormat(otherAmount);
    tryNumberFormat(transportAmount);
    tryNumberFormat(rates);
//    tryNumberFormat(invoiceContractAmount);
//    tryNumberFormat(invoiceContractVAT);
//    tryNumberFormat(invoiceContractTotalAmount);
    total=null;
    totalVAT=null;
    detTotal=null;
    otherAmount=null;
    transportAmount=null;
    rates=null;
//    invoiceContractAmount=null;
//    invoiceContractVAT=null;
//    invoiceContractTotalAmount=null;
    return false;
}
function caculateInvoiceDetail(matId){
    var quantity=document.getElementById("detquantity"+matId);
    var price=document.getElementById("detprice"+matId);
    var vat=document.getElementById("detvat"+matId);
    var detTotal=document.getElementById("detTotal"+matId);
    if(quantity==null || price==null || vat==null || detTotal==null) return;
    detTotal.value=reformatNumberMoneyString(quantity.value)*reformatNumberMoneyString(price.value)*(100+vat.value*1)*0.01;
    tryNumberFormat(detTotal);
    quantity=null;
    price=null;
    vat=null;
    detTotal=null;
    caculateInvoice();
    return false;
}
function saveInvoice(){
    if(scriptFunction=="saveInvoice()") return false;
    var contractNumber = document.forms['invoiceForm'].invoice;
    if(contractNumber.value==''){
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 ho\u00E1 \u0111\u01A1n!");
        contractNumber.focus();
        contractNumber=null;
        return false;
    }
    contractNumber=null;
    var conId = document.forms['invoiceForm'].conId;
    if(conId.selectedIndex==-1){
        alert("Vui l\u00F2ng ch\u1ECDn h\u1EE3p \u0111\u1ED3ng");
        conId=null;
        return false;
    }
    conId=null;
    
    var total=document.forms['invoiceForm'].amount;
    var totalVAT=document.forms['invoiceForm'].sumVAT;
    var detTotal=document.forms['invoiceForm'].detTotal;
    var vat=document.forms['invoiceForm'].vat;
    var price=document.forms['invoiceForm'].price;
    var quantity=document.forms['invoiceForm'].quantity;
    var rates=document.forms['invoiceForm'].rates;
    var rates=document.forms['invoiceForm'].rates;
    var otherAmount=document.forms['invoiceForm'].otherAmount;
    var transportAmount=document.forms['invoiceForm'].transportAmount;
    if(quantity!=null){
        if (quantity.length!=null) {
            for (i = 0; i < quantity.length; i++) {
                reformatNumberMoney(vat[i]);
                reformatNumberMoney(price[i]);
                reformatNumberMoney(detTotal[i]);
                reformatNumberMoney(quantity[i]);
            }
        } else{
            reformatNumberMoney(vat);
            reformatNumberMoney(price);
            reformatNumberMoney(detTotal);
            reformatNumberMoney(quantity);
        }
    }
    if(total!=null) reformatNumberMoney(total);
    if(totalVAT!=null) reformatNumberMoney(totalVAT);
    if(rates!=null) reformatNumberMoney(rates);
    if(otherAmount!=null) reformatNumberMoney(otherAmount);
    if(transportAmount!=null) reformatNumberMoney(transportAmount);
    vat=null;
    price=null;
    quantity=null;
    total=null;
    rates=null;
    otherAmount=null;
    transportAmount=null;
    totalVAT=null;
    detTotal=null;

    //callAjaxCheckError("saveInvoice.do",null,document.forms['invoiceForm'],getInvoiceListData);
    var icId = document.forms['invoiceForm'].icId.value;
    scriptFunction="saveInvoice()";
    callAjaxCheckError("saveInvoice.do",null,document.forms['invoiceForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        invoiceForm(icId);
    });
    return false;
}
function delInvoices(){
    var checkflag = false;
//    var conId = document.forms['invoicesForm'].icId;
//    if(conId==null) return false;
//    if (conId.length!=null) {
//        for (i = 0; i < conId.length; i++) {
//            if (conId[i].checked == true) {
//                checkflag = true;
//                break;
//            }
//        }
//    } else checkflag = conId.checked;
    checkflag = true;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delInvoice.do',null,document.forms['invoiceForm'],function(data){
        var index=data.indexOf('error:');
        if(index==0) alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+data.substring(6));
        else loadInvoiceList();
    });
    conId=null;
    return false;
}
function searchAdvDeliveryRequestForm(){
    callAjax('searchAdvDeliveryRequestForm.do',null,null,showPopupForm);
    return false;

}
function searchAdvDeliveryRequest(){
    callAjax('searchAdvDeliveryRequest.do',null,document.forms['searchDeliveryRequestForm'],getSearchAdvDeliveryRequestData);
    hidePopupForm();
    return false;
}
function getSearchAdvDeliveryRequestData(data){
    setAjaxData(data,'deliveryRequestList');
}
function searchInvoice(){
    var checkflag = true;
    var form=document.forms['searchSimpleInvoiceForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callAjax("searchInvoice.do","invoiceList",form,null);
    form=null;
    return false;
}
function searchAdvInvoiceForm(){
    callAjax('searchAdvInvoiceForm.do',null,null,showPopupForm);
    return false;
}
function searchAdvInvoice(){
    callAjax('searchAdvInvoice.do',null,document.forms['searchInvoiceForm'],getSearchAdvInvoiceData);
    hidePopupForm();
    return false;
}
function getSearchAdvInvoiceData(data){
    setAjaxData(data,'invoiceList');
}

function loadRequestReportForm(){
    callAjax('requestReportForm.do','ajaxContent',null,null);
    return false;
}
function printRequestReport() {
    var reportProject=document.forms['mcReportForm'].mcReportProject;
    if(reportProject.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
        reportProject.focus();
        reportProject=null;
        return false;
    }
//    var reportRequest=document.forms['mcReportForm'].mcReportRequest;
//    if(reportRequest.selectedIndex==-1){
//        alert('Vui l\u00F2ng ch\u1ECDn phi\u1EBFu \u0111\u1EC1 xu\u1EA5t');
//        reportRequest.focus();
//        reportRequest=null;
//        return false;
//    }
//    var reportOrganization=document.forms['mcReportForm'].orgId;
    var url='requestReportPrint.do?reportProject='+reportProject.value;
    url+='&reportRequest='+encodeURIComponent(document.forms['mcReportForm'].mcReportRequest.value);
    url+='&mcReportContract='+encodeURIComponent(document.forms['mcReportForm'].mcReportContract.value);
    url+='&mcReportVendor='+encodeURIComponent(document.forms['mcReportForm'].mcReportVendor.value);
    url+='&deliveryFromDate='+document.forms['mcReportForm'].deliveryFromDate.value;
    url+='&deliveryEndDate='+document.forms['mcReportForm'].deliveryEndDate.value;
    url+='&contractFromDate='+document.forms['mcReportForm'].contractFromDate.value;
    url+='&contractEndDate='+document.forms['mcReportForm'].contractEndDate.value;
    url+='&mrirFromDate='+document.forms['mcReportForm'].mrirFromDate.value;
    url+='&mrirEndDate='+document.forms['mcReportForm'].mrirEndDate.value;
    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value;
    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value;
    url+='&deliveryDateAsContractFromDate='+document.forms['mcReportForm'].deliveryDateAsContractFromDate.value;
    url+='&deliveryDateAsContractToDate='+document.forms['mcReportForm'].deliveryDateAsContractToDate.value;
//    url+='&orgId='+reportOrganization.value;
//    url+='&rfmFromDate='+document.forms['mcReportForm'].rfmFromDate.value;
//    url+='&rfmEndDate='+document.forms['mcReportForm'].rfmEndDate.value;
//    url+='&mivFromDate='+document.forms['mcReportForm'].mivFromDate.value;
//    url+='&mivEndDate='+document.forms['mcReportForm'].mivEndDate.value;
    callServer(url);
    reportProject=null;
    reportRequest=null;
//    reportOrganization=null;
    return false;
}
function loadRequestTimeForm(){
    callAjax('requestTimeForm.do','ajaxContent',null,null);
    return false;
}
function printRequestTime() {
    var reportProject=document.forms['mcReportForm'].mcReportProject;
    if(reportProject.selectedIndex==-1){
//        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
//        reportProject.focus();
//        reportProject=null;
//        return false;
        reportProject=0;
    }else{
        reportProject=reportProject.value;
    }
    var reportRequest=document.forms['mcReportForm'].mcReportRequest.value;
//    if(reportRequest.selectedIndex==-1){
////        alert('Vui l\u00F2ng ch\u1ECDn phi\u1EBFu \u0111\u1EC1 xu\u1EA5t');
////        reportRequest.focus();
////        reportRequest=null;
////        return false;
//        reportRequest=0;
//    }else{
//        reportRequest=reportRequest.value;
//    }
    var url='requestTimeReportPrint.do?reportProject='+reportProject;
    url+='&reportRequest='+reportRequest;
    url+='&deliveryFromDate='+document.forms['mcReportForm'].deliveryFromDate.value;
    url+='&deliveryEndDate='+document.forms['mcReportForm'].deliveryEndDate.value;
//    url+='&contractFromDate='+document.forms['mcReportForm'].contractFromDate.value;
//    url+='&contractEndDate='+document.forms['mcReportForm'].contractEndDate.value;
//    url+='&mrirFromDate='+document.forms['mcReportForm'].mrirFromDate.value;
//    url+='&mrirEndDate='+document.forms['mcReportForm'].mrirEndDate.value;
//    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value;
//    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value;
//    url+='&rfmFromDate='+document.forms['mcReportForm'].rfmFromDate.value;
//    url+='&rfmEndDate='+document.forms['mcReportForm'].rfmEndDate.value;
//    url+='&mivFromDate='+document.forms['mcReportForm'].mivFromDate.value;
//    url+='&mivEndDate='+document.forms['mcReportForm'].mivEndDate.value;
    callServer(url);
    reportProject=null;
    reportRequest=null;
    return false;
}
function whichUseSearchAdvReqChanged(list){
    if(list.selectedIndex==-1) return false;
    var reqId=null;
    if(document.forms['searchRequestForm']!=null) reqId=document.forms['searchRequestForm'].reqId;
    getWhichUse(null,list.options[list.selectedIndex].value,"addFirst=1");
    list=null;
    reqId=null;
    return false;
}

function delPaymentBills(){
    var checkflag = false;
    var detId = document.forms['paymentForm'].detId;
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else checkflag = detId.checked;
    detId=null;
    if (checkflag == true) if(confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) callAjaxCheckError('delPaymentDetail.do',null,document.forms['paymentForm'],function(data){
        if(data=="deleted"){
//            var tbl=document.getElementById('paymentBillTable');
//            var parentNode;
//            if(detId.length!=null){
//                for (i=detId.length-1;i>=0;i--) {
//                    if(detId[i].checked==true){
//                        parentNode=detId[i].parentNode;
//                        parentNode=parentNode.parentNode;
//                        parentNode=parentNode.parentNode;
//                        tbl.deleteRow(parentNode.rowIndex);
//                    }
//                }
//            }else if(detId.checked==true) tbl.deleteRow(1);
//            parentNode=null;
//            tbl=null;
            var payId = document.forms['paymentForm'].payId;
            if(payId!=null) payId=payId.value;
            paymentForm(payId);
        }else{
            alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : "+ data);
        }
    });
    return false;
}
function printReportContractForm(kind,title){
    popupName=title;
    callAjax('searchAdvContractForm.do?kind='+kind+'&isprint=1',null,null,showPopupForm);
    return false;
}

function vendorToTenderPlan(title){
    popupName=title;
    callAjax('vendorListToTenderPlan.do',null,null,function(data){
        showPopupForm(data);
//        var list=document.forms['tenderPlanForm'].cbxMaterialSource;
//        document.getElementById('materialSource').value=list.options[list.selectedIndex].value;
//        list=null;
//        callAjax('materialNotInContracts.do?reload=0','tenderPlanMaterialList',null,null);
        var PVenId=document.forms['tenderPlanForm'].vendor;
        var ids='';
        if(PVenId!=null){
            if(PVenId.length!=null){
                for (i = 0; i < PVenId.length; i++) {
                    ids+=','+PVenId[i].value;
                }
            }else ids+=','+PVenId.value;
        }
        document.getElementById('parentFormVendor').value=ids;
        searchVendorTenderPlan();
    });
    return false;
}

function searchVendorTenderPlan(params){
    url="searchVendorTenderPlan.do?"+params;
    callAjax(url,null,document.forms['selectVendorTenderPlanForm'],function(data){
        setAjaxData(data,'tenderplan_vendorList');
        var venId = document.forms['tenderplanvendorForm'].venId;
        if(venId==null) return false;
        var ids='0'+document.getElementById('parentFormVendor').value;
        var selId=document.forms['vendorTenderPlanSelectedForm'].vendorSelectedChk;
        if(selId!=null){
            if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
            }else ids+=','+selId.value;
            selId=null;
        }ids+=',0';
        if (venId.length!=null){
            for (i = 0; i < venId.length; i++) {
                if (ids.indexOf(','+venId[i].value+',')>-1){
                    venId[i].disabled=true;
                    venId[i].checked=true;
                }
            }
            venId=null;
        }else if (ids.indexOf(','+venId.value+',')>-1){
            venId.disabled=true;
            venId.checked=true;
        }
        venId=null;
    });
    form=null;
    return false;
}

function searchVendorTenderPlanSort(params){
    url="searchVendorTenderPlan.do?"+params;
    callAjax(url,null,document.forms['selectVendorTenderPlanForm'],function(data){
        setAjaxData(data,'tenderplan_vendorList');
        var venId = document.forms['tenderplanvendorForm'].venId;
        if(venId==null) return false;
        var ids='0'+document.getElementById('parentFormVendor').value;
        var selId=document.forms['vendorTenderPlanSelectedForm'].vendorSelectedChk;
        if(selId!=null){
            if(selId.length!=null){
            for (i = 0; i < selId.length; i++) {
                ids+=','+selId[i].value;
            }
            }else ids+=','+selId.value;
            selId=null;
        }ids+=',0';
        if (venId.length!=null){
            for (i = 0; i < venId.length; i++) {
                if (ids.indexOf(','+venId[i].value+',')>-1){
                    venId[i].disabled=true;
                    venId[i].checked=true;
                }
            }
            venId=null;
        }else if (ids.indexOf(','+venId.value+',')>-1){
            venId.disabled=true;
            venId.checked=true;
        }
        venId=null;
    });
    form=null;
    return false;
}
function setVendorTenderPlanSelected(){
    var venId= document.forms['tenderplanvendorForm'].venId;
    if(venId==null) return false;
    var vendorNameHidden = document.forms['tenderplanvendorForm'].vendorNameHidden;
    var vendorCharterCapitalHidden = document.forms['tenderplanvendorForm'].vendorCharterCapitalHidden;
    var vendorPresenterHidden = document.forms['tenderplanvendorForm'].vendorPresenterHidden;
    var tbl=document.getElementById('vendorTenderPlanSelectedTbl');
    var lastRow = tbl.rows.length;
    if (venId.length!=null) {
        for (i = 0; i < venId.length; i++) {
            if (venId[i].checked == true && venId[i].disabled==false) {
                venId[i].disabled=true;
                var row=document.createElement("tr");
                tbl.tBodies[0].appendChild(row);
                if(lastRow%2) row.className="odd"
                else row.className="even";
                var cell = row.insertCell(0);
                var el = document.createElement('input');
                el.type = 'checkbox';
                el.name = 'vendorSelectedChk';
                el.id="vendorSelectedChk";
                el.value=venId[i].value;
                cell.appendChild(el);

                cell = row.insertCell(1);
                var textNode = document.createTextNode(vendorNameHidden[i].value);
                cell.appendChild(textNode);
                
                cell = row.insertCell(2);
                textNode = document.createTextNode(vendorCharterCapitalHidden[i].value);
                cell.appendChild(textNode);
                
                cell = row.insertCell(3);
                textNode = document.createTextNode(vendorPresenterHidden[i].value);
                cell.appendChild(textNode);
                
                row=null;
                cell=null;
                el=null;
                lastRow+=1;
            }
        }
    }else{
        if (venId.checked == true && venId.disabled==false) {
            venId.disabled=true;
            var row=document.createElement("tr");
            tbl.tBodies[0].appendChild(row);
            if(lastRow%2) row.className="odd"
            else row.className="even";
            var cell = row.insertCell(0);
            var el = document.createElement('input');
            el.type = 'checkbox';
            el.name = 'vendorSelectedChk';

            el.id="vendorSelectedChk";
            el.value=venId.value;
            cell.appendChild(el);
        
        
            cell = row.insertCell(1);
            var textNode = document.createTextNode(vendorNameHidden.value);
            cell.appendChild(textNode);
                
            cell = row.insertCell(2);
            textNode = document.createTextNode(vendorCharterCapitalHidden.value);
            cell.appendChild(textNode);
                
            cell = row.insertCell(3);
            textNode = document.createTextNode(vendorPresenterHidden.value);
            cell.appendChild(textNode);
                
            row=null;
            cell=null;
            el=null;
            lastRow+=1;
        }
    }
    venId=null;
    vendorNameHidden=null;
    vendorCharterCapitalHidden=null;
    vendorPresenterHidden=null;
    tbl=null;
}

function delVendorTenderPlan(){
    var selId=document.getElementsByName('vendorSelectedChk');
    if(selId==null) return false;
    var ids='0';
    var tbl=document.getElementById('vendorTenderPlanSelectedTbl');
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
    var venId = document.forms['tenderplanvendorForm'].venId;
    if(venId==null) return false;
    if (venId.length!=null){
        for (i = 0; i < venId.length; i++) {
            if (ids.indexOf(','+venId[i].value+',')>-1){
                venId[i].disabled=false;
                venId[i].checked=false;
            }
        }
        venId=null;
    }else if (ids.indexOf(','+venId.value+',')>-1){
        venId.disabled=false;
        venId.checked=false;
    }
    venId=null;
}

function chooseVendorTenderPlanSelected(){
    var selId=document.forms['vendorTenderPlanSelectedForm'].vendorSelectedChk;
    if(selId==null){
        hidePopupForm();
        return false;
    }
    var existedId=document.forms['tenderPlanForm'].vendor;
    var existedStr="0";
    var rowNum="0";
    if(existedId!=null){
        rowNum="1";
        if(existedId.length!=null){
            for (i=0;i<existedId.length;i++) {
                existedStr+=","+existedId[i].value;
            }
            rowNum=""+existedId.length;
        }else if(selId==existedId.value){
            existedStr+=","+existedId.value;
        }
    }
    existedStr+=",0";
    existedId=null;
    
    var ids='0';
    if(selId.length!=null){
        for (i=selId.length-1;i>=0;i--) {
            if(existedStr.indexOf(","+selId[i].value+",")==-1){
                ids+=','+selId[i].value;
            }
        }
    }else{
        if(existedStr.indexOf(","+selId.value+",")==-1){
            ids+=','+selId.value;
        }
    }
    selId=null;
    if(ids!='0'){
        ids=ids.substring(2);
    }
    callAjax("getVendorForTenderPlan.do?venIds="+ids+"&rowNum="+rowNum,null,null,function(data){
        setAjaxData(data,'tenderPlanVendorHideDiv');
        var venTable=document.getElementById('tenderPlanVendorDetailTbl');
        var detTable=document.getElementById('tenderPlanVendorTbl');
        if(venTable.tBodies[0]==null || detTable.tBodies[0]==null){
            venTable=null;
            detTable=null;
            return;
        }
        for( var i=venTable.tBodies[0].rows.length-1;i>=0;i--) {
            detTable.tBodies[0].appendChild(venTable.tBodies[0].rows[i]);
        }
        venTable=null;
        detTable=null;
    });
    hidePopupForm();
}
function printIntermemoForm(){
    callAjax('printIntermemoForm.do',null,null,showPopupForm);
    return false;
}
function printIntermemo(reqId){
    if (reqId==null) {
        reqId=document.forms['intermemoForm'].reqId;
        if (reqId!=null) reqId = reqId.value;        
    }
    var list=document.forms['intermemoTemplateForm'].templateid;
    var templateId=list.options[list.selectedIndex].value;
    if(reqId!=null) callServer('intermemoPrint.do?reqId='+reqId+'&templateId='+templateId);
    reqId=null;
    return false;s
}
function showRequestReport() {
    var reportProject=document.forms['mcReportForm'].mcReportProject;
    if(reportProject.selectedIndex==-1){
        alert('Vui l\u00F2ng ch\u1ECDn d\u1EF1 \u00E1n');
        reportProject.focus();
        reportProject=null;
        return false;
    }
//    var reportRequest=document.forms['mcReportForm'].mcReportRequest;
//    if(reportRequest.selectedIndex==-1){
//        alert('Vui l\u00F2ng ch\u1ECDn phi\u1EBFu \u0111\u1EC1 xu\u1EA5t');
//        reportRequest.focus();
//        reportRequest=null;
//        return false;
//    }
//    var reportOrganization=document.forms['mcReportForm'].orgId;
    var url='requestReportShow.do?reportProject='+reportProject.value;
    url+='&reportRequest='+encodeURIComponent(document.forms['mcReportForm'].mcReportRequest.value);
    url+='&deliveryFromDate='+document.forms['mcReportForm'].deliveryFromDate.value;
    url+='&deliveryEndDate='+document.forms['mcReportForm'].deliveryEndDate.value;
    url+='&contractFromDate='+document.forms['mcReportForm'].contractFromDate.value;
    url+='&contractEndDate='+document.forms['mcReportForm'].contractEndDate.value;
    url+='&mrirFromDate='+document.forms['mcReportForm'].mrirFromDate.value;
    url+='&mrirEndDate='+document.forms['mcReportForm'].mrirEndDate.value;
    url+='&msvFromDate='+document.forms['mcReportForm'].msvFromDate.value;
    url+='&msvEndDate='+document.forms['mcReportForm'].msvEndDate.value;
//    url+='&orgId='+reportOrganization.value;
    callAjax(url,'ajaxContent',null,null);
    reportProject=null;
    reportRequest=null;
//    reportOrganization=null;
    return false;
}
function updateOtherDateValue(oldValue, sourceElement, number){
    if(oldValue=='') return;
    var _str=oldValue.split("/");
    var dateObj = new Date(_str[2],parseInt(_str[1]-1),_str[0]);
    dateObj.setDate(dateObj.getDate()+number);
    dateObj = formatDate(dateObj,'dd/MM/yyyy');
    sourceElement.value=dateObj;
}

function printPayment(payId){
    if (payId==null) {
        payId=document.forms['paymentForm'].payId;
        if (payId!=null) payId = payId.value;        
    }
    if(payId!=null) callServer('paymentPrint.do?payId='+payId);
    payId=null;
    return false;
}
function tenderPlanCreateContract_old(kind){
    var form=document.forms['bidEvalSumForm'];
    var besvId=form.besvId;
    var ids = '0';
    if (besvId.length!=null) {
        for (i = 0; i < besvId.length; i++) {
            if (besvId[i].checked == true){
                ids=besvId[i].value;
                break;
            }
        }
    } else if(besvId.checked == true){
        ids=besvId.value;
    }
    if (ids!='0'){
        var besvVenId=document.getElementById('besvVenId_'+ids).value;
        var besvTenId=document.getElementById('besvTenId_'+ids).value;
        var url="contractForm.do?kind="+kind+"&tenId="+document.forms['bidEvalSumForm'].tenId.value+"&isnotresell=1";
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            form=document.forms['contractForm'];
            var orderMaterialSource=form.orderMaterialSource;
            if(orderMaterialSource!=null){
                orderMaterialSource.value="tenderplan";
                orderMaterialSourceChanged(orderMaterialSource);
                orderMaterialSource.disabled="true";
                orderMaterialSource=null;
                callAjax('orderMaterialSource.do?kind=2',null,null,function(data){
                    setAjaxData(data,'orderSourceTr');
                    tenderPlanCreateContract_GetCombos(besvTenId, besvVenId);
                });
            }else{
                tenderPlanCreateContract_GetCombos(besvTenId, besvVenId);
            }
        });
    }else{
        alert('Vui l\u00F2ng ch\u1ECDn nh\u00E0 cung c\u1EA5p');
        return false;
    }
    besVenId=null;
    form=null;
    return false;
}
function tenderPlanCreateContract(kind){
    var form=document.forms['bidEvalSumForm'];
    var besvId=form.besvId;
    var matDetId=form.detId;
    var ids = '0';
    var detIds = '0';
    if (besvId.length!=null) {
        for (i = 0; i < besvId.length; i++) {
            if (besvId[i].checked == true){
                ids=besvId[i].value;
                break;
            }
        }
        if (matDetId.length!=null) {
        for (i = 0; i < matDetId.length; i++) {
            if (matDetId[i].checked == true){
                detIds += ',' + matDetId[i].value;
            }
        }
        } else if(matDetId.checked == true){
            detIds += ',' + matDetId.value;
        }           
    } else if(besvId.checked == true){
        ids=besvId.value;
        if (matDetId.length!=null) {
        for (i = 0; i < matDetId.length; i++) {
            if (matDetId[i].checked == true){
                detIds += ',' + matDetId[i].value;
            }
        }
        } else if(matDetId.checked == true){
            detIds += ',' + matDetId.value;
        }      
    }    
    if (ids!='0' && detIds !='0'){
        var besvVenId=document.getElementById('besvVenId_'+ids).value;
        var besvTenId=document.getElementById('besvTenId_'+ids).value;
        var url="contractForm.do?kind="+kind+"&tenId="+document.forms['bidEvalSumForm'].tenId.value+"&isnotresell=1&detIds="+detIds.substring(2);
        callAjax(url,null,null,function(data){
            setAjaxData(data,'ajaxContent');
            form=document.forms['contractForm'];
            var orderMaterialSource=form.orderMaterialSource;
            if(orderMaterialSource!=null){
                orderMaterialSource.value="tenderplan";
                orderMaterialSourceChanged(orderMaterialSource);
                orderMaterialSource.disabled="true";
                orderMaterialSource=null;
                callAjax('orderMaterialSource.do?kind=2',null,null,function(data){
                    setAjaxData(data,'orderSourceTr');
                    tenderPlanCreateContract_GetCombos(besvTenId, besvVenId);
                });
            }else{
                tenderPlanCreateContract_GetCombos(besvTenId, besvVenId);
            }
        });
    }else{
        alert('Vui l\u00F2ng ch\u1ECDn nh\u00E0 cung c\u1EA5p');
        return false;
    }
    besVenId=null;
    form=null 
    return false;
}
function tenderPlanCreateContract_GetCombos(besvTenId, besvVenId){
    var tenId=document.forms['contractForm'].tenId;
    if(tenId!=null){
        tenId.value=besvTenId;
        len = tenId.options.length;
        for (i=0;i<len;i++){
            if(tenId.options[i].value!=besvTenId){
                tenId.options[i]=null;
                i--;
                len--;
            }
        }
        callAjax("getVendorForContract.do?tenId="+tenId.options[tenId.selectedIndex].value,null,null,function(data){
            setAjaxData(data,'contractTenderPlanDiv');
            var venId=document.forms['contractForm'].venId;
            if(venId!=null){
                venId.value=besvVenId;
                contractVendorChanged(venId);
//                venId.disabled="true";
                len = venId.options.length;
                for (i=0;i<len;i++){
                    if(venId.options[i].value!=besvVenId){
                        venId.options[i]=null;
                        i--;
                        len--;
                    }
                }
                venId=null;
            }
        });
//        tenId.disabled="true";
        tenId=null;
    }
}
function printAppendixRequest(){
    var conId=document.forms['appendixForm'].conId;
    if(conId!=null) callServer('contractAppendixRequestPrint.do?conId='+conId.value);
    conId=null;
}
function printOrderRequest(kind){
    var conId=document.forms['contractForm'].conId;
    if(conId!=null) callServer('orderRequestPrint.do?conId='+conId.value+'&kind='+kind);
    conId=null;
}
function searchContractFromPayment(handle,title, url){
    if(handle==null) return false;
    popupName=title;
    callAjax('searchContractForm.do',null,null,function(data){
        showPopupForm(data);
        document.getElementById('callbackFunc').value=handle;
        document.getElementById('invoiceContractSearchUrl').value=url;
    });
    return false;
}
function searchInvoiceContract(){
    var url=document.getElementById('invoiceContractSearchUrl').value;
    if(url==null) return false;
    callAjax(url,null,document.forms['searchContractForm'],function(data){
        var handle=document.getElementById('callbackFunc').value;
        document.getElementById('invoiceContractData').value=data;
        if(handle!='') eval(handle+"()");
        handle=null;
        contractSearchedChanged(null);
    });
    hidePopupForm();
    return false;
}
function invoiceContractSearched(){
    setAjaxData(document.getElementById('invoiceContractData').value,'invoiceContractDiv');
}
function contractSearchedChanged(list){
    if(document.forms['invoiceForm']!=null){
        if(list==null) list=document.forms['invoiceForm'].conId;
        invoiceContractChanged(list);
    }else if(document.forms['paymentForm']!=null){
        if(list==null) list=document.forms['paymentForm'].conId;
        paymentContractChanged(list);
    }
}
function checkTenderplanMatValidQuantity(reqDetailId){
    var maxQuantity=document.getElementById('maxMatQuantity'+reqDetailId);
    var quantity=document.getElementById('matQuantity'+reqDetailId);
    if(quantity.value*1>maxQuantity.value*1){
        //phuongtunew
        alert('S\u1ED1 l\u01B0\u1EE3ng v\u01B0\u1EE3t qu\u00E1 gi\u1EDBi h\u1EA1n cho ph\u00E9p. Vui l\u00F2ng nh\u1EADp l\u1EA1i !');
        quantity.value=maxQuantity.value;
        quantity.focus();
    }
    quantity=null;
    maxQuantity=null;
    return;
}
function checkContractMatValidQuantity(reqDetailId){
    var maxQuantity=document.getElementById('maxMatQuantity'+reqDetailId);
    var quantity=document.getElementById('detquantity'+reqDetailId);
    if(quantity.value*1>maxQuantity.value*1){
        //phuongtunew
        alert('S\u1ED1 l\u01B0\u1EE3ng v\u01B0\u1EE3t qu\u00E1 gi\u1EDBi h\u1EA1n cho ph\u00E9p. Vui l\u00F2ng nh\u1EADp l\u1EA1i !');
        quantity.value=maxQuantity.value;
        quantity.focus();
    }
    quantity=null;
    maxQuantity=null;
    return;
}
function checkContractMatValidQuantity2(matId){
    var maxQuantity=document.getElementById('maxMatQuantity2'+matId);
    var quantity=document.getElementById('det2quantity'+matId);
    if(quantity.value*1>maxQuantity.value*1){
        //phuongtunew
        alert('S\u1ED1 l\u01B0\u1EE3ng v\u01B0\u1EE3t qu\u00E1 gi\u1EDBi h\u1EA1n cho ph\u00E9p. Vui l\u00F2ng nh\u1EADp l\u1EA1i !');
        quantity.value=maxQuantity.value;
        quantity.focus();
    }
    quantity=null;
    maxQuantity=null;
    return;
}
function saveContractCancelMaterial(){
    var quantity=document.forms['contractForm'].quantity;
    var price=document.forms['contractForm'].price;
    var vat=document.forms['contractForm'].vat;
    var detTotal=document.forms['contractForm'].detTotal;
    
    if(detTotal!=null){
        if (detTotal.length!=null) {
            for (i = 0; i < detTotal.length; i++) {
                reformatNumberMoney(quantity[i]);
                reformatNumberMoney(price[i]);
                reformatNumberMoney(vat[i]);
                reformatNumberMoney(detTotal[i]);
            }
        } else{
            reformatNumberMoney(quantity);
            reformatNumberMoney(price);
            reformatNumberMoney(vat);
            reformatNumberMoney(detTotal);
        }
    }
    quantity=null;
    price=null;
    vat=null;
    detTotal=null;
    var conId = document.forms['contractForm'].conId.value;
    var kind = document.forms['contractForm'].kind.value;
    callAjaxCheckError("saveContractCancelMaterial.do",null,document.forms['contractForm'],function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        contractForm(kind,conId);
    });
    return false;
}
function duplicateObject(form, name){
    var object = eval("document.forms['"+form+"']."+name);
    var string="";
    if(object!=null){
        if(object.length!=null){
            for(var i=0;i<object.length;i++){
                if(object[i].value!=""){
                    string=object[i].value;
                    break;
                }
            }
            for(var i=0;i<object.length;i++){
                object[i].value=string;
            }
        }
    }
    object=null;
    return false;
}
function duplicateRequestAssignedEmp(){
    var empId = document.forms['requestForm'].empId;
    var emp="";
    if(empId!=null){
        if(empId.length!=null){
            for(var i=0;i<empId.length;i++){
                if(empId[i].value!=""){
                    emp=empId[i].value;
                    break;
                }
            }
            for(var i=0;i<empId.length;i++){
                empId[i].value=emp;
            }
        }
    }
    empId=null;
    return false;
}
function materialInContract_CheckOnClick(form, reqDetId, matId, reqId, conId, venId, conDetId){
    var reqDetId_chk = document.getElementById("reqDetId_"+form+"_"+reqDetId);
    var reqDetId_hid = document.getElementById("reqDetId_"+form);
    var matId_hid = document.getElementById("matId_"+form);
    var reqId_hid = document.getElementById("reqId_"+form);
    var conDetId_hid = document.getElementById("conDetId_"+form);
    var conId_hid = null;
    var venId_hid = null;
    if(conId!=null && conId!=0 && conId!='0') conId_hid = document.getElementById("conId_"+form);
    if(venId!=null && venId!=0 && venId!='0') venId_hid = document.getElementById("venId_"+form);
    if(conDetId!=null && conDetId!=0 && conDetId!='0') conDetId_hid = document.getElementById("conDetId_"+form);
    if(reqDetId_chk.checked == true){
        if(reqDetId_hid.value==""){
            reqDetId_hid.value+="0,"+reqDetId+",";
            matId_hid.value+="0,"+matId+",";
            reqId_hid.value+="0,"+reqId+",";
//            conDetId_hid.value+="0,"+conDetId+",";
            if(conId!=null && conId!=0 && conId!='0') conId_hid.value+="0,"+conId+",";
            if(venId!=null && venId!=0 && venId!='0') venId_hid.value+="0,"+venId+",";
            if(conDetId!=null && conDetId!=0 && conDetId!='0') conDetId_hid.value+="0,"+conDetId+",";
        }else{
            if (reqDetId_hid.value.indexOf(","+reqDetId+",")==-1){
                reqDetId_hid.value+=reqDetId+",";
                matId_hid.value+=matId+",";
                reqId_hid.value+=reqId+",";
//                conDetId_hid.value+=conDetId+",";
                if(conId!=null && conId!=0 && conId!='0') conId_hid.value+=conId+",";
                if(venId!=null && venId!=0 && venId!='0') venId_hid.value+=venId+",";
                if(conDetId!=null && conDetId!=0 && conDetId!='0') conDetId_hid.value+=conDetId+",";
            }
        }
    }else{
        var reqDetId_val=reqDetId_hid.value;
        reqDetId_val = reqDetId_val.replace(","+reqDetId+",", ",");
        reqDetId_hid.value=reqDetId_val;
        var matId_val=matId_hid.value;
        matId_val = matId_val.replace(","+matId+",", ",");
        matId_hid.value=matId_val;
        var reqId_val=reqId_hid.value;
        reqId_val = reqId_val.replace(","+reqId+",", ",");
        reqId_hid.value=reqId_val;
        
//        var conDetId_val=conDetId_hid.value;
//        conDetId_val = conDetId_val.replace(","+conDetId+",", ",");
//        conDetId_hid.value=conDetId_val;
        if(conId!=null && conId!=0 && conId!='0'){
            var conId_val=conId_hid.value;
            conId_val = conId_val.replace(","+conId+",", ",");
            conId_hid.value=conId_val;
        }
        if(venId!=null && venId!=0 && venId!='0'){
            var venId_val=venId_hid.value;
            venId_val = venId_val.replace(","+venId+",", ",");
            venId_hid.value=venId_val;
        }
        if(conDetId!=null && conDetId!=0 && conDetId!='0'){
            var conDetId_val=conDetId_hid.value;
            conDetId_val = conDetId_val.replace(","+conDetId+",", ",");
            conDetId_hid.value=conDetId_val;
        }
    }
    reqDetId_chk=null;
    reqDetId_hid=null;
    matId_hid=null;
    reqId_hid=null;
    conId_hid=null;
    venId_hid=null;
    conDetId_hid=null;
}
function checkMaterialInContract(formName){
    var form=document.forms[formName];
    var matId = form.matId;
    if(matId==null) return false;
    var hid=eval('form.reqDetId_'+formName+'.value');
    var n = 'reqDetId_'+formName+'_';
    var len = n.length;
    if (matId.length!=null) {
        for (i = 0; i < matId.length; i++) {
            if (hid.indexOf(","+matId[i].id.substring(len)+",")>-1){
                matId[i].checked=true;
            }
        }
    } else{
        if (hid.indexOf(","+matId.id.substring(len)+",")>-1){
            matId.checked=true;
        }
    }
    form=null;
    matId=null;
    return false;
}
function duplicateDnDetail(name){
    var form=document.forms['dnForm']
    var obj = eval('form.'+name);
    var value="";
    if(obj!=null){
        if(obj.length!=null){
            for(var i=0;i<obj.length;i++){
                if(obj[i].value!=""){
                    value=obj[i].value;
                    break;
                }
            }
            for(var i=0;i<obj.length;i++){
                obj[i].value=value;
            }
        }
    }
    obj=null;
    return false;
}
function comboTextIdClick(form,name,id,nameTr,idTr){
    var text=eval('document.forms[form].'+name);
    if(text!=null) text.value="";
    text=eval('document.forms[form].'+id);
    if(text!=null) text.selectedIndex=-1;
    text=null;
    showhide2(idTr, false);
    showhide2(nameTr, true);
}
function comboTextNameClick(form,url,param,name,id,span,nameTr,idTr){
    var text=eval('document.forms[form].'+id);
    if(text!=null) text.selectedIndex=-1;
    text=eval('document.forms[form].'+name);
    if(text!=null){
        url=url+"?name="+text.value;
//        else{
//            text=eval('document.forms[form].'+id);
//            if(text!=null) text.value=0;
//        }
        if(param!=null) url+="&"+param;
        callAjax(url,null,null,function(data){
            setAjaxData(data,span);
            text=eval('document.forms[form].'+id);
            if(text!=null) text.selectedIndex=0;
        });
    }
    
    text=null;
    showhide2(idTr, true);
    showhide2(nameTr, false);
}
function comboTextNameClick_Handle(form,handle,param,name,id,span,nameTr,idTr){
    var text=eval('document.forms[form].'+id);
    if(text!=null) text.selectedIndex=-1;
    text=eval('document.forms[form].'+name);
    if(text!=null){
        handle(text.value,span);
    }
    text=null;
    showhide2(idTr, true);
    showhide2(nameTr, false);
}
function caculatePaymentDetail(rowId){
    caculatePayment();
    return false;
}
function cancelRequestRemainQuantity(reqDetId, callback){
    callAjaxCheckError("cancelRequestRemainQuantity.do?reqDetId="+reqDetId,null,null,function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        callback();
    });
    return false;
}
function rollbackMaterial(tenId) {
    callAjaxCheckError("rollbackMaterial.do?tenId="+tenId,null,null,function(data){
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
    });
    return false;
}
function changeMaterial(reqDetId,title){
    if(reqDetId==null || reqDetId==0) return false;
    popupName=title;
    callAjax('changeMaterialForm.do?reqDetId='+reqDetId,null,null,function(data){
        showPopupForm(data);
        searchMaterialRequestForChange(reqDetId);
    });
    return false;
}
function searchMaterialRequestForChange(reqDetId){
    var form=document.forms['selectMaterialForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    callAjax("searchMaterialRequestForChange.do?reqDetId="+reqDetId,'materialChangeRequestList',form,null);
    form=null;
    return false;
}
function saveMaterialChanged(detId) {
    var matId = document.forms['materialChangeRequestForm'].newMatIdForChange;
    var newMat=0;
    if(matId!=null){
        if(matId.length!=null){
            for(var i=0;i<matId.length;i++){
                if(matId[i].checked==true){
                    newMat=matId[i].value;
                    break;
                }
            }
        }else{
         if(matId.checked==true){
                newMat=matId.value;
            }   
        }
    }
    if(newMat==0){
        alert("Vui l\u00F2ng ch\u1ECDn v\u1EADt t\u01B0 c\u1EA7n thay \u0111\u1ED5i");
        return false;
    }
    matId=null;
    var reqId = document.forms['materialChangeRequestForm'].reqId.value;
    callAjaxCheckError("changeRequestMaterial.do?detId="+detId+"&matId="+newMat,null,null,function(data){
        requestForm(reqId);
        hidePopupForm();
    });
    return false;
}

function emailForNotMaterialCodeBidEvalSum(){
    var besId = document.forms['bidEvalSumForm'].besId;
    var tenId = document.forms['bidEvalSumForm'].tenId;
    if(besId==null) return false;
    else besId=besId.value;
    if(tenId==null) return false;
    else tenId=tenId.value;
    callAjaxCheckError("emailNotCodeBidEvalSum.do?besId="+besId+"&tenId="+tenId,null,null,function(data){
        alert("\u0110\u00E3 g\u1EDFi email!");
        bidEvalSumForm(tenId);
    });
    return false;
}

function emailForNotMaterialCodeContract(){
    var conId = document.forms['contractForm'].conId;
    var kind = document.forms['contractForm'].kind;
    if(conId==null) return false;
    else conId=conId.value;
    if(kind==null) return false;
    else kind=kind.value;
    callAjaxCheckError("emailNotCodeContract.do?conId="+conId,null,null,function(data){
        alert("\u0110\u00E3 g\u1EDFi email!");
        contractForm(kind,conId);
    });
    return false;
}
function emailForNotMaterialCodeAdjustment(){
    var conId = document.forms['adjustmentForm'].conId;
    var kind = document.forms['adjustmentForm'].kind;
    if(conId==null) return false;
    else conId=conId.value;
    if(kind==null) return false;
    else kind=kind.value;
    callAjaxCheckError("emailNotCodeContract.do?conId="+conId,null,null,function(data){
        alert("\u0110\u00E3 g\u1EDFi email!");
        var conId=document.forms['adjustmentForm'].conId.value;
        var kind=document.forms['adjustmentForm'].kind.value;
        var venId=document.forms['adjustmentForm'].venId.value;
        adjustmentForm(kind,conId,venId);
    });
    return false;
}
function emailForNotMaterialCodeOsD(){
    var osdId = document.forms['osDForm'].osdId.value;
    callAjaxCheckError("emailNotCodeOsD.do?osdId="+osdId,null,null,function(data){
        alert("\u0110\u00E3 g\u1EDFi email!");
        loadOsD(getMrirId());
    });
    return false;
}
function copyRequest(){
    var detId = document.forms['requestForm'].detId;
    var reqId=document.forms['requestForm'].reqId.value;
    var detIds="0";
    if(detId==null) return false;
    if (detId.length!=null) {
        for (i = 0; i < detId.length; i++) {
            if (detId[i].checked == true) {
                detIds+=","+detId[i].value;
            }
        }
    } else if(detId.checked==true) detIds+=","+detId.value;;
    if (detIds!="0"){ 
//        callAjaxCheckError('copyRequest.do',null,document.forms['requestForm'],function(data){
//        var obj=eval('('+data+')');
//        var reqId=obj.reqId;
//        requestForm(reqId);
//    });
        callAjax('copyRequest.do?reqId='+reqId,null,null,function(data){
            setAjaxData(data,"ajaxContent");
            var list=document.forms['requestForm'].whichUse;
            if(list!=null && list.selectedIndex>-1){
                getWhichUse(reqId,list.options[list.selectedIndex].value);
                list=null;
            }
            var url='listRequestDetail.do?detIds='+detIds;
            if(document.forms['requestForm'].isAssignEmp.value>0) url=url+"&assignEmp=1";
            callAjax(url,null,null,function(data){
                setAjaxData(data,'listRequestMaterialDataDiv');
                var presentQuantity=document.forms['requestForm'].presentQuantity;
                var requestQuantity=document.forms['requestForm'].requestQuantity;

                if(requestQuantity!=null){
                    if (requestQuantity.length!=null) {
                        for (i = 0; i < requestQuantity.length; i++) {
                            tryNumberFormat(requestQuantity[i],"USD");
                            tryNumberFormat(presentQuantity[i],"USD");
                        }
                    } else{
                        tryNumberFormat(requestQuantity,"USC");
                        tryNumberFormat(presentQuantity,"USD");
                    }
                }

                requestQuantity=null;
                presentQuantity=null;
            });
            if(reqId!=null) {
                loadAttachFileSystem(1,reqId);
            }
        });
    }else alert("Vui l\u00F2ng ch\u1ECDn v\u1EADt t\u01B0 c\u1EA7n copy");
    detId=null;
    return false;
}
function paymentReportPrintForm(){
    callAjax('paymentReportForm.do','ajaxContent',null,null);
    return false;
}
function printPaymentReport() {
    var url='paymentReportPrint.do?t=1';
    url+='&paymentFromDate='+document.forms['paymentReportForm'].createdFromDate.value;
    url+='&paymentEndDate='+document.forms['paymentReportForm'].createdEndDate.value;
    callServer(url);
    return false;
}
function updateContractStatusValue(status, conId, kind){
    if(scriptFunction=="updateContractStatusValue()") return false;
    scriptFunction="updateContractStatusValue()";
    callAjaxCheckError("updateContractStatus.do?conId="+conId+"&status="+status,null,document.forms['contractForm'],function(data){
        scriptFunction="";
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        contractForm(kind,conId);
    });
    return false;
}
function updateContractStatus(form){
    var form = eval("document.forms['"+form+"']");
    var status=1;
    var list=form.paymentStatus;
    if(list!=null && list.selectedIndex>-1){
        status=list.options[list.selectedIndex].value;
        list=null;
    }
    updateContractStatusValue(status, form.conId.value, form.kind.value);
    form=null;
    return false;
}
function printSearchedContract(kind){
    var checkflag = true;
    var form=document.forms['searchSimpleDeliveryRequestForm'];
    if (form.searchid.value!=0) {
        if (form.searchvalue.value=="") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    }else form.searchvalue.value="";
    if (checkflag == true) callServer("deliveryRequestReportPrint.do?kind="+kind+"&searchValue="+form.searchvalue.value+"&searchId="+form.searchvalue.searchid);
    form=null;
    return false;
}