(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-22e8dd4a"],{"117c":function(e,t,i){},"159b":function(e,t,i){var l=i("da84"),s=i("fdbc"),a=i("17c2"),o=i("9112");for(var r in s){var n=l[r],c=n&&n.prototype;if(c&&c.forEach!==a)try{o(c,"forEach",a)}catch(d){c.forEach=a}}},"17c2":function(e,t,i){"use strict";var l=i("b727").forEach,s=i("a640"),a=i("ae40"),o=s("forEach"),r=a("forEach");e.exports=o&&r?[].forEach:function(e){return l(this,e,arguments.length>1?arguments[1]:void 0)}},2332:function(e,t,i){"use strict";i.r(t);var l=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-container",[i("el-main",{staticClass:"teamDoc"},[i("el-card",{staticClass:"cardFile",staticStyle:{"margin-bottom":"30px","background-color":"#F7F7F7",cursor:"pointer"},attrs:{shadow:"always"},nativeOn:{click:function(t){t.preventDefault(),e.showCreateDocDialog=!0}}},[i("div",[i("i",{staticClass:"el-icon-plus"})])]),i("table",{staticStyle:{margin:"-20px"},attrs:{cellspacing:"20"}},e._l(e.displayFiles,(function(t){return i("tr",{key:t[0].id},e._l(t,(function(t){return i("td",{key:t.id},[i("el-card",{staticClass:"cardFile",attrs:{dFile:t,shadow:"always"}},[i("el-row",{staticClass:"cardRow"},[i("el-col",{staticStyle:{margin:"0% 10%"},attrs:{span:1}},[i("el-image",{staticStyle:{width:"60px",height:"60px"},attrs:{src:e.fileIcon}})],1),i("el-col",{staticStyle:{margin:"px"},attrs:{span:13}},[i("div",{staticStyle:{"font-size":"100%",margin:"20px 0px 5px 0px"},attrs:{align:"left"}},[e._v(e._s(t.name))])]),i("el-col",{attrs:{span:1}},["已收藏"==t.collected?i("i",{staticClass:"el-icon-star-on"}):e._e(),"已收藏"!=t.collected?i("i",{staticClass:"el-icon-collection-tag",staticStyle:{color:"rgba(255,255,255,0)"}}):e._e()]),i("el-col",{attrs:{span:1,offset:1}},[i("el-dropdown",{attrs:{placement:"bottom-end"}},[i("i",{staticClass:"el-icon-more"}),i("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[i("el-dropdown-item",{nativeOn:{click:function(i){return i.preventDefault(),e.browseFile(t.id)}}},[i("i",{staticClass:"el-icon-search"}),i("span",[e._v("浏览")])]),i("el-dropdown-item",{nativeOn:{click:function(i){return i.preventDefault(),e.collectFile(t.id)}}},[i("i",{class:"已收藏"==t.collected?"el-icon-star-on":"el-icon-star-off"}),"已收藏"==t.collected?i("span",[e._v("已收藏")]):e._e(),"已收藏"!=t.collected?i("span",[e._v("收藏")]):e._e()]),i("el-dropdown-item",{attrs:{disabled:e.checkAuthority(t.owner)},nativeOn:{click:function(i){return i.preventDefault(),e.ShowAuthorizeDialog(t.id)}}},[i("i",{staticClass:"el-icon-s-tools"}),i("span",[e._v("管理权限")])]),i("el-dropdown-item",{nativeOn:{click:function(i){return i.preventDefault(),e.templateFile(t.id)}}},[i("i",{staticClass:"el-icon-s-tools",staticStyle:{color:"rgba(255,255,255,0)"}}),i("span",[e._v("存为模板")])]),i("el-dropdown-item",{attrs:{divided:"",disabled:e.checkAuthority(t.owner)},nativeOn:{click:function(i){return i.preventDefault(),e.deleteFile(t.id)}}},[i("i",{staticClass:"el-icon-star-off",staticStyle:{color:"rgba(255,255,255,0)"}}),i("span",[e._v("删除")])])],1)],1)],1)],1)],1)],1)})),0)})),0),e.files.length<1?i("div",{staticStyle:{margin:"13% auto"}},[i("div",{staticStyle:{"font-size":"500%",color:"#999999"}},[e._v("無")]),i("div",{staticStyle:{"font-size":"80%",color:"#999999"}},[e._v("没有文件")])]):e._e(),i("el-dialog",{staticStyle:{width:"1000px",margin:"auto auto"},attrs:{visible:e.showCreateDocDialog,title:"创建团队文档"},on:{"update:visible":function(t){e.showCreateDocDialog=t}}},[i("el-form",{ref:"newFileForm",attrs:{model:e.newFileForm,rules:e.rule,"label-width":"0px"}},[i("el-form-item",{attrs:{prop:"name",align:"left"}},[i("span",{staticStyle:{margin:"0px 20px"}},[e._v("文件名:")]),i("el-input",{staticStyle:{width:"300px"},attrs:{type:"text",placeholder:"文件名"},model:{value:e.newFileForm.name,callback:function(t){e.$set(e.newFileForm,"name",t)},expression:"newFileForm.name"}})],1),i("el-form-item",{attrs:{prop:"templateId",align:"left"}},[i("span",{staticStyle:{margin:"0px 27px"}},[e._v("模板:")]),i("el-select",{staticStyle:{width:"300px"},attrs:{placeholder:"选择模板"},model:{value:e.newFileForm.templateId,callback:function(t){e.$set(e.newFileForm,"templateId",t)},expression:"newFileForm.templateId"}},e._l(e.templates,(function(t){return i("el-option",{key:t.id,attrs:{label:t.name,value:t.id}},[i("span",{staticStyle:{float:"left"}},[e._v(e._s(t.name))]),i("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"10px"}},[e._v(e._s("-1"!=t.id?t.id:""))])])})),1)],1)],1),i("div",{attrs:{align:"right"}},[i("el-button",{attrs:{type:"primary",size:"medium",loading:e.submitting},nativeOn:{click:function(t){return t.preventDefault(),e.newTeamFile(t)}}},[e._v("确认创建")])],1)],1),i("el-dialog",{attrs:{visible:e.showAuthorizeDialog,title:"管理文档权限",width:"400px"},on:{"update:visible":function(t){e.showAuthorizeDialog=t}}},[i("p",{attrs:{align:"left"}},[i("span",[i("h4",[e._v("浏览:")])]),i("el-radio-group",{model:{value:e.viewAuth,callback:function(t){e.viewAuth=t},expression:"viewAuth"}},["-1"==e.selectFileTeam?i("el-radio",{attrs:{label:"0"}},[e._v("仅创建者")]):e._e(),"-1"!=e.selectFileTeam?i("el-radio",{attrs:{label:"0"}},[e._v("文档所在团队成员")]):e._e(),i("el-radio",{attrs:{label:"1"}},[e._v("所有人")])],1)],1),i("p",{attrs:{align:"left"}},[i("span",[i("h4",[e._v("编辑:")])]),i("el-radio-group",{model:{value:e.editAuth,callback:function(t){e.editAuth=t},expression:"editAuth"}},[i("el-radio",{attrs:{label:"0",disabled:e.editAuthCheck("0")}},[e._v("仅创建者")]),i("el-radio",{attrs:{label:"1",disabled:e.editAuthCheck("1")}},[e._v("文档所在团队成员")]),i("el-radio",{attrs:{label:"2",disabled:e.editAuthCheck("2")}},[e._v("所有人")])],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.authorizeFile(t)}}},[e._v("确认")])],1)])],1)],1)},s=[],a=(i("4160"),i("b0c0"),i("159b"),i("56d7")),o={data:function(){return{fileIcon:"http://qexiy12gt.hd-bkt.clouddn.com/%E6%96%87%E6%A1%A3%E5%9B%BE%E6%A0%87.png",rowWidth:3,showAuthorizeDialog:!1,showCreateDocDialog:!1,submitting:!1,viewAuth:null,editAuth:null,selectFileId:null,selectFileTeam:null,displayFiles:[],rule:{name:[{required:!0,message:"文件名不能为空,最多16个字",max:16,trigger:"blur"}],templateId:[{required:!0,message:"请选择模板",trigger:"blur"}]},templates:[{id:"-1",name:"无"}],newFileForm:{name:null,templateId:"-1"},selectTemplateId:null,files:[],userTypeInTeam:2,userId:""}},methods:{newTeamFile:function(){var e=this;this.$refs.newFileForm.validate((function(t){if(t){var i={userId:e.$store.state.userId,teamId:e.$store.state.teamId,name:e.newFileForm.name,templateId:e.newFileForm.templateId};e.submitting=!0,console.log("newFileForm"),console.log(i),Object(a["NewFile"])(i).then((function(t){1==t.data.result?(e.$message({type:"success",message:"创建成功"}),e.submitting=!1,e.showCreateDocDialog=!1,e.$router.go(0)):e.$message.error({message:"创建失败,请稍后再试"})}))}}))},editAuthCheck:function(e){if("-1"==this.selectFileTeam){if("0"==this.viewAuth&&"0"!=e)return this.editAuth="0",!0}else if("0"==this.viewAuth&&"2"==e)return"2"==this.editAuth&&(this.editAuth="1"),!0;return!1},checkAuthority:function(e){return 2==this.userTypeInTeam&&this.$store.state.userId!=e},collectFile:function(e){var t=this,i={userId:this.$store.state.userId,fileId:e};Object(a["CollectFile"])(i).then((function(i){1==i.data.result?(console.log("collectFile_Succeed: "+e),t.$message({type:"success",message:"收藏成功"}),t.$router.go(0)):(console.log("collectFile_Failed: "+e),t.$message.error({message:"收藏失败"}))}))},browseFile:function(e){this.$router.push({path:"/docBrowse",query:{docId:e}})},ShowAuthorizeDialog:function(e){for(var t=0;t<this.files.length;t++)this.files[t].id==e&&(this.viewAuth=this.files[t].view,this.editAuth=this.files[t].edit,this.selectFileTeam=this.files[t].team,this.selectFileId=this.files[t].id);this.showAuthorizeDialog=!0},authorizeFile:function(e){var t=this,i={id:this.selectFileId,view:this.viewAuth,edit:this.editAuth};Object(a["AuthorizeFile"])(i).then((function(e){1==e.data.result?(console.log("authFile_Succeed: "+i),t.$message({type:"success",message:"修改成功"}),t.$router.go(0)):(console.log("authFile_Failed: "+i),t.$message.error({message:"收藏失败"}))}))},templateFile:function(e){var t=this,i={userId:this.$store.state.userId,fileId:e};Object(a["TemplateFile"])(i).then((function(i){1==i.data.result?(console.log("templateFile_Succeed: "+e),t.$message({type:"success",message:"模板保存成功"}),t.$router.go(0)):(console.log("templateFile_Failed: "+e),t.$message.error({message:"模板保存失败"}))}))},deleteFile:function(e){var t=this,i={userId:this.$store.state.userId,fileId:e};Object(a["DeleteFile"])(i).then((function(i){1==i.data.result?(console.log("deleteFile_Succeed: "+e),t.$message({type:"success",message:"删除成功"}),t.$router.go(0)):(console.log("deleteFile_Failed: "+e),t.$message.error({message:"删除失败"}))}))}},mounted:function(){var e=this;this.userId=this.$store.state.userId,Object(a["GetTeamMember"])({teamId:this.$store.state.teamId}).then((function(t){var i=e.$store.state.userId;if(!0===t.data.result)for(var l=0;l<t.data.memberInfo.length;l++){var s=t.data.memberInfo[l].memberId,a=t.data.memberInfo[l].memberType;s===i&&(e.userTypeInTeam=parseInt(a))}})),Object(a["GetUserTemplate"])({id:this.$store.state.userId}).then((function(t){e.templates=t.data.templates,e.templates.unshift({id:"-1",name:"无"})}));var t={id:this.$store.state.userId,teamId:this.$store.state.teamId};Object(a["GetTeamFile"])(t).then((function(t){t.data.files.forEach((function(t){return"true"!=t.isDel&&e.files.push(t)})),console.log("this.files = "),console.log(e.files);for(var i=0;i<e.files.length;){e.$set(e.displayFiles,parseInt(i/e.rowWidth),[]);for(var l=0;l<e.rowWidth&&i<e.files.length;l++)e.$set(e.displayFiles[parseInt(i/e.rowWidth)],l,e.files[i]),i++}console.log("displayFiles"),console.log(e.displayFiles)}))}},r=o,n=(i("afce"),i("2877")),c=Object(n["a"])(r,l,s,!1,null,null,null);t["default"]=c.exports},4160:function(e,t,i){"use strict";var l=i("23e7"),s=i("17c2");l({target:"Array",proto:!0,forced:[].forEach!=s},{forEach:s})},a640:function(e,t,i){"use strict";var l=i("d039");e.exports=function(e,t){var i=[][e];return!!i&&l((function(){i.call(null,t||function(){throw 1},1)}))}},afce:function(e,t,i){"use strict";var l=i("117c"),s=i.n(l);s.a}}]);
//# sourceMappingURL=chunk-22e8dd4a.79ed35fe.js.map