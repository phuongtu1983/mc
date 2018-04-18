function loadStoreFromXml() {
    window.location = 'storeList.do';
}
function loadHomePage() {
    callAjax('homePage.do', 'ajaxContent', null, null);
    return false;
}
function loadVendorList(params) {
    callAjaxEx('vendorList.do', 'ajaxContent', 'vendors.do', 'vendorList', params);
    return false;
}
function loadVendorListSort(params) {
    callAjaxEx('searchVendor.do', 'vendorList', null, null, params);
    return false;
}
function getVendorListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadVendors();
    return false;
}
function loadVendors() {
    return callAjax("vendors.do", "vendorList", null, null);
//return false;
}
function vendorForm(venId) {
    if (venId != null) {
        getVendorTabs(function() {
            loadVendor(venId);
        });
    } else
        callAjax('vendorForm.do', 'ajaxContent', null, null);
    return false;
}
function getVendorTabs(handle) {
    callAjax('vendorTabs.do', null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        addVendorTabs();
        handle();
    });
}
function loadVendor(venId) {
    callAjax('vendorForm.do?venId=' + venId, null, null, function(data) {
        setAjaxData(data, 'vendoredetail');
        showhide2('vendorFormTitle', true);
        setVendorId(venId);
        loadAttachFileSystem(14, venId, 'divVendorAttachFileSystem');
    });
}
function saveVendor() {
    var name = document.forms['vendorForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn nh\u00E0 cung c\u1EA5p");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("saveVendor.do", null, document.forms['vendorForm'], getVendorListData, 'vendorFormError');
    return false;
}
function saveVendorContract() {
    var name = document.forms['vendorForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn nh\u00E0 cung c\u1EA5p");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n Thay \u0111\u1ED5i \u0111i\u1EC1u ch\u1EC9nh H\u0110 ?'))
        callAjaxCheckError("saveVendor.do?contract=1", null, document.forms['vendorForm'], getVendorListData, 'vendorFormError');
    return false;
}
function searchVendor() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchVendor.do", "vendorList", document.forms[0], null);
    }
    return false;
}
function exportVendor() {
    var form = document.forms['searchSimpleVendorContactForm'];
    var url = "vendorReportPrint.do?searchid=" + form.searchid.value + "&searchvalue=" + encodeURIComponent(form.searchvalue.value);
    form = null;
    callServer(url);
    return false;
}
function delVendors() {
    var checkflag = false;
    var venId = document.forms['vendorsForm'].venId;
    if (venId == null)
        return false;
    if (venId.length != null) {
        for (i = 0; i < venId.length; i++) {
            if (venId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else
        checkflag = venId.checked;
    if (checkflag == true)
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delVendor.do', null, document.forms['vendorsForm'], function(data) {
                var index = data.indexOf('error:');
                if (index == 0)
                    alert("Vui l\u00F2ng xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y tr\u01B0\u1EDBc : " + data.substring(6));
                else
                    setAjaxData(data, 'vendorList');
            });
    venId = null;
    return false;
}
function searchAdvVendorForm() {
    callAjax('searchAdvVendorForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvVendor() {
    callAjax('searchAdvVendor.do', null, document.forms['searchVendorForm'], getSearchAdvVendorData);
    hidePopupForm();
    return false;
}
function getSearchAdvVendorData(data) {
    setAjaxData(data, 'vendorList');
}
function addVendorTabs() {
    displayTabs("vendorTabs", "vendorTabChild", vendorTabHandle);
}
function vendorTabHandle(child) {
    if (child.isLoaded == 'true')
        return true;
    if (child.id == 'vendorevaluate')
        loadVendorEvalList();
    else if (child.id == 'vendormaterial')
        loadVendorMaterialList();
    else if (child.id == 'vendorcontact')
        loadVendorContactList();
    else if (child.id == 'vendoregroupmaterial')
        loadVendorGroupMaterialList();
    else if (child.id == 'vendoredetail') {
        var venId = getVendorId();
        if (venId != '')
            loadVendor(venId);
    }
    child.isLoaded = 'true';
    child = null;
}
function loadVendorEvalListFromXml(venId) {
    getVendorTabs(function() {
        setVendorId(venId);
        loadVendorEvalList();
        dijit.byId('vendorTabs').selectChild('vendorevaluate');
    });
}
function loadVendorEvalList() {
    callAjax('venEvalList.do', null, null, getVenEvalListData);
    return false;
}
function getVenEvalListData(data) {
    setAjaxData(data, 'vendorevaluate');
    loadVenEvals();
    return false;
}
function loadVenEvals(params) {
    var url = "venEvals.do";
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjaxEx(url, 'venEvalList', null, null, params);
    return false;
}
function venEvalForm(venEvalId) {
    var url = "venEvalForm.do?ajax=1";
    var venId = getVendorId();
    if (venId != '')
        url = url + "&venId=" + venId;
    if (venEvalId != null)
        url = url + "&venEvalId=" + venEvalId;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'vendorevaluate');
        url = "venEvalCris.do";
        if (venEvalId != null)
            url = url + "?venEvalId=" + venEvalId;
        callAjax(url, "venEvalDetailList", null, null);
        setVendorId(document.getElementById('venIdEval').value);
        if (venEvalId == null) {
            var currentTime = new Date();
            var date = currentTime.getDate();
            var month = currentTime.getMonth() + 1;
            if (date < 10)
                date = '0' + date;
            if (month < 10)
                month = '0' + month;
            document.getElementById('createdDateVenEval').value = date + '/' + month + '/' + currentTime.getFullYear();
        }
    });
    return false;
}
function venEvalFormFromXml(venEvalId) {
    getVendorTabs(function() {
        venEvalForm(venEvalId);
        dijit.byId('vendorevaluate').isLoaded = 'true';
        dijit.byId('vendorTabs').selectChild('vendorevaluate');
    });
}
function saveVenEval() {
    var evalNumber = document.forms['venEvalForm'].evalNumber;
    if (evalNumber.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp s\u1ED1 \u0111\u00E1nh gi\u00E1");
        evalNumber.focus();
        evalNumber = null;
        return false;
    }
    var fromDate = document.forms['venEvalForm'].fromDate;
    if (fromDate.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y b\u1EAFt \u0111\u1EA7u");
        fromDate.focus();
        fromDate = null;
        return false;
    }
    fromDate = null;
    var toDate = document.forms['venEvalForm'].toDate;
    if (toDate.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp ng\u00E0y k\u1EBFt th\u00FAc");
        toDate.focus();
        toDate = null;
        return false;
    }
    toDate = null;
    var url = "saveVenEval.do";
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjaxCheckError(url, null, document.forms['venEvalForm'], getVenEvalListData);
    return false;
}
function delVenEvals() {
    var checkflag = false;
    var evalid = document.forms['venEvalsForm'].venevalid;
    if (evalid == null)
        return false;
    if (evalid.length != null) {
        for (i = 0; i < evalid.length; i++) {
            if (evalid[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else
        checkflag = evalid.checked;
    if (checkflag == true)
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delVenEval.do', null, document.forms['venEvalsForm'], loadVenEvals);
    evalid = null;
    return false;
}
function searchVenEval() {
    var checkflag = true;
    var form = document.forms['searchSimpleVendorEvalForm'];
    if (form.searchid.value != 0) {
        if (form.searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else
        form.searchvalue.value = "";
    if (checkflag == true) {
        var url = "searchVenEval.do";
        var venId = getVendorId();
        if (venId != '')
            url = url + "?venId=" + venId;
        callAjax(url, "venEvalList", form, null);
    }
    form = null;
    return false;
}
function loadVendorContactListFromXml(venId) {
    getVendorTabs(function() {
        var vendor = document.getElementById('venIdHidden');
        if (vendor != null) {
            vendor.value = venId;
            vendor = null;
        }
        loadVendorContactList();
        dijit.byId('vendorTabs').selectChild('vendorcontact');
    });
}
function loadVendorContactList() {
    callAjax('venContactList.do', null, null, getVenContactListData);
    return false;
}
function getVenContactListData(data) {
    setAjaxData(data, 'vendorcontact');
    loadVenContacts();
    return false;
}
function loadVenContacts(params) {
    var url = "venContacts.do";
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjaxEx(url, 'venContactList', null, null, params);
    return false;
}
function venContactForm(venContactId) {
    var url = "venContactForm.do?ajax=1";
    var venId = getVendorId();
    if (venId != '')
        url = url + "&venId=" + venId;
    if (venContactId != null)
        url = url + "&venContactId=" + venContactId;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'vendorcontact');
        setVendorId(document.getElementById('venIdContact').value);
    });
    return false;
}
function loadVendorContactFromXml(venContactId) {
    getVendorTabs(function() {
        venContactForm(venContactId);
        dijit.byId('vendorcontact').isLoaded = 'true';
        dijit.byId('vendorTabs').selectChild('vendorcontact');
    });
}
function saveVenContact() {
    var name = document.forms['venContactForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn ng\u01B0\u1EDDi li\u00EAn h\u1EC7");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("saveVenContact.do", null, document.forms['venContactForm'], getVenContactListData, 'venContactFormError');
    return false;
}
function delVenContacts() {
    var checkflag = false;
    var contactid = document.forms['venContactsForm'].vencontactid;
    if (contactid == null)
        return false;
    if (contactid.length != null) {
        for (i = 0; i < contactid.length; i++) {
            if (contactid[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else
        checkflag = contactid.checked;
    if (checkflag == true)
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delVenContact.do', null, document.forms['venContactsForm'], loadVenContacts);
    contactid = null;
    return false;
}
function searchVenContact() {
    var checkflag = true;
    var form = document.forms['searchSimpleVendorContactForm'];
    if (form.searchid.value != 0) {
        if (form.searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else
        form.searchvalue.value = "";
    if (checkflag == true) {
        var url = "searchVenContact.do";
        var venId = getVendorId();
        if (venId != '')
            url = url + "?venId=" + venId;
        callAjax(url, "venContactList", form, null);
    }
    form = null;
    return false;
}
function searchAdvVenContactForm() {
    var url = 'searchAdvVenContactForm.do';
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjax(url, null, null, showPopupForm);
    return false;
}
function searchAdvVenContact() {
    callAjax('searchAdvVenContact.do', null, document.forms['searchVenContactForm'], getSearchAdvVenContactData);
    hidePopupForm();
    return false;
}
function getSearchAdvVenContactData(data) {
    setAjaxData(data, 'venContactList');
}
function loadVendorMaterialListFromXml(venId) {
    getVendorTabs(function() {
        var vendor = document.getElementById('venIdHidden');
        if (vendor != null) {
            vendor.value = venId;
            vendor = null;
        }
        loadVendorMaterialList();
        dijit.byId('vendorTabs').selectChild('vendormaterial');
    });
}
function loadVendorMaterialList() {
    callAjax('venMaterialList.do', null, null, getVenMaterialListData);
    return false;
}
function getVenMaterialListData(data) {
    setAjaxData(data, 'vendormaterial');
    loadVenMaterials();
    return false;
}
function loadVenMaterials(params) {
    var url = "venMaterials.do";
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjaxEx(url, 'venMaterialList', null, null, params);
    return false;
}
function venMaterialForm(venMaterialId) {
    var url = "venMaterialForm.do?ajax=1";
    var venId = getVendorId();
    if (venId != '')
        url = url + "&venId=" + venId;
    if (venMaterialId != null)
        url = url + "&venMaterialId=" + venMaterialId;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'vendormaterial');
        setVendorId(document.getElementById('venIdMaterial').value);
        if (venId != '') loadAttachFileSystem(15, venId, 'divVenMatAttachFileSystem');
    });
    return false;
}
function loadVendorMaterialFromXml(venMaterialId) {
    getVendorTabs(function() {
        venMaterialForm(venMaterialId);
        dijit.byId('vendormaterial').isLoaded = 'true';
        dijit.byId('vendorTabs').selectChild('vendormaterial');
    });
}
function saveVenMaterial() {
    var nameVn = document.forms['venMaterialForm'].nameVn;
    if (nameVn.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn v\u1EADt t\u01B0");
        nameVn.focus();
        nameVn = null;
        return false;
    }
    nameVn = null;
    var nameEn = document.forms['venMaterialForm'].nameEn;
    if (nameEn.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn v\u1EADt t\u01B0 (ti\u1EBFng Anh)");
        nameEn.focus();
        nameEn = null;
        return false;
    }
    nameEn = null;
    callAjaxCheckError("saveVenMaterial.do", null, document.forms['venMaterialForm'], getVenMaterialListData, 'venMaterialFormError');
    return false;
}
function delVenMaterials() {
    var checkflag = false;
    var materialid = document.forms['venMaterialsForm'].venmaterialid;
    if (materialid == null)
        return false;
    if (materialid.length != null) {
        for (i = 0; i < materialid.length; i++) {
            if (materialid[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else
        checkflag = materialid.checked;
    if (checkflag == true)
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delVenMaterial.do', null, document.forms['venMaterialsForm'], loadVenMaterials);
    materialid = null;
    return false;
}
function searchVenMaterial() {
    var checkflag = true;
    var form = document.forms['searchSimpleVendorMaterialForm'];
    if (form.searchid.value != 0) {
        if (form.searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else
        form.searchvalue.value = "";
    if (checkflag == true) {
        var url = "searchVenMaterial.do";
        var venId = getVendorId();
        if (venId != '')
            url = url + "?venId=" + venId;
        callAjax(url, "venMaterialList", form, null);
    }
    form = null;
    return false;
}
function getVendorId() {
    var ven = document.getElementById('venIdHidden');
    var venId = '';
    if (ven != null) {
        venId = ven.value;
        ven = null;
    }
    return venId;
}
function setVendorId(venId) {
    var vendor = document.getElementById('venIdHidden');
    if (vendor != null) {
        vendor.value = venId;
        vendor = null;
    }
}
function loadCriterionList() {
    callAjax('criterionList.do', null, null, getCriterionListData);
    return false;
}
function getCriterionListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadCriterions();
    return false;
}
function loadCriterions() {
    callAjax("criterions.do", "criterionList", null, null);
    return false;
}
function criterionForm(venCriterionId) {
    var url = "criterionForm.do";
    if (venCriterionId != null)
        url = url + "?criterionId=" + venCriterionId;
    callAjax(url, 'ajaxContent', null, null);
    return false;
}
function saveCriterion() {
    callAjaxCheckError("saveCriterion.do", null, document.forms['venCriForm'], loadCriterionList);
    return false;
}
function delCriterions() {
    var checkflag = false;
    var criterionId = document.forms['criterionsForm'].criterionId;
    if (criterionId == null)
        return false;
    if (criterionId.length != null) {
        for (i = 0; i < criterionId.length; i++) {
            if (criterionId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else
        checkflag = criterionId.checked;
    if (checkflag == true)
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delCriterion.do', 'criterionList', document.forms['criterionsForm'], null);
    criterionId = null;
    return false;
}
function loadVendorGroupMaterialList() {
    var url = "venGroupMaterialList.do";
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjax(url, null, null, getVenGroupMaterialListData);
    return false;
}
function getVenGroupMaterialListData(data) {
    setAjaxData(data, 'vendoregroupmaterial');
    setVendorId(document.getElementById('venIdGroupMaterial').value);
    loadVenGroupMaterials();
    return false;
}
function loadVenGroupMaterials(params) {
    var url = "venGroupMaterials.do";
    var venId = getVendorId();
    if (venId != '')
        url = url + "?venId=" + venId;
    callAjaxEx(url, 'venGroupMaterialList', null, null, params);
    return false;
}
function saveVenGroupMaterial() {
    var venId = getVendorId();
    var groupMaterialSel = document.getElementById('groupMaterialSel');
    if (groupMaterialSel == null)
        return false;
    if (groupMaterialSel.selectedIndex == -1)
        return false;
    var group = groupMaterialSel.options[groupMaterialSel.selectedIndex];
    if (venId != '')
        callAjaxCheckError("saveVendorGroupMaterial.do?venId=" + venId + "&groupId=" + group.value, null, null, loadVendorGroupMaterialList);
    groupMaterialSel = null;
    group = null;
    return false;
}
function delVenGroupMaterials() {
    var checkflag = false;
    var vengroupmaterialid = document.forms['venGroupMaterialsForm'].vengroupmaterialid;
    if (vengroupmaterialid == null)
        return false;
    if (vengroupmaterialid.length != null) {
        for (i = 0; i < vengroupmaterialid.length; i++) {
            if (vengroupmaterialid[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else
        checkflag = vengroupmaterialid.checked;
    vengroupmaterialid = null;
    if (checkflag == true)
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?'))
            callAjaxCheckError('delVenGroupMaterial.do', null, document.forms['venGroupMaterialsForm'], loadVendorGroupMaterialList);
    return false;
}

// STORE
function loadStoreList(params) {
    callAjaxEx('storeList.do', 'ajaxContent', 'searchStore.do', 'storeList', params);
    return false;
}

function getStoreListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadStores();
    return false;
}

function loadStores(params) {
    //callAjax("stores.do","storeList",null,null);
    callAjaxExChild("searchStore.do", "storeList", params);
    return false;
}

function addStore(stoId) {
    var url = "storeForm.do";
    if (stoId != null)
        url = url + "?stoId=" + stoId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}

function saveStore() {
    var name = document.forms['storeForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn kho");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addStore.do", null, document.forms[0], getStoreListData);
    return false;
}

function searchStore() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchStore.do", "storeList", document.forms[0], null);
    }
    return false;
}

function delStores() {
    var checkflag = false;
    var stoId = document.forms['storesForm'].stoId;
    if (stoId.length != null) {
        for (i = 0; i < stoId.length; i++) {
            if (stoId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = stoId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delStore.do', 'storeList', document.forms['storesForm'], null);
        }
    }
    stoId = null;
    return false;
}

function searchAdvStoreForm() {
    callAjax('searchAdvStoreForm.do', null, null, showPopupForm);
    return false;
}

function searchAdvStore() {
    callAjax('searchAdvStore.do', null, document.forms['searchStoreForm'], getSearchAdvStoreData);
    hidePopupForm();
    return false;
}

function getSearchAdvStoreData(data) {
    setAjaxData(data, 'storeList');
}

// PROJECT
function loadProjectList(params) {
    callAjaxEx('projectList.do', 'ajaxContent', 'searchProject.do', 'projectList', params);
    return false;
}
function getProjectListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadProjects();
    return false;
}
function projectForm(proId) {
//    if(proId!=null) getProjectTabs(function(){
//        loadProject(proId);
//    });

    if (proId != null) {
        loadProject(proId);
    } else {
        callAjax('projectForm.do', 'ajaxContent', null, null);
    }
    return false;
}

function getProjectTabs(handle) {
    callAjax('projectTabs.do', null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        addProjectTabs();
        handle();
    });
}

function loadProject(proId) {
    callAjax('projectForm.do?proId=' + proId, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
//        setAjaxData(data,'projectdetail');
        //        showhide2('projectFormTitle',true);
        setProjectId(proId);
    });
}

function loadProjects(params) {
    //callAjax("projects.do","projectList",null,null);
    callAjaxExChild("searchProject.do", "projectList", params);
    return false;
}

function addProject(proId) {
    var url = "projectForm.do";
    if (proId != null)
        url = url + "?proId=" + proId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}

function saveProject() {
    var name = document.forms['projectForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn d\u1EF1 \u00E1n");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addProject.do", null, document.forms[0], getProjectListData);
    return false;
}
function searchProject() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchProject.do", "projectList", document.forms[0], null);
    }
    return false;
}
function delProjects() {
    var checkflag = false;
    var proId = document.forms['projectsForm'].proId;
    if (proId.length != null) {
        for (i = 0; i < proId.length; i++) {
            if (proId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = proId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delProject.do', 'projectList', document.forms['projectsForm'], null);
        }
    }
    proId = null;
    return false;
}
function searchAdvProjectForm() {
    callAjax('searchAdvProjectForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvProject() {
    callAjax('searchAdvProject.do', null, document.forms['searchProjectForm'], getSearchAdvProjectData);
    hidePopupForm();
    return false;
}
function getSearchAdvProjectData(data) {
    setAjaxData(data, 'projectList');
}

function addProjectTabs() {
    displayTabs("projectTabs", "projectTabChild", projectTabHandle);
}
function projectTabHandle(child) {
    if (child.isLoaded == 'true')
        return true;
    if (child.id == 'projectstore')
        loadProjectStoreList();
    else if (child.id == 'projectdetail') {
        var proId = getProjectId();
        if (proId != '')
            loadProject(proId);
    }
    child.isLoaded = 'true';
    child = null;
}

function loadProjectStoreList() {
    callAjax('projectStoreList.do', null, null, getProjectStoreListData);
    return false;
}
function getProjectStoreListData(data) {
    setAjaxData(data, 'projectstore');
    loadProjectStores();
    return false;
}
function loadProjectStores() {
    var url = "projectStores.do";
    var proId = getProjectId();
    if (proId != '')
        url = url + "?proId=" + proId;
    callAjax(url, "projectStoreList", null, null);
    return false;
}

function addProjectStore(stoId) {
    var url = "projectStoreForm.do";
    var proId = getProjectId();
    if (proId == null)
        proId = "0";
    url = url + "?proId=" + proId;
    if (stoId != null)
        url = url + "&stoId=" + stoId;
    callAjax(url, "projectstore", null, null);
    return false;
}

function saveProjectStore() {
    var name = document.forms[0].name;
    callAjaxCheckError("addProjectStore.do", null, document.forms['projectStoreForm'], getProjectStoreListData);
    return false;
}

function delProjectStores() {
    var checkflag = false;
    var stoId = document.forms['storesForm'].stoId;
    if (stoId.length != null) {
        for (i = 0; i < stoId.length; i++) {
            if (stoId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = stoId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delProjectStore.do', null, document.forms['storesForm'], loadProjectStores);
        }
    }
    stoId = null;
    return false;

}

function getProjectId() {
    var pro = document.getElementById('proIdHidden');
    var proId = '';
    if (pro != null) {
        proId = pro.value;
        pro = null;
    }
    return proId;
}
function setProjectId(proId) {
    var pro = document.getElementById('proIdHidden');
    if (pro != null) {
        pro.value = proId;
        pro = null;
    }
}

// Position

function loadPositionList(params) {
    callAjaxEx('positionList.do', 'ajaxContent', 'searchPosition.do', 'positionList', params);
    return false;
}
function getPositionListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadPositions();
    return false;
}
function loadPositions(params) {
    callAjaxEx('searchPosition.do', 'positionList', null, null, params);
    return false;
}
function addPosition(posId) {
    var url = "positionForm.do";
    if (posId != null)
        url = url + "?posId=" + posId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function savePosition() {
    var name = document.forms[0].name;
    callAjaxCheckError("addPosition.do", null, document.forms[0], getPositionListData);
    return false;
}
function searchPosition() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchPosition.do", "positionList", document.forms[0], null);
    }
    return false;
}
function delPositions() {
    var checkflag = false;
    var posId = document.forms['positionsForm'].posId;
    if (posId.length != null) {
        for (i = 0; i < posId.length; i++) {
            if (posId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = posId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delPosition.do', 'positionList', document.forms['positionsForm'], null);
        }
    }
    posId = null;
    return false;
}
function searchAdvPositionForm() {
    callAjax('searchAdvPositionForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvPosition() {
    callAjax('searchAdvPosition.do', null, document.forms['searchPositionForm'], getSearchAdvPositionData);
    hidePopupForm();
    return false;
}
function getSearchAdvPositionData(data) {
    setAjaxData(data, 'positionList');
}

// Organization

function loadOrganizationList(params) {
    callAjaxEx('organizationList.do', 'ajaxContent', 'searchOrganization.do', 'organizationList', params);
    return false;
}
function getOrganizationListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadOrganizations();
    return false;
}
function loadOrganizations(params) {
    callAjaxEx('searchOrganization.do', 'organizationList', null, null, params);
    return false;
}
function addOrganization(orgId) {
    var url = "organizationForm.do";
    if (orgId != null)
        url = url + "?orgId=" + orgId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function saveOrganization() {
    var name = document.forms['organizationForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn ph\u00F2ng ban");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addOrganization.do", null, document.forms[0], getOrganizationListData);
    return false;
}
function searchOrganization() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchOrganization.do", "organizationList", document.forms[0], null);
    }
    return false;
}
function delOrganizations() {
    var checkflag = false;
    var orgId = document.forms['organizationsForm'].orgId;
    if (orgId.length != null) {
        for (i = 0; i < orgId.length; i++) {
            if (orgId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = orgId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delOrganization.do', 'organizationList', document.forms['organizationsForm'], null);
        }
    }
    orgId = null;
    return false;
}
function searchAdvOrganizationForm() {
    callAjax('searchAdvOrganizationForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvOrganization() {
    callAjax('searchAdvOrganization.do', null, document.forms['searchOrganizationForm'], getSearchAdvOrganizationData);
    hidePopupForm();
    return false;
}
function getSearchAdvOrganizationData(data) {
    setAjaxData(data, 'organizationList');
}

// Employee

function loadEmployeeList(params) {
    callAjaxEx('employeeList.do', 'ajaxContent', 'searchEmployee.do', 'employeeList', params);
    return false;
}
function getEmployeeListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadEmployees();
    return false;
}
function loadEmployees(params) {
    callAjaxEx('searchEmployee.do', 'employeeList', null, null, params);
    return false;
}
function addEmployee(empId) {
    var url = "employeeForm.do";
    if (empId != null)
        url = url + "?empId=" + empId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function saveEmployee() {
    var password = document.forms[0].password; // document.getElementById('main:password');
    if (password != null)
        document.forms[0].password.value = pwtomd5(password);
    callAjaxCheckError("addEmployee.do", null, document.forms[0], getEmployeeListData);
    return false;
}
function searchEmployee() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchEmployee.do", "employeeList", document.forms[0], null);
    }
    return false;
}
function delEmployees() {
    var checkflag = false;
    var empId = document.forms['employeesForm'].empId;
    if (empId.length != null) {
        for (i = 0; i < empId.length; i++) {
            if (empId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = empId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delEmployee.do', 'employeeList', document.forms['employeesForm'], null);
        }
    }
    empId = null;
    return false;
}
function searchAdvEmployeeForm() {
    callAjax('searchAdvEmployeeForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvEmployee() {
    callAjax('searchAdvEmployee.do', null, document.forms['searchEmployeeForm'], getSearchAdvEmployeeData);
    hidePopupForm();
    return false;
}
function getSearchAdvEmployeeData(data) {
    setAjaxData(data, 'employeeList');
}

//Material 

function spePopupDetail(kind, speId, speId0) {
    var url = "spePopupForm.do?kind=" + kind + "&speIda=" + speId;
    callAjax(url, null, null, function(data) {
        showPopupFormById("popupDialog", data);
        document.forms['spePopupDetailForm'].kind.value = kind;
        document.forms['spePopupDetailForm'].speId.value = speId;
        document.forms['spePopupDetailForm'].speId0.value = speId0;
    });

    return false;
}

function addSpePopupForm() {
    var url = "addSpePopupForm.do";
    var kind = document.forms['spePopupDetailForm'].kind.value;
    var speId0 = document.forms['spePopupDetailForm'].speId0.value
    var speId = document.forms['spePopupDetailForm'].speId.value
    url = url + "?add=1&save=1&speId=" + document.forms['spePopupDetailForm'].speId.value + "&kind=" + document.forms['spePopupDetailForm'].kind.value;
    callAjaxCheckError(url, null, document.forms['spePopupDetailForm'], function(data) {
        hidePopupForm();
        if (kind == 1)
            getSpe01a(speId);
        if (kind == 2)
            getSpe1a(speId0, speId);
        if (kind == 3)
            getSpe2a(speId0, speId);
        if (kind == 4)
            getSpe3a(speId0, speId);
        if (kind == 5)
            getSpe4a(speId0, speId);
    });
    return false;
}

function addSpe(materialId) {
    var url = "speForm.do";
    url = url + "?spe=0";
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        spe01aChanged();
    });
    return false;
}

function addSpe1(materialId) {
    var url = "speForm.do";
    url = url + "?add=1&spe=1";
    callAjax(url, null, null, function(data) {
        //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 1!")
    });
    document.forms[0].sign.disabled = false;
    document.forms[0].sign.value = "";
    document.forms[0].note.disabled = false;
    document.forms[0].note.value = "";
    document.forms[0].spe.value = "1";
    document.forms[0].speId.value = 0;
    document.forms[0].sign.style.backgroundColor = "yellow";
    document.forms[0].note.style.backgroundColor = "yellow";
    return false;
}

function addSpe2(materialId) {
    var url = "speForm.do";
    url = url + "?add=1&spe=2&spe1=" + document.forms[0].spe1Id.value;
    if ((document.forms[0].spe1Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 1!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 2!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "2";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe3(materialId) {
    var url = "speForm.do";
    url = url + "?add=1&spe=3&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value;
    if ((document.forms[0].spe2Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 2!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 3!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "3";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe4(materialId) {
    var url = "speForm.do";
    url = url + "?add=1&spe=4&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value;
    if ((document.forms[0].spe3Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 3!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 4!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "4";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe5(materialId) {
    var url = "speForm.do";
    url = url + "?add=1&spe=5&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value;
    if ((document.forms[0].spe4Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 4!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 5!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "5";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe6(materialId) {
    var url = "speForm.do";
    url = url + "?add=1&spe=6&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value + "&spe5=" + document.forms[0].spe5Id.value;
    if ((document.forms[0].spe5Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 5!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 6!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "6";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe1(materialId) {
    if ((document.forms[0].spe1Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 1!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "1";
        document.forms[0].speId.value = document.forms[0].spe1Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe2(materialId) {
    if ((document.forms[0].spe2Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 2!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "2";
        document.forms[0].speId.value = document.forms[0].spe2Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe3(materialId) {
    if ((document.forms[0].spe3Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 3!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "3";
        document.forms[0].speId.value = document.forms[0].spe3Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe4(materialId) {
    if ((document.forms[0].spe4Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 4!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "4";
        document.forms[0].speId.value = document.forms[0].spe4Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe5(materialId) {
    if ((document.forms[0].spe5Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 5!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "5";
        document.forms[0].speId.value = document.forms[0].spe5Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function delSpe1(materialId) {
    var url = "speForm.do";
    url = url + "?del=1&spe=1&spe1=" + document.forms[0].spe1Id.value;
    callAjaxCheckError(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
        addSpe();
    });
    return false;
}

function delSpe2(materialId) {
    var url = "speForm.do";
    url = url + "?del=1&spe=2&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value;
    callAjaxCheckError(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe2Id != null)
        callAjax("spe2List.do?spe1=" + document.forms[0].spe1Id.value, null, null, function(data) {
            setAjaxData(data, 'spe2Span');
        });
    if (document.forms[0].spe3Id != null)
        callAjax("spe3List.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
            setAjaxData(data, 'spe3Span');
        });
    if (document.forms[0].spe4Id != null)
        callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
            setAjaxData(data, 'spe4Span');
        });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe3(materialId) {
    var url = "speForm.do";
    url = url + "?del=1&spe=3&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value;
    callAjaxCheckError(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe3Id != null)
        callAjax("spe3List.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
            setAjaxData(data, 'spe3Span');
        });
    if (document.forms[0].spe4Id != null)
        callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
            setAjaxData(data, 'spe4Span');
        });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe4(materialId) {
    var url = "speForm.do";
    url = url + "?del=1&spe=4&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value;
    callAjaxCheckError(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe4Id != null)
        callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
            setAjaxData(data, 'spe4Span');
        });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe5(materialId) {
    var url = "speForm.do";
    url = url + "?del=1&spe=5&&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value + "&spe5=" + document.forms[0].spe5Id.value;
    callAjaxCheckError(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe6(materialId) {
    var url = "speForm.do";
    url = url + "?del=1&spe=6&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value + "&spe5=" + document.forms[0].spe5Id.value + "&spe6=" + document.forms[0].spe6Id.value;
    callAjaxCheckError(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function addSpe0(materialId) {

    var url = "speForm.do";
    url = url + "?add=1&save=1&speId=" + document.forms[0].speId.value + "&spe=" + document.forms[0].spe.value;
    //callAjax(url,"ajaxContent",null,null);

    if ((document.forms[0].sign.value.length) == 0 || (document.forms[0].note.value.length) == 0) {
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o \u0111\u1EA7y \u0111\u1EE7 th\u00F4ng tin!");
    }
    else
    {
        //    callAjax(url,null,null,function(data){
        //       alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!")
        //   });     
        callAjaxCheckError(url, null, document.forms['speForm'], function(data) {
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        });
        document.forms[0].sign.disabled = true;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = true;
        document.forms[0].note.value = "";
        document.forms[0].sign.style.backgroundColor = "white";
        document.forms[0].note.style.backgroundColor = "white";
        if (document.forms[0].spe.value == "0") {
            addSpe();
        }
        if (document.forms[0].spe.value == "1") {
            addSpe();
            if (document.forms[0].spe2Id != null)
                callAjax("spe2List.do?spe1=" + document.forms[0].spe1Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe2Span');
                });
            if (document.forms[0].spe3Id != null)
                callAjax("spe3List.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe3Span');
                });
            if (document.forms[0].spe4Id != null)
                callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe4Span');
                });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "2") {
            callAjax("spe2List.do?spe1=" + document.forms[0].spe1Id.value, null, null, function(data) {
                setAjaxData(data, 'spe2Span');
            });
            if (document.forms[0].spe3Id != null)
                callAjax("spe3List.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe3Span');
                });
            if (document.forms[0].spe4Id != null)
                callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe4Span');
                });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "3") {
            callAjax("spe3List.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
                setAjaxData(data, 'spe3Span');
            });
            if (document.forms[0].spe4Id != null)
                callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe4Span');
                });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "4") {
            callAjax("spe4List.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                setAjaxData(data, 'spe4Span');
            });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "5") {
            callAjax("spe5List.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                setAjaxData(data, 'spe5Span');
            });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "6") {
            callAjax("spe6List.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                setAjaxData(data, 'spe6Span');
            });
        }
    }


    return false;
}

function spe1Changed(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe1Id = document.forms[0].spe1Id;
    getSpe1(spe1Id, list.options[list.selectedIndex].value);
    list = null;
    spe1Id = null;
    return false;
}
function getSpe1(spe1Id, kind) {
    var url = "spe2List.do?spe1=" + kind;
    if (spe1Id != null)
        url += "&spe1Id=" + spe1Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe2Span');
    });
}

function spe2Changed(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe2Id = document.forms[0].spe2Id;
    getSpe2(spe2Id, list.options[list.selectedIndex].value);
    list = null;
    spe2Id = null;
    return false;
}
function getSpe2(spe2Id, kind) {
    var url = "spe3List.do?spe2=" + kind;
    if (spe2Id != null)
        url += "&spe2Id=" + spe2Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe3Span');
    });
}

function spe3Changed(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe3Id = document.forms[0].spe3Id;
    getSpe3(spe3Id, list.options[list.selectedIndex].value);
    list = null;
    spe3Id = null;
    return false;
}
function getSpe3(spe3Id, kind) {
    var url = "spe4List.do?spe3=" + kind;
    if (spe3Id != null)
        url += "&spe3Id=" + spe3Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe4Span');
    });
}

function spe4Changed(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe4Id = document.forms[0].spe4Id;
    getSpe4(spe4Id, list.options[list.selectedIndex].value);
    list = null;
    spe4Id = null;
    return false;
}
function getSpe4(spe4Id, kind) {
    var url = "spe5List.do?spe4=" + kind;
    if (spe4Id != null)
        url += "&spe4Id=" + spe4Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe5Span');
    });
}

function spe5Changed(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe5Id = document.forms[0].spe5Id;
    getSpe5(spe5Id, list.options[list.selectedIndex].value);
    list = null;
    spe5Id = null;
    return false;
}
function getSpe5(spe5Id, kind) {
    var url = "spe6List.do?spe5=" + kind;
    if (spe5Id != null)
        url += "&spe5Id=" + spe5Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe6Span');
    });
}

function spe01aChanged() {
    getSpe01a();
    return false;
}
function getSpe01a(spe1Id) {
    var url = "spe1aList.do?";
    if (spe1Id != null)
        url += "spe1Id=" + spe1Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe1Span');

        document.forms[0].spe1Id.value = document.forms[0].spe.value.split(";")[0];
        spe1aChanged(document.forms[0].spe1Id);
    });
    //if (document.forms[0].spe1Id.selectedIndex>0){
    //    document.forms[0].code.value=document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0]+document.forms[0].code.value.substring(2);
    // }
}

function spe1aChanged(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe1Id = document.forms[0].spe1Id.value;
    getSpe1a(spe1Id, list.options[list.selectedIndex].value);
    list = null;
    spe1Id = null;
    return false;
}
function getSpe1a(spe1Id, kind) {
    var url = "spe2aList.do?a=1&spe1=" + kind;
    if (spe1Id != null)
        url += "&spe1Id=" + spe1Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe2Span');
        document.forms[0].spe2Id.value = document.forms[0].spe.value.split(";")[1];
        spe2aChanged(document.forms[0].spe2Id);
    });
    if (document.forms[0].spe1Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0];
//        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(3);
        //document.forms[0].nameVn.value=document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.substring(6);
    }
}

function spe2aChanged(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe2Id = document.forms[0].spe2Id.value;
    getSpe2a(spe2Id, list.options[list.selectedIndex].value);
    list = null;
    spe2Id = null;
    return false;
}
function getSpe2a(spe2Id, kind) {
    var url = "spe3aList.do?a=1&spe2=" + kind;
    if (spe2Id != null)
        url += "&spe2Id=" + spe2Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe3Span');
        document.forms[0].spe3Id.value = document.forms[0].spe.value.split(";")[2];
        spe3aChanged(document.forms[0].spe3Id);
    });
    if (document.forms[0].spe2Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0];
//        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(7);
        // document.forms[0].nameVn.value=document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.substring(6);
    }
}

function spe3aChanged(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe3Id = document.forms[0].spe3Id.value;
    getSpe3a(spe3Id, list.options[list.selectedIndex].value);
    list = null;
    spe3Id = null;
    return false;
}
function getSpe3a(spe3Id, kind) {
    var url = "spe4aList.do?a=1&spe3=" + kind;
    if (spe3Id != null)
        url += "&spe3Id=" + spe3Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe4Span');
        document.forms[0].spe4Id.value = document.forms[0].spe.value.split(";")[3];
        spe4aChanged(document.forms[0].spe4Id);
    });
    if (document.forms[0].spe3Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0];
//        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(11);
        //  document.forms[0].nameVn.value=document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.substring(6);
    }
}

function spe4aChanged(list) {
    if (list.selectedIndex == -1)
        return false;

    var spe4Id = document.forms[0].spe4Id.value;
    getSpe4a(spe4Id, list.options[list.selectedIndex].value);
    list = null;
    spe4Id = null;
    return false;
}
function getSpe4a(spe4Id, kind) {
    var url = "spe5aList.do?a=1&spe4=" + kind;
    if (spe4Id != null)
        url += "&spe4Id=" + spe4Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe5Span');
        document.forms[0].spe5Id.value = document.forms[0].spe.value.split(";")[4];
        spe5aChanged(document.forms[0].spe5Id);
    });
    if (document.forms[0].spe4Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0];
//        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(15);
        //  document.forms[0].nameVn.value=document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.substring(6);
    }
}

function spe5aChanged(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe5Id = document.forms[0].spe5Id.value;
    getSpe5a(spe5Id, list.options[list.selectedIndex].value);
    list = null;
    spe5Id = null;
    return false;
}
function getSpe5a(spe5Id, kind) {
    var url = "spe6aList.do?a=1&spe5=" + kind;
    if (spe5Id != null)
        url += "&spe5Id=" + spe5Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe6Span');
        //document.forms[0].spe6Id.selectedIndex = document.forms[0].spe.value.split(";")[5];
        //spe6aChanged(document.forms[0].spe6Id);
    });
    if (document.forms[0].spe5Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(19);
        //  document.forms[0].nameVn.value=document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.substring(6);
    }
}
function spe6aChanged(list) {
    //document.forms[0].code.value=document.forms[0].spe1Id.value+'-'+document.forms[0].spe2Id.value+'-'+document.forms[0].spe3Id.value+'-'+document.forms[0].spe4Id.value+'-'+document.forms[0].spe5Id.value+'-'+document.forms[0].spe6Id.value+document.forms[0].code.value.substring(23);
    return false;
}
function spe7aChanged(list) {
    //document.forms[0].code.value=document.forms[0].spe1Id.value+'-'+document.forms[0].spe2Id.value+'-'+document.forms[0].spe3Id.value+'-'+document.forms[0].spe4Id.value+'-'+document.forms[0].spe5Id.value+'-'+document.forms[0].spe6Id.value+'-'+document.forms[0].spe7Id.value;
    return false;
}
function loadMaterialList(params) {
    //callAjax('materialList.do',null,null,getMaterialListData);
    callAjaxEx('materialList.do', 'ajaxContent', 'searchMaterial.do', 'materialList', params);
    return false;
}
function getMaterialListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadMaterials();
    return false;
}
function loadMaterials(params) {
    //callAjax("materials.do","materialList",null,null);
    callAjaxExChild("searchMaterial.do", "materialList", params);
    return false;
}
function addMaterial(materialId) {
    var url = "materialForm.do";
    if (materialId != null)
        url = url + "?matId=" + materialId;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        var matId = document.forms['addMaterial'].matId;
        if (matId != null) {
            //Constants.ATTACH_FILE_MATERIAL
            matId = matId.value;
            loadAttachFileSystem(11, matId, 'divMaterialAttachFileSystem');
        }
        spe01aChanged();
    });
    return false;
}
function saveMaterial() {
    var nameVn = document.forms['addMaterial'].nameVn;
    var nameEn = document.forms['addMaterial'].nameEn;
    var uniId = document.forms['addMaterial'].uniId;
    var oriId = document.forms['addMaterial'].oriId;
    var groId = document.forms['addMaterial'].groId;
    var materialId = document.forms['addMaterial'].matId.value;
    var spec, specCode;
    specCode = "";
    spec = document.forms['addMaterial'].spe1Id;
    if (typeof spec != 'undefined') {
        specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe2Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe3Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe4Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe5Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    if (specCode != "")
        specCode = specCode.substring(0, specCode.length - 1);
    document.forms['addMaterial'].spe.value = specCode;
    if (nameVn.value.length == 0 && nameEn.value.length == 0) {
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn = null;
        return false;
    }
    if (uniId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId = null;
        return false;
    }
    if (oriId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn N\u01B0\u1EDBc s\u1EA3n xu\u1EA5t");
        oriId.focus();
        oriId = null;
        return false;
    }
    if (groId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn Nh\u00F3m v\u1EADt t\u01B0");
        groId.focus();
        groId = null;
        return false;
    }
//    callAjaxCheckError("addMaterial.do",null,document.forms[0],getMaterialListData);
    //phuongtu
    callAjaxCheckError("addMaterial.do", null, document.forms['addMaterial'], function(data) {
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        if (materialId == 0) {
            var material = eval('(' + data + ')');
            materialId = material.id;
        }
        addMaterial(materialId);
        //if(document.forms['requestForm']==null){
        //   getMaterialListData(data);
        //}else{
        //    hidePopupForm();
        //    setRequestMaterialDetail(document.forms['addMaterial'].matId.value);
        // }
    }, 'errorMaterialMessageDiv');
    return false;
}
function searchMaterial() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchMaterial.do", "materialList", document.forms[0], null);
    }
    return false;
}
function delMaterials() {
    var checkflag = false;
    var matId = document.forms['materialsForm'].matId;
    if (matId.length != null) {
        for (i = 0; i < matId.length; i++) {
            if (matId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = matId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delMaterial.do', 'materialList', document.forms['materialsForm'], null);
        }
    }
    matId = null;
    return false;
}
function searchAdvMaterialForm() {
    callAjax('searchAdvMaterialForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvMaterial() {
    callAjax('searchAdvMaterial.do', null, document.forms['searchMaterialForm'], getSearchAdvMaterialData);
    hidePopupForm();
    return false;
}
function getSearchAdvMaterialData(data) {
    setAjaxData(data, 'materialList');
}
function codeMaterial(list) {
    //document.forms[0].code.value =(document.forms[0].spe1Id.value +'-'+ document.forms[0].spe2Id.value +'-'+ document.forms[0].spe3Id.value +'-'+ document.forms[0].spe4Id.value +'-'+ document.forms[0].spe5Id.value +'-'+ document.forms[0].spe6Id.value +'-'+ document.forms[0].spe7.value).replace('--','-');
    document.forms[0].code.value = document.forms[0].spe1Id.value;
    spe2Changed(list);
}

//Material Not Code

function spePopupDetailNotCode(kind, speId, speId0) {
    var url = "spePopupFormNotCode.do";
    callAjax(url, null, null, function(data) {
        showPopupFormById("popupDialog", data);
        document.forms['spePopupDetailForm'].kind.value = kind;
        document.forms['spePopupDetailForm'].speId.value = speId;
        document.forms['spePopupDetailForm'].speId0.value = speId0;
    });

    return false;
}

function addSpePopupFormNotCode() {
    var url = "addSpePopupFormNotCode.do";
    var kind = document.forms['spePopupDetailForm'].kind.value;
    var speId0 = document.forms['spePopupDetailForm'].speId0.value
    var speId = document.forms['spePopupDetailForm'].speId.value
    url = url + "?add=1&save=1&sign=" + document.forms['spePopupDetailForm'].sign.value + "&note=" + document.forms['spePopupDetailForm'].note.value + "&speId=" + document.forms['spePopupDetailForm'].speId.value + "&kind=" + document.forms['spePopupDetailForm'].kind.value;
    callAjaxCheckError(url, null, document.forms['spePopupDetailForm'], function(data) {
        hidePopupForm();
        if (kind == 1)
            getSpe01aNotCode(speId);
        if (kind == 2)
            getSpe1aNotCode(speId0, speId);
        if (kind == 3)
            getSpe2aNotCode(speId0, speId);
        if (kind == 4)
            getSpe3aNotCode(speId0, speId);
        if (kind == 5)
            getSpe4aNotCode(speId0, speId);
    });
    return false;
}

function addSpeNotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?spe=0";
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        spe01aChangedNotCode();
    });
    return false;
}

