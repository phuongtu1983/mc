function $defined(A){
    return(A!=undefined);
}
function $type(B){
    if(!$defined(B)){
        return false;
    }
    if(B.htmlElement){
        return"element";
    }
    var A=typeof B;
    if(A=="object"&&B.nodeName){
        switch(B.nodeType){
            case 1:return"element";
            case 3:return(/\S/).test(B.nodeValue)?"textnode":"whitespace";
        }
    }
    if(A=="object"||A=="function"){
        switch(B.constructor){
            case Array:return"array";
            case RegExp:return"regexp";
        }
        if(typeof B.length=="number"){
            if(B.item){
                return"collection";
            }
            if(B.callee){
                return"arguments";
            }
        }
    }
    try{
        return A;
    }
    finally{
        A=null;
    }
}
var ie45,ns6,ns4,dom;
if (navigator.appName=="Microsoft Internet Explorer") {
    ie45=parseInt(navigator.appVersion)>=4;
} else if (navigator.appName=="Netscape") {
    ns6=parseInt(navigator.appVersion)>=5;
    ns4=parseInt(navigator.appVersion)<5;
}
dom=ie45 || ns6;
function showhide(id) {
    el = document.all ? document.all[id] :
    dom ? document.getElementById(id) :
    document.layers[id];
    try{
        els = dom ? el.style : el;
        if (dom){
            if (els.display == "none") els.display = "";
            else els.display = "none";
        }
        else if (ns4){
            if (els.display == "show") els.display = "hide";
            else els.display = "show";
        }
    }
    finally{
        el=null;
    }
}
function isshow(id) {
    result = false;
    try{
        var type = $type(id);
        var el;
        if (type=='string'){
            el = document.all ? document.all[id] :
            dom ? document.getElementById(id) :
            document.layers[id];        
        }    
        else
            el=id;
        els = dom ? el.style : el;
        if (dom){
            if(els.display == "") result = true;
        }
        else if (ns4){
            if(els.display == "show") result = true;
        }
        return result;
    }
    finally{
        result=null;
    }
}
function showhide2(id, ishide) {
    var type = $type(id);
    var el;
    if (type=='string'){
        el = document.all ? document.all[id] :
        dom ? document.getElementById(id) :
        document.layers[id];        
    }    
    else el=id;
    els = dom ? el.style : el;
    if (dom){
        if (ishide == false) els.display = "";
        else els.display = "none";//khong hien thi
    }
    else if (ns4){
        if (ishide == true) els.display = "hide";//khong hien thi
        else els.display = "show";
    }
}
function trimLeft(s) {
    var whitespaces = " \t\n\r";
    for(n = 0; n < s.length; n++) { 
        if (whitespaces.indexOf(s.charAt(n)) == -1) return (n > 0) ? s.substring(n, s.length) : s;
    }
    return("");
}
function trimRight(s){
    var whitespaces = " \t\n\r";
    for(n = s.length - 1; n  > -1; n--) { 
        if (whitespaces.indexOf(s.charAt(n)) == -1) return (n < (s.length - 1)) ? s.substring(0, n+1) : s;
    }
    return("");
}
function trim(s) {
    return ((s == null) ? "" : trimRight(trimLeft(s)));
}
function enableButton(id,state){
    showhide2(id,state);
}
function clearDefault(el){
    if(el.defaultValue==el.value) el.value = ""
}

function showPopupForm(data){        
    var dialog=dijit.byId("popupDialog");
    if (dialog!=null) {
        dialog.attr('draggable',true);
        dialog.attr('content',data);    
        dialog.show();
        if(popupName!=''){
            dialog.attr('title',popupName);
            popupName='';
        }else dialog.attr('title','PTSC MC');
        
        //excuteJS(dialog);
        dialog=null;
    }    
    return false;
}

