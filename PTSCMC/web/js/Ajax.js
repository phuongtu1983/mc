var sessionId='';
var popupName='';
function callAjax(action,targetId,_formNode,handleFunc){
    if(action.indexOf('?')==-1) action=action+'?';
    else action=action+'&';
    action=action+"session="+sessionId+"&random="+Math.random();
    if (_formNode==null){
        dojo.xhrGet({
            url: action,
            handleAs: "text",
            load: function(data,args){
                dumpData(data,targetId,handleFunc);
            },
            error: function(error,args){
                errorHandler(error);
            }
        });
    }else{
        dojo.xhrPost({
            url:action,
            form: _formNode,
            handleAs: "text",
            load: function(data,args){
                dumpData(data,targetId,handleFunc);
            },
            error: function(error,args){
                errorHandler(error);
            }
        });
        _formNode=null;
    }
    return false;
}

function callAjaxCheckError(action,targetId,_formNode,handleFunc,divErrorMessage){
    //    if (divErrorMessage==null) {
    //        return callAjax(action,targetId,_formNode,handleFunc);
    //    }

    if(action.indexOf('?')==-1) action=action+'?';
    else action=action+'&';
    action=action+"session="+sessionId+"&random="+Math.random();
    if (_formNode==null){
        dojo.xhrGet({
            url: action,
            handleAs: "text",
            load: function(data,args){
                dumpDataCheckError(data,targetId,handleFunc,divErrorMessage);
            },
            error: function(error,args){
                errorHandler(error);
            }
        });
    }else{
        dojo.xhrPost({
            url:action,
            form: _formNode,
            handleAs: "text",
            load: function(data,args){
                dumpDataCheckError(data,targetId,handleFunc,divErrorMessage);
            },
            error: function(error,args){
                errorHandler(error);
            }
        });
        _formNode=null;
//        if (divErrorMessage!=undefined){
//            if (divErrorMessage!=null){
//                alert(divErrorMessage);
//            }
//        }
    }
    return false;
}
function dumpData(data,targetId,handleFunc){
    if(data=='login'){
        window.location='loginPage.do';return;
    }
    if(handleFunc==null){
        if(targetId!=null) setAjaxData(data,targetId);
    }
    else handleFunc(data);
}
function dumpDataCheckError(data,targetId,handleFunc,divErrorMessage){
    if(data=='login'){
        window.location='loginPage.do';return;
    }
    var id;
    if(divErrorMessage==null) id='errorMessageDiv';
    else id=divErrorMessage;
    var index=data.indexOf('error:');
    if(index==0){
        errorDiv=document.getElementById(id);
        if(errorDiv==null) {
            //alert(data.substring(6));
            alert(data.substring(6).replace("null",""));
        } else {
            //alert(data.substring(6).replace("null",""))
            errorDiv.innerHTML="<fieldset style='color:red'><legend class='lbl10b' style='color:red'>C\u00F3 l\u1ED7i x\u1EA3y ra</legend>" + data.substring(6).replace("null","")+"</fieldset><br>";
            errorDiv=null;
        }
        return;
    }
    if(handleFunc==null){
        if(targetId!=null) setAjaxData(data,targetId);
    }
    else handleFunc(data);
}

//goi thuc thi javascript trong trang web tra ve
function excuteJS(parentTag)
{
    var x = parentTag.getElementsByTagName("script");
    if (x!=null) {
        for(var i=0;i<x.length;i++) {
            eval(x[i].text);
        }
        x = null;
    }
    return false;
}

function setAjaxData(data,targetId){
    var displayDiv=document.getElementById(targetId);
    if(displayDiv!=null){
        displayDiv.innerHTML='';
        displayDiv.innerHTML=data;
        //goi thuc thi javascript trong trang web tra ve
        var x = displayDiv.getElementsByTagName("script");
        if (x!=null) {
            for(var i=0;i<x.length;i++) {
                eval(x[i].text);
            }
            x = null;
        }
        displayDiv=null;
    }
}
function errorHandler(error ){
    alert('Something went horribly wrong ...\n' + error.message);
}
function callServer(action){
    if(action.indexOf('?')==-1) action=action+'?';
    else action=action+'&';
    action=action+"session="+sessionId+"&random="+Math.random();
    window.location=action;
    return false;
}

function callAjaxEx(actionParent, divParent, actionChild, divChild, parameters)
{
    if (actionParent==null) {
        return false;
    }
    if (actionChild==null) {
        if (parameters!=null) {
            if(actionParent.indexOf('?')==-1) actionParent=actionParent+'?';
            else actionParent=actionParent+'&';
            actionParent += parameters;
        }
    }
    callAjaxCheckError(actionParent,null,null,function(data) {
        setAjaxData(data,divParent);
        if (actionChild==null) {
            return false;
        }
        var url = actionChild;
        var i=0;
        var temp = 0;
        if (parameters!=null) {
            for (i=actionChild.length;i>0;i--)
            if (actionChild.charAt(i)=='?') temp = 1;
            if(temp==0) url=url+'?';
            else url=url+'&';
            url += parameters;
        }
        callAjax(url,divChild,null,null);
        url= null;
    });
    return false;
}

function callAjaxWithIFrame(action,targetId,_formNode,handleFunc){
    if(action.indexOf('?')==-1) action=action+'?';
    else action=action+'&';
    action=action+"session="+sessionId+"&random="+Math.random();
    if (_formNode==null){
        dojo.io.iframe.send({
            url: action,
            handleAs: "text",
            load: function(data,args){
                dumpData(data,targetId,handleFunc);
            },
            error: function(error,args){
                errorHandler(error);
            }
        });
    }else{
        dojo.io.iframe.send({
            url:action,
            form: _formNode,
            handleAs: "text",
            load: function(data,args){
                dumpData(data,targetId,handleFunc);
            },
            error: function(error,args){
                errorHandler(error);
            }
        });
        _formNode=null;
    }
    return false;
}

function callAjaxExChild(actionChild, divChild, parameters)
{
    if (actionChild==null) {
        return false;
    }
    var url = actionChild;
    if (parameters!=null) {
        var i=0;
        var temp = 0;
        if (parameters!=null) {
            for (i=actionChild.length;i>0;i--)
            if (actionChild.charAt(i)=='?') temp = 1;
            if(temp==0) url=url+'?';
            else url=url+'&';
            url += parameters;
        }
    }
    callAjax(url,divChild,null,null);
    url= null;
    return false;
}
function callAjaxExChild(actionChild, divChild, parameters, handleFunc)
{
    if (actionChild==null) {
        return false;
    }
    var url = actionChild;
    if (parameters!=null) {
        var i=0;
        var temp = 0;
        if (parameters!=null) {
            for (i=actionChild.length;i>0;i--)
            if (actionChild.charAt(i)=='?') temp = 1;
            if(temp==0) url=url+'?';
            else url=url+'&';
            url += parameters;
        }
    }
    callAjax(url,divChild,null,handleFunc);
    url= null;
    return false;
}