function addSpe1NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&spe=1";
    callAjax(url, null, null, function(data) {
        //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 1!")
    });
    document.forms[0].sign.disabled = false;
    document.forms[0].sign.value = "";
    document.forms[0].note.disabled = false;
    document.forms[0].note.value = "";
    document.forms[0].spe.value = "1";
    document.forms[0].speId.value = 0;
    document.forms[0].sign.style.backgroundColor = "yellow";
    document.forms[0].note.style.backgroundColor = "yellow";
    return false;
}

function addSpe2NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&spe=2&spe1=" + document.forms[0].spe1Id.value;
    if ((document.forms[0].spe1Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 1!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 2!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "2";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe3NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&spe=3&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value;
    if ((document.forms[0].spe2Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 2!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 3!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "3";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe4NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&spe=4&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value;
    if ((document.forms[0].spe3Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 3!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 4!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "4";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe5NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&spe=5&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value;
    if ((document.forms[0].spe4Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 4!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 5!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "5";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function addSpe6NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&spe=6&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value + "&spe5=" + document.forms[0].spe5Id.value;
    if ((document.forms[0].spe5Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 5!");
    }
    else {
        callAjax(url, null, null, function(data) {
            //alert("B\u1EA1n ch\u01B0a th\u00EAm m\u1EDBi \u0111\u1EB7c t\u00EDnh c\u1EA5p 6!")
        });
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = "";
        document.forms[0].spe.value = "6";
        document.forms[0].speId.value = 0;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe1NotCode(materialId) {
    if ((document.forms[0].spe1Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 1!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "1";
        document.forms[0].speId.value = document.forms[0].spe1Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe2NotCode(materialId) {
    if ((document.forms[0].spe2Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 2!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "2";
        document.forms[0].speId.value = document.forms[0].spe2Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe3NotCode(materialId) {
    if ((document.forms[0].spe3Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 3!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "3";
        document.forms[0].speId.value = document.forms[0].spe3Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe4NotCode(materialId) {
    if ((document.forms[0].spe4Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 4!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "4";
        document.forms[0].speId.value = document.forms[0].spe4Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function editSpe5NotCode(materialId) {
    if ((document.forms[0].spe5Id.options.selectedIndex) == 0) {
        alert("B\u1EA1n ch\u01B0a ch\u1ECDn \u0111\u1EB7c t\u00EDnh c\u1EA5p 5!");
    }
    else {
        document.forms[0].sign.disabled = false;
        document.forms[0].sign.value = document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.split(" - ")[0];
        document.forms[0].note.disabled = false;
        document.forms[0].note.value = document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.split(" - ")[1];
        document.forms[0].spe.value = "5";
        document.forms[0].speId.value = document.forms[0].spe5Id.value;
        document.forms[0].sign.style.backgroundColor = "yellow";
        document.forms[0].note.style.backgroundColor = "yellow";
    }
    return false;
}

function delSpe1NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?del=1&spe=1&spe1=" + document.forms[0].spe1Id.value;
    callAjax(url, "ajaxContent", null, null);
    return false;
}

function delSpe2NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?del=1&spe=2&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value;
    callAjax(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe2Id != null)
        callAjax("spe2ListNotCode.do?spe1=" + document.forms[0].spe1Id.value, null, null, function(data) {
            setAjaxData(data, 'spe2Span');
        });
    if (document.forms[0].spe3Id != null)
        callAjax("spe3ListNotCode.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
            setAjaxData(data, 'spe3Span');
        });
    if (document.forms[0].spe4Id != null)
        callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
            setAjaxData(data, 'spe4Span');
        });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe3NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?del=1&spe=3&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value;
    callAjax(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe3Id != null)
        callAjax("spe3ListNotCode.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
            setAjaxData(data, 'spe3Span');
        });
    if (document.forms[0].spe4Id != null)
        callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
            setAjaxData(data, 'spe4Span');
        });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe4NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?del=1&spe=4&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value;
    callAjax(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe4Id != null)
        callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
            setAjaxData(data, 'spe4Span');
        });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe5NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?del=1&spe=5&&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value + "&spe5=" + document.forms[0].spe5Id.value;
    callAjax(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe5Id != null)
        callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
            setAjaxData(data, 'spe5Span');
        });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function delSpe6NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?del=1&spe=6&spe1=" + document.forms[0].spe1Id.value + "&spe2=" + document.forms[0].spe2Id.value + "&spe3=" + document.forms[0].spe3Id.value + "&spe4=" + document.forms[0].spe4Id.value + "&spe5=" + document.forms[0].spe5Id.value + "&spe6=" + document.forms[0].spe6Id.value;
    callAjax(url, null, null, function(data) {
        alert("B\u1EA1n \u0111\u00E3 x\u00F3a th\u00E0nh c\u00F4ng!")
    });
    if (document.forms[0].spe6Id != null)
        callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
            setAjaxData(data, 'spe6Span');
        });
    return false;
}

function addSpe0NotCode(materialId) {
    var url = "speFormNotCode.do";
    url = url + "?add=1&save=1&sign=" + document.forms[0].sign.value + "&note=" + document.forms[0].note.value + "&speId=" + document.forms[0].speId.value + "&spe=" + document.forms[0].spe.value;
    //callAjax(url,"ajaxContent",null,null);

    if ((document.forms[0].sign.value.length) == 0 || (document.forms[0].note.value.length) == 0) {
        alert("B\u1EA1n ch\u01B0a nh\u1EADp v\u00E0o \u0111\u1EA7y \u0111\u1EE7 th\u00F4ng tin!");
    }
    else
    {
        callAjaxCheckError(url, null, document.forms['speForm'], function(data) {
            alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        });
        document.forms[0].sign.disabled = true;
        document.forms[0].sign.value = "";
        document.forms[0].note.disabled = true;
        document.forms[0].note.value = "";
        document.forms[0].sign.style.backgroundColor = "white";
        document.forms[0].note.style.backgroundColor = "white";
        if (document.forms[0].spe.value == "0") {
            addSpeNotCode();
        }
        if (document.forms[0].spe.value == "1") {
            addSpeNotCode();
            if (document.forms[0].spe2Id != null)
                callAjax("spe2ListNotCode.do?spe1=" + document.forms[0].spe1Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe2Span');
                });
            if (document.forms[0].spe3Id != null)
                callAjax("spe3ListNotCode.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe3Span');
                });
            if (document.forms[0].spe4Id != null)
                callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe4Span');
                });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "2") {
            callAjax("spe2ListNotCode.do?spe1=" + document.forms[0].spe1Id.value, null, null, function(data) {
                setAjaxData(data, 'spe2Span');
            });
            if (document.forms[0].spe3Id != null)
                callAjax("spe3ListNotCode.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe3Span');
                });
            if (document.forms[0].spe4Id != null)
                callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe4Span');
                });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "3") {
            callAjax("spe3ListNotCode.do?spe2=" + document.forms[0].spe2Id.value, null, null, function(data) {
                setAjaxData(data, 'spe3Span');
            });
            if (document.forms[0].spe4Id != null)
                callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe4Span');
                });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "4") {
            callAjax("spe4ListNotCode.do?spe3=" + document.forms[0].spe3Id.value, null, null, function(data) {
                setAjaxData(data, 'spe4Span');
            });
            if (document.forms[0].spe5Id != null)
                callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe5Span');
                });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "5") {
            callAjax("spe5ListNotCode.do?spe4=" + document.forms[0].spe4Id.value, null, null, function(data) {
                setAjaxData(data, 'spe5Span');
            });
            if (document.forms[0].spe6Id != null)
                callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                    setAjaxData(data, 'spe6Span');
                });
        }
        if (document.forms[0].spe.value == "6") {
            callAjax("spe6ListNotCode.do?spe5=" + document.forms[0].spe5Id.value, null, null, function(data) {
                setAjaxData(data, 'spe6Span');
            });
        }
    }


    return false;
}