function showPopupFormLoc(data){
    var dialog=dijit.byId("popupDialog");
    if (dialog!=null) {
        dialog.attr('draggable',true);
        //mai vinh loc
        dialog.attr("style", {width: "900px",height: "600px",overflow: "auto"});
        //
        dialog.attr('content',data);
        dialog.show();
        if(popupName!=''){
            dialog.attr('title',popupName);
            popupName='';
        }else dialog.attr('title','PTSC MC');

        //excuteJS(dialog);
        dialog=null;
    }
    return false;
}
/*
function showPopupFormEx(data){
    var dialog=dijit.byId("popupDialogEx");   
    if (dialog==null) {
        alert('create Dlg');
        dialog = new dijit.Dialog({
                title: "PTSC MC"               
            },'popupDialogEx');
    }           
    var handlePopupEx = dojo.connect(dialog,"hide",function(e) {              
         dojo.disconnect(handlePopupEx);
         handlePopupEx = null;
         var dialog=dijit.byId("popupDialogEx");
         if (dialog!=null) {
             alert("destroy");
            dialog.destroy();
            dialog = null;
         }
         
    });
    dialog.attr('draggable',false);
    dialog.attr('content',data);    
    dialog.show();
    dialog=null;
    return false;
    
}
*/
function showPopupFormById(id, data, parentDlg, myFunc){
    if (parentDlg!=null) {
        parentDlg.hide();
    }  
    var dialog=dijit.byId(id);    
    if (dialog==null)
        return false;
    
    if (parentDlg!=null) {
        var handle = dojo.connect(dialog,"hide",function(e) {        
            dojo.disconnect(handle);
            handle = null;                  
            if (myFunc!=null) {
                myFunc();
            } else {
                parentDlg.show();
            }                   
        });
    }    
    dialog.attr('draggable',false);
    dialog.attr('content',data);
    dialog.show();
    dialog=null;
    return false;
}

function hidePopupForm(id){
    if (id==null)
        id = 'popupDialog';
    
    var dialog=dijit.byId(id);
    if(dialog!=null){
        dialog.hide();
        dialog=null;
    }
    return false;
}
var tabHandle;
function displayTabs(parent,childs,func){
    var tag;
    dojo.query("."+childs).forEach(function(n){
        tag=dijit.byId(n.id);
        if(tag!=null) tag.destroyRecursive();
    });
    dojo.query("."+childs).forEach(function(n){
        new dijit.layout.ContentPane({
            title: dojo.attr(n,"title")
        }, n);
    });
    tag=dijit.byId(parent);
    if(tag!=null){
        if(tabHandle!=null) dojo.unsubscribe(tabHandle);
        tag.destroyRecursive();
    }
    var style=dojo.attr(parent, "style");
    var height;
    if(typeof(style)=='object') height='height:'+style.height;
    else height=style;
    var tc = new dijit.layout.TabContainer({
        style: height
    },parent);
    style=null;
    tag=null;
    tabHandle=dojo.subscribe(parent+'-selectChild',func);
    tc.startup();
}
function hideTab(parent, childs, child){
    parent=dijit.byId(parent);
    var tag;
    dojo.query("."+childs).forEach(function(n){
        if(n.id==child){
            tag=dijit.byId(n.id);
            if(tag!=null){
                parent.closeChild(tag);
                tag.destroyRecursive();
                tag=null;
            }
        }
    });
}
function createDateTextBox(params,id){
    var box=dijit.byId(id);
    if(box!=null){
        box.destroyRecursive();
    }
    new dijit.form.DateTextBox(params,dojo.byId(id));
}
function numbersonly(myfield, e, dec) {
    var key;
    var keychar;
    if (window.event) key = window.event.keyCode;
    else if (e) key = e.which;
    else return true;
    keychar = String.fromCharCode(key);
    // control keys
    if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) ) return true;
    // numbers
    else if ((("0123456789").indexOf(keychar) > -1)) return true;
    // decimal point jump
    else if (dec && (keychar == ".")) {
        myfield.form.elements[dec].focus();
        return false;
    } else return false;
}
function expandDiv(image,divId){
    var div = document.getElementById(divId);
    var source = image.src;
    var index = source.indexOf('icon_minus.gif');
    if(index != -1){
        image.src = source.substring(0,index) + 'icon_plus.gif';
        showhide2(divId,true);//hide
    }
    else{
        index = source.indexOf('icon_plus.gif');
        if(index != -1){
            image.src = source.substring(0,index) + 'icon_minus.gif';
            top.showhide2(divId,false);//show
        }
    }
    div=null;
    image=null;
}

/**
type 
id: parent id
divData: 
*/
function loadAttachFileSystem(ftype, pid, divData) {
    var url = 'loadAttachFileSystem.do?';
    url += 'ftype=' + ftype;
    url += '&pid=' + pid;        
    if (divData==null)
        divData = 'divAttachFileSystem';
    url += '&fdiv=' + divData
    callAjax(url,divData,null,null);
    url = null;
    divData = null;
    return false;
}

