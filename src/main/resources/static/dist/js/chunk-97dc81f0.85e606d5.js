(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-97dc81f0"],{"56c8":function(t,e,i){"use strict";i.r(e);var o=function(){var t=this,e=t.$createElement,i=t._self._c||e;return t.showContent?i("el-main",[i("el-row",[i("el-col",{attrs:{span:8,offset:6}},[i("div",{staticClass:"tt"},[t._v(t._s(t.doc.docName))])]),i("el-col",{attrs:{span:5,offset:0}},[!0===t.auth.edit?i("el-button",{attrs:{type:"success",icon:"el-icon-edit-outline",circle:"",plain:""},on:{click:t.toEdit}}):t._e(),i("el-button",{attrs:{type:"warning",icon:"el-icon-share",circle:"",plain:""},on:{click:t.share}}),!0===t.auth.admin?i("el-button",{attrs:{type:"info",icon:"el-icon-setting",circle:"",plain:""},on:{click:t.show}}):t._e()],1)],1),i("p"),i("el-dialog",{attrs:{modal:!1,visible:t.showSettings,title:"管理文档权限",width:"400px"},on:{"update:visible":function(e){t.showSettings=e}}},[i("p",{attrs:{align:"left"}},[i("span",[i("h4",[t._v("编辑:")])]),t.showSettings?i("el-radio-group",{on:{change:t.updateSettings},model:{value:t.setting.edit,callback:function(e){t.$set(t.setting,"edit",e)},expression:"setting.edit"}},[i("el-radio",{attrs:{label:"0"}},[t._v("仅创建者")]),"-1"!=this.teamId?i("el-radio",{attrs:{label:"1"}},[t._v("仅团队内")]):t._e(),i("el-radio",{attrs:{label:"2"}},[t._v("所有人")])],1):t._e()],1),i("p",{attrs:{align:"left"}},[i("span",[i("h4",[t._v("浏览:")])]),t.showSettings?i("el-radio-group",{model:{value:t.setting.view,callback:function(e){t.$set(t.setting,"view",e)},expression:"setting.view"}},[i("el-radio",{attrs:{disabled:"2"===this.setting.edit,label:"0"}},[t._v(t._s("-1"!=t.teamId?"仅团队内":"仅创建者"))]),i("el-radio",{attrs:{label:"1"}},[t._v("所有人")])],1):t._e()],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"success",circle:"",plain:"",icon:"el-icon-check",disabled:""===this.setting.edit||""===this.setting.view},on:{click:t.commitAuth}})],1)]),i("el-row",{staticStyle:{width:"75%",margin:"auto auto"}},[i("el-col",{attrs:{span:3,offset:4}},[i("div",{staticClass:"info info-left"},[t._v("创建者："+t._s(t.doc.ownerName))])]),i("el-col",{attrs:{span:5}},[i("div",{staticClass:"info info-left"},[t._v("创建时间："+t._s(t.doc.createTime))])]),i("el-col",{attrs:{span:5}},[i("div",{staticClass:"info info-right"},[t._v("上次修改时间："+t._s(t.doc.updateTime))])]),i("el-col",{attrs:{span:3,offset:0}},[i("div",{staticClass:"info info-right"},[t._v("历史修改次数："+t._s(t.doc.count))])])],1),i("div",{staticStyle:{width:"50%",margin:"auto auto"}},[i("el-divider")],1),!0===this.showContent?i("el-row",[i("el-col",{attrs:{span:12,offset:6}},[i("mavon-editor",{staticStyle:{"min-height":"800px"},attrs:{value:t.doc.content,subfield:!1,defaultOpen:"preview",toolbarsFlag:!1,editable:!1,scrollStyle:!0,ishljs:!0,previewBackground:"#ffffff"}})],1)],1):t._e(),i("p",{staticStyle:{width:"50%",margin:"auto auto"}},[i("el-divider")],1),t._l(t.comment,(function(e){return i("p",{key:e},[i("el-row",[i("el-col",{staticClass:"comment-info",attrs:{span:1,offset:6}},[i("el-link",{attrs:{underline:!1,href:"http://60.205.189.66:8080/userInfo?userId="+e.id.toString()}},[i("el-avatar",{attrs:{src:e.avatar,size:30,fit:"fill"}},[t._v(t._s(e.name))])],1)],1),i("el-col",{staticClass:"comment-info",attrs:{span:6}},[i("el-row",[i("el-link",{staticClass:"comment-name",attrs:{underline:!1,href:"http://60.205.189.66:8080/userInfo?userId="+e.id.toString()}},[i("div",[t._v(t._s(e.name))])])],1),i("el-row",[i("div",{staticClass:"comment-name",staticStyle:{"font-size":"60%",color:"#888888"}},[t._v(t._s(e.time))])])],1)],1),i("el-row",[i("el-col",{attrs:{span:12,offset:6}},[i("div",[i("mavon-editor",{staticStyle:{"min-height":"30px","box-shadow":"0 0px 0px 0 rgba(0, 0, 0, 0.1)"},attrs:{value:e.content,subfield:!1,defaultOpen:"preview",toolbarsFlag:!1,editable:!1,scrollStyle:!0,ishljs:!0,previewBackground:"#F5F5F5"}})],1)])],1)],1)})),i("el-row",[i("el-col",{staticClass:"comment-info",attrs:{span:1,offset:6}},[i("el-avatar",{attrs:{src:this.$store.state.avatar,size:30,fit:"fill"}},[t._v(t._s(this.$store.state.username))])],1),i("el-col",{staticClass:"comment-info",attrs:{span:2}},[i("el-row",[i("div",{staticClass:"comment-name"},[t._v(t._s(this.$store.state.username))])]),i("el-row",[i("div",{staticClass:"comment-name"})])],1)],1),i("el-row",[i("el-col",{attrs:{span:12,offset:6}},[i("div",[i("mavon-editor",{ref:"editor",staticStyle:{"min-height":"250px","border-width":"1px","border-color":"#E6E6E6","border-style":"solid","box-shadow":"0 0 0 0 rgba(0, 0, 0, 0.1)"},attrs:{toolbars:t.toolbars,subfield:!1,defaultOpen:"edit",toolbarsFlag:!0,editable:!0,scrollStyle:!0,ishljs:!0,autofocus:!1,placeholder:"说两句呗",previewBackground:"#ffffff"},on:{imgAdd:t.imgAdd,change:t.change}})],1)])],1),i("p"),i("el-row",[i("el-col",{staticClass:"info-right",attrs:{span:14,offset:6}},[i("el-button",{attrs:{type:"success",plain:""},on:{click:t.commitComment}},[t._v("发表评论")])],1)],1)],2):t._e()},s=[],n=(i("b0c0"),i("d3b7"),i("25f0"),i("56d7")),a=i("1105"),r=i("381a"),l=i.n(r),c=i("bc3a"),d=i.n(c),u={data:function(){return{teamId:"1",doc:{ownerName:"",content:"",count:"",createTime:"",updateTime:"",docName:""},auth:{view:!1,admin:!1,edit:!1,lock:!1},showContent:!1,comment:[],newComment:"",showSettings:!1,setting:{edit:"",view:"",ctl1:!0,ctl2:!0,te:"",tv:""},toolbars:{bold:!0,italic:!0,header:!0,underline:!0,strikethrough:!0,mark:!0,superscript:!0,subscript:!0,quote:!0,ol:!0,ul:!0,link:!0,imagelink:!1,code:!0,table:!0,fullscreen:!0,readmodel:!0,htmlcode:!1,help:!1,undo:!0,redo:!0,trash:!1,save:!1,navigation:!0,alignleft:!0,aligncenter:!0,alignright:!0,subfield:!1,preview:!0}}},methods:{disableSubmit:function(){return""===this.setting.edit||""===this.setting.view},share:function(){this.$alert("http://60.205.189.66:8080/docBrowse?docId="+this.$route.query.docId,"复制下面的链接来分享吧")},toEdit:function(){this.$router.push({path:"/docEdit",query:{docId:this.$route.query.docId}})},beforeUpload:function(t){var e="image/jpeg"===t.type||"image/jpg"===t.type||"image/png"===t.type,i=t.size/1024/1024<5;return e||this.$message.error("上传图片必须是 jpeg/jpg/png 格式！"),i||this.$message.error("上传图片大小不能超过 5MB！"),e&&i},imgAdd:function(t,e){var i=this;if(this.beforeUpload(e)){var o,s={},n="domaint",r="K96MCAU7eCnSWz4XUbxIBe9Q9PUm_gBHfacmsAEf",c="g0eagx-yjztmAo0iVi-Nj8QrsCRGrKhdGKIjpVr9",u=159984e4;s.scope=n,s.deadline=u,o=Object(a["a"])(r,c,s);var m=this.$refs.editor,h=new FormData;h.append("file",e),h.append("token",o),h.append("key",l()(16)),d.a.post(this.actionPath,h).then((function(e){m.$img2Url(t,i.photoUrl+e.data.key)}))}},change:function(t,e){this.newComment=t},commitAuth:function(){Object(n["AuthorizeFile"])({id:this.$route.query.docId,edit:this.setting.edit,view:this.setting.view}),this.$router.go(0)},commitComment:function(){console.log("[commitComment]"),console.log("this.newComment:"+this.newComment),Object(n["CommitComment"])({id:this.$store.state.userId,did:this.$route.query.docId,content:this.newComment}),this.$router.go(0)},show:function(){console.log("[show]"),this.showSettings=!this.showSettings,!0===this.showSettings&&(this.setting.edit=this.setting.te,this.setting.view=this.setting.tv),console.log(this.showSettings)},updateSettings:function(t){"2"===t&&(this.setting.view="1")}},mounted:function(){var t=this;Object(n["GetAuth"])({id:this.$store.state.userId.toString(),did:this.$route.query.docId.toString()}).then((function(e){!1!==e.data.result&&!1!==e.data.view?(t.auth.admin=e.data.admin,t.auth.edit=e.data.edit,!0===e.data.lock&&t.$message.warning("此文件正在被他人编辑中，您看到的可能并不是最新内容"),Object(n["GetComment"])({did:t.$route.query.docId.toString()}).then((function(e){t.comment=e.data.comment})),Object(n["GetFile"])({id:t.$route.query.docId.toString()}).then((function(e){var i=e.data;!1!==i.result?(t.setting.te=e.data.edit,t.setting.tv=e.data.view,t.setting.edit=t.setting.te,t.setting.view=t.setting.tv,t.teamId=e.data.tid.toString(),Object(n["GetUserInfo"])({id:i.owner.toString()}).then((function(e){t.doc.ownerName=e.data.username})),t.doc.createTime=i.createTime,t.doc.updateTime=i.updateTime,t.doc.content=i.content,t.doc.docName=i.name,t.doc.count=i.count,console.log(t.doc),t.showContent=!0):t.$message.error("请求文档失败！请稍后再试！")}))):t.$alert("文档不存在或无权查看","出错啦",{confirmButtonText:"确定",callback:function(e){t.$router.go(-1)}})}))}},m=u,h=(i("d474"),i("2877")),f=Object(h["a"])(m,o,s,!1,null,null,null);e["default"]=f.exports},d474:function(t,e,i){"use strict";var o=i("dfaa"),s=i.n(o);s.a},dfaa:function(t,e,i){}}]);
//# sourceMappingURL=chunk-97dc81f0.85e606d5.js.map