function spe1ChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe1Id = document.forms[0].spe1Id;
    getSpe1NotCode(spe1Id, list.options[list.selectedIndex].value);
    list = null;
    spe1Id = null;
    return false;
}
function getSpe1NotCode(spe1Id, kind) {
    var url = "spe2ListNotCode.do?spe1=" + kind;
    if (spe1Id != null)
        url += "&spe1Id=" + spe1Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe2Span');
    });
}

function spe2ChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe2Id = document.forms[0].spe2Id;
    getSpe2NotCode(spe2Id, list.options[list.selectedIndex].value);
    list = null;
    spe2Id = null;
    return false;
}
function getSpe2NotCode(spe2Id, kind) {
    var url = "spe3ListNotCode.do?spe2=" + kind;
    if (spe2Id != null)
        url += "&spe2Id=" + spe2Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe3Span');
    });
}

function spe3ChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe3Id = document.forms[0].spe3Id;
    getSpe3NotCode(spe3Id, list.options[list.selectedIndex].value);
    list = null;
    spe3Id = null;
    return false;
}
function getSpe3NotCode(spe3Id, kind) {
    var url = "spe4ListNotCode.do?spe3=" + kind;
    if (spe3Id != null)
        url += "&spe3Id=" + spe3Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe4Span');
    });
}