function showUploadDialog(ftype, pid, divData) {                
    var dialog = dijit.byId('dialogUploadFile');
    var handle = dojo.connect(dialog,"hide",function(e) {
        //alert("Reload AttachFileSystem " + ftype + "|div:" + divData);
        loadAttachFileSystem(ftype, pid, divData);        
        dojo.disconnect(handle);
        handle = null;
    });
    dialog.show();
    dialog=null;               
    return false;
}

function removeAttachmentFile(fid, ftype, pid, divData) {
    var url = 'removeFile.do?';
    url += 'ftype=' + ftype;
    url += '&fid=' + fid;    
    callAjax(url,null,null,function(data) {
        //alert("Reload AttachFileSystem " + ftype + "|div:" + divData);
        loadAttachFileSystem(ftype,pid,divData);
    });
    url = null;
    return false;
}
function getAttachmentFile(fid,ftype,pid) {
    var url = 'getFile.do?';
    url += 'ftype=' + ftype;
    url += '&fid=' + fid; 
    callAjax(url,null,null,null);    
    return false;
}

function hideUploadDialog() {                
    var dialog = dijit.byId('dialogUploadFile');    
    if (dialog!=null) {
        dialog.show();
        dialog=null;               
    }
    return false;
}
function formatNumberMoney(myfield, fs, fv, e, dec){
    if(numbersonly(myfield, e, dec)){
        reformatNumberMoney(myfield,fs,fv);
        var s="";
        for(i=0;i<myfield.value.length;i++){
            if(myfield.value.charAt(i)!='0') s=s+ myfield.value.charAt(i);
            else
            if(s.length>0) s=s+ myfield.value.charAt(i);
        }
         if (s=="") s='0';
        myfield.value=s;
        var ind=myfield.value.lastIndexOf('.');
        var value='';
        if(ind>-1){
            value=myfield.value.substring(ind+1);
            if(parseInt(value)==0) value='';
            myfield.value=myfield.value.substring(0,ind);
        }else ind=myfield.value.length-1;
        var str=myfield.value.substring(ind);
        n=myfield.value;
        var i=n.length-3;
        while(i>0){
            n=n.substring(0,i)+","+n.substring(i);
            i-=3;
        }
        myfield.value=n;
        if(value!='') myfield.value+='.'+value;        
    }
}
function formatNumberMoneyString(numstr, e, dec){
//    if(numbersonly(numstr, e, dec)){  
        reformatNumberMoneyString(numstr);
        var s="";
        for(i=0;i<numstr.length;i++){
            if(numstr.charAt(i)!='0') s=s+numstr.charAt(i);
            else
            if(s.length>0) s=s+numstr.charAt(i);
        }
         if (s=="") s='0';
        numstr=s;
        var ind=numstr.lastIndexOf('.');
        var value='';
        if(ind>-1){
            value=numstr.substring(ind+1);
            if(parseInt(value)==0) value='';
            numstr=numstr.substring(0,ind);
        }else ind=numstr.length-1;
        var str=numstr.substring(ind);
        n=numstr;
        var i=n.length-3;
        while(i>0){
            n=n.substring(0,i)+","+n.substring(i);
            i-=3;
        }
        numstr=n;
        if(value!='') numstr+='.'+value;
        return numstr;
//    }
}
function reformatNumberMoney(o,fs,fv){
    if(fs!=null){
        if(fs==0) o.value=(parseFloat(o.value)).toFixed(fv);
        else o.value=(parseFloat(o.value)).toPrecision(fv);
    } 
    var ind=o.value.lastIndexOf('.');
    var value='';
    if(ind>-1){
        value=o.value.substring(ind+1);
        if(parseInt(value,10)==0) value='';
        o.value=o.value.substring(0,ind);
    }else ind=o.value.length-1;
    var str=o.value.substring(ind);
    ind=o.value.indexOf(',');
    while (ind>-1){
        o.value=o.value.replace(',','');
        ind=o.value.indexOf(',');
    }
    if(value!='') o.value+='.'+value;
    return o.value;
}
function reformatNumberMoneyString(numstr){
    var ind=numstr.lastIndexOf('.');
    var value='';
    if(ind>-1){
        value=numstr.substring(ind+1);
        if(parseInt(value)==0) value='';
        numstr=numstr.substring(0,ind);
    }else ind=numstr.length-1;
    var str=numstr.substring(ind);
    ind=numstr.indexOf(',');
    while (ind>-1){
        numstr=numstr.replace(',','');
        ind=numstr.indexOf(',');
    }
    if(value!='') numstr+='.'+value;
    return numstr;
}
function checkMaxValue(myfield, value){
    reformatNumberMoney(myfield);
    if(myfield.value*1>value){
        alert('Gi\u00E1 tr\u1ECB kh\u00F4ng th\u1EC3 l\u1EDBn h\u01A1n '+value);
        myfield.value=value;
    }
}
function formatMoney(o){
    var ind=o.indexOf(',');
    while (ind>-1){
        o=o.replace(',','');
        ind=o.indexOf(',');
    }
    return eval(o);
}