function spe4ChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe4Id = document.forms[0].spe4Id;
    getSpe4NotCode(spe4Id, list.options[list.selectedIndex].value);
    list = null;
    spe4Id = null;
    return false;
}
function getSpe4NotCode(spe4Id, kind) {
    var url = "spe5ListNotCode.do?spe4=" + kind;
    if (spe4Id != null)
        url += "&spe4Id=" + spe4Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe5Span');
    });
}

function spe5ChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe5Id = document.forms[0].spe5Id;
    getSpe5NotCode(spe5Id, list.options[list.selectedIndex].value);
    list = null;
    spe5Id = null;
    return false;
}
function getSpe5NotCode(spe5Id, kind) {
    var url = "spe6ListNotCode.do?spe5=" + kind;
    if (spe5Id != null)
        url += "&spe5Id=" + spe5Id;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe6Span');
    });
}

function spe01aChangedNotCode() {
    getSpe01aNotCode();
    return false;
}
function getSpe01aNotCode(spe1Id) {
    var url = "spe1aListNotCode.do?";
    if (spe1Id != null)
        url += "spe1Id=" + spe1Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe1Span');
        document.forms[0].spe1Id.value = document.forms[0].spe.value.split(";")[0];
        spe1aChangedNotCode(document.forms[0].spe1Id);
    });
    //if (document.forms[0].spe1Id.selectedIndex>0){
    //    document.forms[0].code.value=document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0]+document.forms[0].code.value.substring(2);
    // }
}

function spe1aChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe1Id = document.forms[0].spe1Id.value;
    getSpe1aNotCode(spe1Id, list.options[list.selectedIndex].value);
    list = null;
    spe1Id = null;
    return false;
}
function getSpe1aNotCode(spe1Id, kind) {
    var url = "spe2aListNotCode.do?a=1&spe1=" + kind;
    if (spe1Id != null)
        url += "&spe1Id=" + spe1Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe2Span');
        document.forms[0].spe2Id.value = document.forms[0].spe.value.split(";")[1];
        spe2aChangedNotCode(document.forms[0].spe2Id);
    });
    if (document.forms[0].spe1Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(3);
    }
}

function spe2aChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe2Id = document.forms[0].spe2Id.value;
    getSpe2aNotCode(spe2Id, list.options[list.selectedIndex].value);
    list = null;
    spe2Id = null;
    return false;
}
function getSpe2aNotCode(spe2Id, kind) {
    var url = "spe3aListNotCode.do?a=1&spe2=" + kind;
    if (spe2Id != null)
        url += "&spe2Id=" + spe2Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe3Span');
        document.forms[0].spe3Id.value = document.forms[0].spe.value.split(";")[2];
        spe3aChangedNotCode(document.forms[0].spe3Id);
    });
    if (document.forms[0].spe2Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(7);
    }
}