function isBlank(field, strAlert) {
  strTrimmed = trim(field.value);
  if (strTrimmed.length > 0) {
      field = null;
      strTrimmed = null;
      return false;
  }
  alert(strAlert);
  field.focus();
  field = null;
  strTrimmed = null;
  return true;
}
function isGreaterThan(field, dValue) {  
  var nValue = parseInt(trim(field.value));  
  if (nValue>dValue) {
      nValue = null;
      field=null;
      return true;
  }
   nValue = null;
   field=null;
   return false;
}

function displayTip(spanid){                                
    var object = document.getElementById(spanid);
    var innerText = object.innerHTML;
    if(innerText != ''){
        var index = innerText.indexOf('[content]');                    
        var header = innerText.substring(9,index);
        var content = innerText.substring(index + 10);
        Tip(content
            ,TITLE,header
            ,TITLEALIGN,'left'
            ,CLICKCLOSE,true
            ,STICKY,true
            ,FONTCOLOR,'#000000' 
            ,BORDERCOLOR,'#CEBA94'
            ,BGCOLOR,'#FFFBEF'
            //,TITLEBGCOLOR,'#FFFBEF' 
            ,TITLEFONTCOLOR,'#000000' 
            ,SHADOW,false                        
            ,CENTERMOUSE,false
            ,OFFSETX,0
            ,PADDING,10
            ,DURATION,-1000
            ,WIDTH,340);
    }
}
function formatPositiveNumberMoney(myfield, fs, fv, e, dec){
    if(numbersonly(myfield, e, dec)){
        reformatNumberMoney(myfield,fs,fv);
        var s="";
        for(i=0;i<myfield.value.length;i++){
            if(myfield.value.charAt(i)!='0' && myfield.value.charAt(i)!='-') s=s+ myfield.value.charAt(i);
            else
            if(s.length>0) s=s+ myfield.value.charAt(i);
        }
         if (s=="") s='0';
        myfield.value=s;
        var ind=myfield.value.lastIndexOf('.');
        var value='';
        if(ind>-1){
            value=myfield.value.substring(ind+1);
            if(parseInt(value)==0) value='';
            myfield.value=myfield.value.substring(0,ind);
        }else ind=myfield.value.length-1;
        var str=myfield.value.substring(ind);
        n=myfield.value;
        var i=n.length-3;
        while(i>0){
            n=n.substring(0,i)+","+n.substring(i);
            i-=3;
        }
        myfield.value=n;
        if(value!='') myfield.value+='.'+value;        
    }
}

function readonlyNumber(e){
//Hàm dùng để ngăn người dùng nhập các ký tự khác ký tự số vào TextBox
var keypressed = null;
    if (window.event){
        keypressed = window.event.keyCode; //IE
    }else{
        keypressed = e.which; //NON-IE, Standard
    }
    if (keypressed < 48 || keypressed > 57){ 
        // //CharCode của 0 là 48 (Theo bảng mã ASCII)
        //CharCode của 9 là 57 (Theo bảng mã ASCII)
        if (keypressed == 8 || keypressed == 127 || keypressed == 0){//Phím Delete và Phím Back
            return;
        }
    return false;
    }
}

function readonlyFloat(e){
//Hàm dùng để ngăn người dùng nhập các ký tự khác ký tự số vào TextBox
var keypressed = null;
    if (window.event){
        keypressed = window.event.keyCode; //IE
    }else{
        keypressed = e.which; //NON-IE, Standard
    }
    if (keypressed < 48 || keypressed > 57){
        // //CharCode của 0 là 48 (Theo bảng mã ASCII)
        //CharCode của 9 là 57 (Theo bảng mã ASCII)
        if (keypressed == 8 || keypressed == 127 || keypressed == 0 || keypressed == 46){//Phím Delete và Phím Back va phim .
            return;
        }
    return false;
    }
}

function readonly(e){
    return false;
}