function spe3aChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe3Id = document.forms[0].spe3Id.value;
    getSpe3aNotCode(spe3Id, list.options[list.selectedIndex].value);
    list = null;
    spe3Id = null;
    return false;
}
function getSpe3aNotCode(spe3Id, kind) {
    var url = "spe4aListNotCode.do?a=1&spe3=" + kind;
    if (spe3Id != null)
        url += "&spe3Id=" + spe3Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe4Span');
        document.forms[0].spe4Id.value = document.forms[0].spe.value.split(";")[3];
        spe4aChangedNotCode(document.forms[0].spe4Id);
    });
    if (document.forms[0].spe3Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(11);
    }
}

function spe4aChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe4Id = document.forms[0].spe4Id.value;
    getSpe4aNotCode(spe4Id, list.options[list.selectedIndex].value);
    list = null;
    spe4Id = null;
    return false;
}
function getSpe4aNotCode(spe4Id, kind) {
    var url = "spe5aListNotCode.do?a=1&spe4=" + kind;
    if (spe4Id != null)
        url += "&spe4Id=" + spe4Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe5Span');
        document.forms[0].spe5Id.value = document.forms[0].spe.value.split(";")[4];
        spe5aChangedNotCode(document.forms[0].spe5Id);
    });
    if (document.forms[0].spe4Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(15);
    }
}

function spe5aChangedNotCode(list) {
    if (list.selectedIndex == -1)
        return false;
    var spe5Id = document.forms[0].spe5Id.value;
    getSpe5aNotCode(spe5Id, list.options[list.selectedIndex].value);
    list = null;
    spe5Id = null;
    return false;
}
function getSpe5aNotCode(spe5Id, kind) {
    var url = "spe6aListNotCode.do?a=1&spe5=" + kind;
    if (spe5Id != null)
        url += "&spe5Id=" + spe5Id + "&spe=" + document.forms[0].spe.value;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'spe6Span');
        //document.forms[0].spe6Id.selectedIndex = document.forms[0].spe.value.split(";")[5];
        //spe6aChangedNotCode(document.forms[0].spe6Id);
    });
    if (document.forms[0].spe5Id.selectedIndex > 0) {
        document.forms[0].code.value = document.forms[0].spe1Id.options[document.forms[0].spe1Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe2Id.options[document.forms[0].spe2Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe3Id.options[document.forms[0].spe3Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe4Id.options[document.forms[0].spe4Id.selectedIndex].text.split(" - ")[0] + '.' + document.forms[0].spe5Id.options[document.forms[0].spe5Id.selectedIndex].text.split(" - ")[0] + document.forms[0].code.value.substring(19);
    }
}
function spe6aChangedNotCode(list) {
    //document.forms[0].code.value=document.forms[0].spe1Id.value+'-'+document.forms[0].spe2Id.value+'-'+document.forms[0].spe3Id.value+'-'+document.forms[0].spe4Id.value+'-'+document.forms[0].spe5Id.value+'-'+document.forms[0].spe6Id.value+document.forms[0].code.value.substring(23);
    return false;
}
function spe7aChangedNotCode(list) {
    //document.forms[0].code.value=document.forms[0].spe1Id.value+'-'+document.forms[0].spe2Id.value+'-'+document.forms[0].spe3Id.value+'-'+document.forms[0].spe4Id.value+'-'+document.forms[0].spe5Id.value+'-'+document.forms[0].spe6Id.value+'-'+document.forms[0].spe7Id.value;
    return false;
}
function loadMaterialListNotCode(params) {
    //callAjax('materialListNotCode.do',null,null,getMaterialListData);
    callAjaxEx('materialListNotCode.do', 'ajaxContent', 'searchMaterialNotCode.do', 'materialList', params);
    return false;
}
function getMaterialListDataNotCode(data) {
    setAjaxData(data, 'ajaxContent');
    loadMaterialsNotCode();
    return false;
}
function loadMaterialsNotCode(params) {
    //callAjax("materialsNotCode.do","materialList",null,null);
    callAjaxExChild("searchMaterialNotCode.do", "materialList", params);
    return false;
}
function addMaterialNotCode(materialId, reqId) {
    var url = "materialFormNotCode.do";
    if (materialId != null)
        url = url + "?matId=" + materialId + "&reqId=" + reqId;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        spe01aChangedNotCode();
        //spe1aChangedNotCode(document.forms[0].spe1Id);
    });
    return false;
}
function emailForSameMaterial() {
    var reqId = document.forms['addMaterial'].reqId;
    if (reqId == null)
        return false;
    else
        reqId = reqId.value;
    var matId = document.forms['addMaterial'].matId;
    if (matId == null)
        return false;
    else
        matId = matId.value;
    var code = document.forms['addMaterial'].code;
    if (code == null)
        return false;
    else
        code = code.value;
    callAjaxCheckError("emailForSameMaterial.do?reqId=" + reqId + "&matId=" + matId + "&code=" + code, null, null, function(data) {
        alert("\u0110\u00E3 g\u1EDFi email!");
    });
    return false;
}
function saveMaterialNotCode() {
    var nameVn = document.forms['addMaterial'].nameVn;
    var nameEn = document.forms['addMaterial'].nameEn;
    var uniId = document.forms['addMaterial'].uniId;
    var oriId = document.forms['addMaterial'].oriId;
    var groId = document.forms['addMaterial'].groId;
    var code = document.forms['addMaterial'].code;
    var materialId = document.forms['addMaterial'].matId.value;
    var reqId = document.forms['addMaterial'].reqId.value;
    if (code.value.length == 0) {
        alert("Vui l\u00F2ng t\u1EA1o m\u00E3 cho v\u1EADt t\u01B0!");
        code.focus();
        code = null;
        return false;
    }
    code = null;
    var spec, specCode;
    specCode = "";
    spec = document.forms['addMaterial'].spe1Id;
    if (typeof spec != 'undefined') {
        specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe2Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe3Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe4Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    spec = document.forms['addMaterial'].spe5Id;
    if (typeof spec != 'undefined') {
        if (spec.value != "")
            specCode += spec.value + ";";
    }
    if (specCode != "")
        specCode = specCode.substring(0, specCode.length - 1);
    document.forms['addMaterial'].spe.value = specCode;
    if (nameVn.value.length == 0 && nameEn.value.length == 0) {
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn = null;
        return false;
    }
    if (uniId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId = null;
        return false;
    }
    if (oriId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn N\u01B0\u1EDBc s\u1EA3n xu\u1EA5t");
        oriId.focus();
        oriId = null;
        return false;
    }
    if (groId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn Nh\u00F3m v\u1EADt t\u01B0");
        groId.focus();
        groId = null;
        return false;
    }
//    callAjaxCheckError("addMaterialNotCode.do",null,document.forms[0],getMaterialListData);
    //phuongtu
    callAjaxCheckError("addMaterialNotCode.do", null, document.forms['addMaterial'], function(data) {
        alert("L\u01B0u d\u1EEF li\u1EC7u th\u00E0nh c\u00F4ng!");
        addMaterial(materialId);
        //if(document.forms['requestForm']==null){
        //    getMaterialListDataNotCode(data);
        //}else{
        //   hidePopupForm();
        //   setRequestMaterialDetailNotCode(document.forms['addMaterial'].matId.value);
        //}
    }, 'errorMaterialMessageDiv');
    return false;
}
function searchMaterialNotCode() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchMaterialNotCode.do", "materialList", document.forms[0], null);
    }
    return false;
}
function delMaterialsNotCode() {
    if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
        callAjaxCheckError('delMaterialNotCode.do', 'materialList', document.forms['materialsForm'], null);
    }
    return false;
}
function searchAdvMaterialFormNotCode() {
    callAjax('searchAdvMaterialFormNotCode.do', null, null, showPopupForm);
    return false;
}
function searchAdvMaterialNotCode() {
    callAjax('searchAdvMaterialNotCode.do', null, document.forms['searchMaterialForm'], getSearchAdvMaterialData);
    hidePopupForm();
    return false;
}
function getSearchAdvMaterialDataNotCode(data) {
    setAjaxData(data, 'materialList');
}
function codeMaterialNotCode(list) {
    //document.forms[0].code.value =(document.forms[0].spe1Id.value +'-'+ document.forms[0].spe2Id.value +'-'+ document.forms[0].spe3Id.value +'-'+ document.forms[0].spe4Id.value +'-'+ document.forms[0].spe5Id.value +'-'+ document.forms[0].spe6Id.value +'-'+ document.forms[0].spe7.value).replace('--','-');
    document.forms[0].code.value = document.forms[0].spe1Id.value;
    spe2ChangedNotCode(list);
}

//Ematerial
function loadEmaterialList(params) {
    //callAjax('ematerialList.do',null,null,getEmaterialListData);
    callAjaxEx('ematerialList.do', 'ajaxContent', 'searchEmaterial.do', 'ematerialList', params);
    return false;
}
function getEmaterialListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadEmaterials();
    return false;
}
function loadEmaterials(params) {
    //callAjax("ematerials.do","ematerialList",null,null);
    callAjaxExChild("searchEmaterial.do", "ematerialList", params);
    return false;
}
function addEmaterial(ematerialId) {
    var url = "ematerialForm.do";
    if (ematerialId != null)
        url = url + "?ematId=" + ematerialId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function saveEmaterial() {
    var nameVn = document.forms['addEmaterial'].nameVn;
    var nameEn = document.forms['addEmaterial'].nameEn;
    var uniId = document.forms['addEmaterial'].uniId;
    var oriId = document.forms['addEmaterial'].oriId;
    var kind = document.forms['addEmaterial'].kind;
    if (nameVn.value.length == 0 && nameEn.value.length == 0) {
        alert("Nh\u1EADp v\u00E0o T\u00EAn v\u1EADt t\u01B0!");
        nameVn.focus();
        nameVn = null;
        return false;
    }
    if (uniId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn \u0110\u01A1n v\u1ECB t\u00EDnh");
        uniId.focus();
        uniId = null;
        return false;
    }
    if (oriId.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn N\u01B0\u1EDBc s\u1EA3n xu\u1EA5t");
        oriId.focus();
        oriId = null;
        return false;
    }
    if (kind.selectedIndex == 0) {
        alert("Vui l\u00F2ng ch\u1ECDn Lo\u1EA1i v\u1EADt t\u01B0");
        kind.focus();
        kind = null;
        return false;
    }
    callAjaxCheckError("addEmaterial.do", null, document.forms[0], getEmaterialListData);
    return false;
}
function searchEmaterial() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchEmaterial.do", "ematerialList", document.forms[0], null);
    }
    return false;
}
function delEmaterials() {
    var checkflag = false;
    var ematId = document.forms['ematerialsForm'].ematId;
    if (ematId.length != null) {
        for (i = 0; i < ematId.length; i++) {
            if (ematId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = ematId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delEmaterial.do', 'ematerialList', document.forms['ematerialsForm'], null);
        }
    }
    ematId = null;
    return false;
}
function searchAdvEmaterialForm() {
    callAjax('searchAdvEmaterialForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvEmaterial() {
    callAjax('searchAdvEmaterial.do', null, document.forms['searchEmaterialForm'], getSearchAdvEmaterialData);
    hidePopupForm();
    return false;
}
function getSearchAdvEmaterialData(data) {
    setAjaxData(data, 'ematerialList');
}

//Origin

function loadOriginList(params) {
    callAjaxEx('originList.do', 'ajaxContent', 'searchOrigin.do', 'originList', params);
    return false;
}
function getOriginListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadOrigins();
    return false;
}
function loadOrigins(params) {
    //callAjax("origins.do","originList",null,null);
    callAjaxExChild("searchOrigin.do", "originList", params);
    return false;
}
function addOrigin(originId) {
    var url = "originForm.do";
    if (originId != null)
        url = url + "?oriId=" + originId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function saveOrigin() {
    var originEn = document.forms[0].nameEn;
    var originVn = document.forms[0].nameVn;
    if (originEn.value == '' && originVn.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp Xu\u1EA5t x\u1EE9!");
        originVn.focus();
        originVn = null;
        return false;
    }
    callAjaxCheckError("addOrigin.do", null, document.forms[0], getOriginListData);
    return false;
}
function searchOrigin() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchOrigin.do", "originList", document.forms[0], null);
    }
    return false;
}
function delOrigins() {
    var checkflag = false;
    var oriId = document.forms['originsForm'].oriId;
    if (oriId.length != null) {
        for (i = 0; i < oriId.length; i++) {
            if (oriId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = oriId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delOrigin.do', 'originList', document.forms['originsForm'], null);
        }
    }
    oriId = null;
    return false;
}
function searchAdvOriginForm() {
    callAjax('searchAdvOriginForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvOrigin() {
    callAjax('searchAdvOrigin.do', null, document.forms['searchOriginForm'], getSearchAdvOriginData);
    hidePopupForm();
    return false;
}
function getSearchAdvOriginData(data) {
    setAjaxData(data, 'originList');
}

//Unit
function loadUnitList(params) {
    callAjaxEx('unitList.do', 'ajaxContent', 'searchUnit.do', 'unitList', params);
    return false;
}
function getUnitListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadUnits();
    return false;
}
function loadUnits(params) {
    //callAjax("units.do","unitList",null,null);
    callAjaxExChild("searchUnit.do", "unitList", params);
    return false;
}
function addUnit(unitId) {
    var url = "unitForm.do";
    if (unitId != null)
        url = url + "?uniId=" + unitId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function saveUnit() {
    var unitEn = document.forms[0].unitEn;
    var unitVn = document.forms[0].unitVn;
    if (unitEn.value == '' && unitVn.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp T\u00EAn \u0110\u01A1n v\u1ECB t\u00EDnh!");
        unitVn.focus();
        unitVn = null;
        return false;
    }
    callAjaxCheckError("addUnit.do", null, document.forms[0], getUnitListData);
    return false;
}
function searchUnit() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp \u0111i\u1EC1u ki\u1EC7n t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchUnit.do", "unitList", document.forms[0], null);
    }
    return false;
}
function delUnits() {
    var checkflag = false;
    var uniId = document.forms['unitsForm'].uniId;
    if (uniId.length != null) {
        for (i = 0; i < uniId.length; i++) {
            if (uniId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = uniId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delUnit.do', 'unitList', document.forms['unitsForm'], null);
        }
    }
    uniId = null;
    return false;
}
function searchAdvUnitForm() {
    callAjax('searchAdvUnitForm.do', null, null, showPopupForm);
    return false;
}
function searchAdvUnit() {
    callAjax('searchAdvUnit.do', null, document.forms['searchUnitForm'], getSearchAdvUnitData);
    hidePopupForm();
    return false;
}
function getSearchAdvUnitData(data) {
    setAjaxData(data, 'unitList');
}
function loadMaterialPriceList(params) {
//    callAjaxEx('materialPriceList.do','ajaxContent','materialPrices.do','materialPriceList',params);
    var url = "materialPriceList.do";
    if (params != null)
        url += "?" + params;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        var currentTime = new Date();
        var date = currentTime.getDate();
        var month = currentTime.getMonth() + 1;
        if (date < 10)
            date = '0' + date;
        if (month < 10)
            month = '0' + month;
        var year = currentTime.getFullYear() - 1;
        document.getElementById('fromDateMaterialPriceFromDate').value = date + '/' + month + '/' + year;
        document.getElementById('toDateMaterialPriceToDate').value = date + '/' + month + '/' + currentTime.getFullYear();
        url = "materialPrices.do?t=1&fromDate=" + document.getElementById('fromDateMaterialPriceFromDate').value + "&toDate=" + document.getElementById('toDateMaterialPriceToDate').value;
        if (params != null)
            url += "?" + params;
        callAjax(url, null, null, function(data) {
            setAjaxData(data, 'materialPriceList');
            var price = document.forms['materialPricesForm'].materialPrice;
            if (price != null) {
                if (price.length != null) {
                    for (i = 0; i < price.length; i++) {
                        //tryNumberFormat(price[i],0,0);
                        tryNumberFormat(price[i]);
                    }
                } else {
                    //tryNumberFormat(price,0,0);
                    tryNumberFormat(price);
                }
            }
            price = null;
        });
    });
    return false;
}
function getMaterialPriceData(data) {
    setAjaxData(data, 'ajaxContent');
    loadMaterialPrices();
    return false;
}
function loadMaterialPrices(params) {
//    callAjax("materialPrices.do","materialPriceList",null,null);
    callAjaxEx('materialPrices.do', 'materialPriceList', null, null, params);
    return false;
}
function loadMaterialPricesSort(params) {
//    callAjax("materialPrices.do","materialPriceList",null,null);
    callAjaxEx('searchMaterialPrice.do', 'materialPriceList', null, null, params);
    return false;
}

//PERMISSION
function loadPermissionList(params) {
    callAjaxEx('permissionList.do', 'ajaxContent', 'permissions.do', 'permissionList', params);
    return false;
}
function getPermissionListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadPermissions();
    return false;
}
function loadPermissions() {
    callAjax("permissions.do", "permissionList", null, null);
    return false;
}
function permissionForm(perId) {
    var url = "permissionForm.do";
    if (perId != null)
        url = url + "?perId=" + perId;
    callAjax(url, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
    });
    return false;
}
function savePermission() {
    var name = document.forms['permissionForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn nh\u00F3m ph\u00E2n quy\u1EC1n");
        name.focus();
        name = null;
        return false;
    }
    callAjaxCheckError("savePermission.do", null, document.forms['permissionForm'], getPermissionListData);
    return false;
}
function searchMaterialPrice() {
    var checkflag = true;
    if (document.forms['searchSimpleForm'].searchid.value != 0) {
        if (document.forms['searchSimpleForm'].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms['searchSimpleForm'].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchMaterialPrice.do?t=1&fromDate=" + document.getElementById('fromDateMaterialPriceFromDate').value + "&toDate=" + document.getElementById('toDateMaterialPriceToDate').value, null, document.forms['searchSimpleForm'], function(data) {
            setAjaxData(data, 'materialPriceList');
            var price = document.forms['materialPricesForm'].materialPrice;
            if (price != null) {
                if (price.length != null) {
                    for (i = 0; i < price.length; i++) {
                        //tryNumberFormat(price[i],0,0);
                        tryNumberFormat(price[i]);
                    }
                } else {
                    //tryNumberFormat(price,0,0);
                    tryNumberFormat(price);
                }
            }
            price = null;
        });
    }
    return false;
}
function permissionLevel1Change(srcObject, name) {
    var objs = document.getElementsByName(name);
    var chk = srcObject.checked;
    for (var i = 0; i < objs.length; i++) {
        if (objs[i].id == srcObject.id && objs[i] != srcObject) {
            objs[i].checked = chk;
        }
    }
    return false;
}
// CERTIFICATE
function certificateForm(cerId) {
    if (cerId != null) {
        loadCertificate(cerId);
    } else {
        callAjax('certificateForm.do', 'ajaxContent', null, null);
    }
    return false;
}

function loadCertificate(cerId) {
    callAjax('certificateForm.do?cerId=' + cerId, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        setCerId(cerId);
    });
}

function loadCertificateList(params) {
    callAjaxEx('certificateList.do', 'ajaxContent', 'searchCertificate.do', 'certificateList', params);
    return false;
}

function getCertificateListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadCertificates();
    return false;
}

function loadCertificates(params) {
    //callAjax("certificates.do","certificateList",null,null);
    callAjaxExChild("searchCertificate.do", "certificateList", params);
    return false;
}

function addCertificate(cerId) {
    var url = "certificateForm.do";
    if (cerId != null)
        url = url + "?cerId=" + cerId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}

function saveCertificate() {
    var name = document.forms['certificateForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn certificatesite");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addCertificate.do", null, document.forms[0], getCertificateListData);
    return false;
}
function searchCertificate() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchCertificate.do", "certificateList", document.forms[0], null);
    }
    return false;
}

function delCertificates() {
    var checkflag = false;
    var cerId = document.forms['certificatesForm'].cerId;
    if (cerId.length != null) {
        for (i = 0; i < cerId.length; i++) {


            if (cerId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = cerId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delCertificate.do', 'certificateList', document.forms['certificatesForm'], null);
        }
    }
    cerId = null;
    return false;
}

function getCerId() {
    var certificate = document.getElementById('cerIdHidden');
    var cerId = '';
    if (certificate != null) {
        cerId = certificate.value;
        certificate = null;
    }
    return cerId;
}
function setCerId(cerId) {
    var certificate = document.getElementById('cerIdHidden');
    if (certificate != null) {
        certificate.value = cerId;
        certificate = null;
    }
}
// incoterm
function incotermForm(incId) {
    if (incId != null) {
        loadIncoterm(incId);
    } else {
        callAjax('incotermForm.do', 'ajaxContent', null, null);
    }
    return false;
}

function loadIncoterm(incId) {
    callAjax('incotermForm.do?incId=' + incId, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        setIncId(incId);
    });
}

function loadIncotermList(params) {
    callAjaxEx('incotermList.do', 'ajaxContent', 'searchIncoterm.do', 'incotermList', params);
    return false;
}

function getIncotermListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadIncoterms();
    return false;
}

function loadIncoterms(params) {
    //callAjax("incoterms.do","incotermList",null,null);     callAjaxExChild("searchIncoterm.do", "incotermList", params);
    return false;
}

function addIncoterm(incId) {
    var url = "incotermForm.do";
    if (incId != null)
        url = url + "?incId=" + incId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}

function saveIncoterm() {
    var name = document.forms['incotermForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn incotermsite");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addIncoterm.do", null, document.forms[0], getIncotermListData);
    return false;
}

function searchIncoterm() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchIncoterm.do", "incotermList", document.forms[0], null);
    }
    return false;
}

function delIncoterms() {
    var checkflag = false;
    var incId = document.forms['incotermsForm'].incId;
    if (incId.length != null) {
        for (i = 0; i < incId.length; i++) {

            if (incId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = incId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delIncoterm.do', 'incotermList', document.forms['incotermsForm'], null);
        }
    }
    incId = null;
    return false;
}

function getIncId() {
    var incoterm = document.getElementById('incIdHidden');
    var incId = '';
    if (incoterm != null) {
        incId = incoterm.value;
        incoterm = null;

    }
    return incId;
}
function setIncId(incId) {
    var incoterm = document.getElementById('incIdHidden');
    if (incoterm != null) {
        incoterm.value = incId;
        incoterm = null;
    }
}
// WEB
function webForm(webId) {
    if (webId != null) {
        loadWeb(webId);
    } else {
        callAjax('webForm.do', 'ajaxContent', null, null);
    }
    return false;
}

function loadWeb(webId) {
    callAjax('webForm.do?webId=' + webId, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        setWebId(webId);
    });
}

function loadWebList(params) {
    callAjaxEx('webList.do', 'ajaxContent', 'searchWeb.do', 'webList', params);
    return false;
}

function getWebListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadWebs();
    return false;
}

function loadWebs(params) {
    //callAjax("webs.do","webList",null,null);
    callAjaxExChild("searchWeb.do", "webList", params);
    return false;
}

function addWeb(webId) {
    var url = "webForm.do";
    if (webId != null)
        url = url + "?webId=" + webId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}
function saveWeb() {
    var name = document.forms['webForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn website");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addWeb.do", null, document.forms[0], getWebListData);
    return false;
}

function searchWeb() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchWeb.do", "webList", document.forms[0], null);
    }
    return false;
}
function delWebs() {
    var checkflag = false;
    var webId = document.forms['websForm'].webId;
    if (webId.length != null) {
        for (i = 0; i < webId.length; i++) {


            if (webId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = webId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delWeb.do', 'webList', document.forms['websForm'], null);
        }
    }
    webId = null;
    return false;
}

function getWebId() {
    var web = document.getElementById('webIdHidden');
    var webId = '';
    if (web != null) {
        webId = web.value;
        web = null;
    }
    return webId;
}
function setWebId(webId) {
    var web = document.getElementById('webIdHidden');
    if (web != null) {
        web.value = webId;
        web = null;
    }
}
function loadSystemConfig() {
    callAjax('configForm.do', 'ajaxContent', null, null);
    return false;
}
function saveConfig() {
    callAjaxCheckError("saveConfig.do", null, document.forms['configForm'], function(data) {
        //        if(data=='true')
        alert('Insert successfully');
        //        else alert('Insert unsuccessfully');
    });
    return false;
}

// GROUP MATERIAL
function groupMaterialForm(groId) {
    if (groId != null) {
        loadGroupMaterial(groId);
    } else {
        callAjax('groupMaterialForm.do', 'ajaxContent', null, null);
    }
    return false;
}

function loadGroupMaterial(groId) {
    callAjax('groupMaterialForm.do?groId=' + groId, null, null, function(data) {
        setAjaxData(data, 'ajaxContent');
        setGroId(groId);
    });
}

function loadGroupMaterialList(params) {
    callAjaxEx('groupMaterialList.do', 'ajaxContent', 'searchGroupMaterial.do', 'groupMaterialList', params);
    return false;
}

function getGroupMaterialListData(data) {
    setAjaxData(data, 'ajaxContent');
    loadGroupMaterials();
    return false;
}

function loadGroupMaterials(params) {
    //callAjax("groupMaterials.do","groupMaterialList",null,null);
    callAjaxExChild("searchGroupMaterial.do", "groupMaterialList", params);
    return false;
}

function addGroupMaterial(groId) {
    var url = "groupMaterialForm.do";
    if (groId != null)
        url = url + "?groId=" + groId;
    callAjax(url, "ajaxContent", null, null);
    return false;
}

function saveGroupMaterial() {
    var name = document.forms['groupMaterialForm'].name;
    if (name.value == '') {
        alert("Vui l\u00F2ng nh\u1EADp t\u00EAn nh\u00F3m v\u1EADt t\u01B0");
        name.focus();
        name = null;
        return false;
    }
    name = null;
    callAjaxCheckError("addGroupMaterial.do", null, document.forms[0], getGroupMaterialListData);
    return false;
}

function searchGroupMaterial() {
    var checkflag = true;
    if (document.forms[0].searchid.value != 0) {
        if (document.forms[0].searchvalue.value == "") {
            alert('Vui l\u00F2ng nh\u1EADp gi\u00E1 tr\u1ECB t\u00ECm ki\u1EBFm');
            checkflag = false;
        }
    } else {
        document.forms[0].searchvalue.value = "";
    }
    if (checkflag == true) {
        callAjax("searchGroupMaterial.do", "groupMaterialList", document.forms[0], null);
    }
    return false;
}

function delGroupMaterials() {
    var checkflag = false;
    var groId = document.forms['groupMaterialsForm'].groId;
    if (groId.length != null) {
        for (i = 0; i < groId.length; i++) {
            if (groId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = groId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delGroupMaterial.do', 'groupMaterialList', document.forms['groupMaterialsForm'], null);
        }
    }
    groId = null;
    return false;
}

function getGroId() {
    var groupMaterial = document.getElementById('groIdHidden');
    var groId = '';
    if (groupMaterial != null) {
        groId = groupMaterial.value;
        groupMaterial = null;
    }
    return groId;
}
function setGroId(groId) {
    var groupMaterial = document.getElementById('groIdHidden');
    if (groupMaterial != null) {
        groupMaterial.value = groId;
        groupMaterial = null;
    }
}

function exportMaterial() {
    var form = document.forms['searchSimpleForm'];
    var url = "materialReportPrint.do?searchid=" + form.searchid.value + "&searchvalue=" + encodeURIComponent(form.searchvalue.value);
    form = null;
    callServer(url);
    return false;
}

function exportMaterialPrice() {
    var form = document.forms['searchSimpleForm'];
    var url = "materialPriceReportPrint.do?searchid=" + form.searchid.value + "&searchvalue=" + encodeURIComponent(form.searchvalue.value);
    url += "&fromDate=" + document.getElementById('fromDateMaterialPriceFromDate').value + "&toDate=" + document.getElementById('toDateMaterialPriceToDate').value;
    form = null;
    callServer(url);
    return false;
}
function delPrices() {
    var checkflag = false;
    var autoId = document.forms['materialPricesForm'].autoId;
    if (autoId.length != null) {
        for (i = 0; i < autoId.length; i++) {
            if (autoId[i].checked == true) {
                checkflag = true;
                break;
            }
        }
    } else {
        checkflag = autoId.checked;
    }
    if (checkflag == true) {
        if (confirm('B\u1EA1n th\u1EF1c s\u1EF1 mu\u1ED1n xo\u00E1 nh\u1EEFng th\u00F4ng tin n\u00E0y ?')) {
            callAjaxCheckError('delPrice.do', null, document.forms['materialPricesForm'], function(data) {
                loadMaterialPriceList();
            });
        }
    }
    empId = null;
    return false;
}