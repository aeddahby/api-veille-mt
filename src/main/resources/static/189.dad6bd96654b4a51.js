"use strict";(self.webpackChunkng_veille_mt=self.webpackChunkng_veille_mt||[]).push([[189],{5189:(y,s,l)=>{l.r(s),l.d(s,{HealthModule:()=>H});var h=l(6696),r=l(9136),t=l(5e3),u=l(3357),c=l(9808);function d(n,o){if(1&n&&(t.TgZ(0,"h4",8),t._uU(1,"\n    "),t.TgZ(2,"span",9),t._uU(3),t.qZA(),t._uU(4,"\n  "),t.qZA()),2&n){const e=t.oxw();t.xp6(3),t.Oqu(e.health.key)}}function _(n,o){if(1&n&&(t.TgZ(0,"tr"),t._uU(1,"\n            "),t.TgZ(2,"td",14),t._uU(3),t.qZA(),t._uU(4,"\n            "),t.TgZ(5,"td",14),t._uU(6),t.qZA(),t._uU(7,"\n          "),t.qZA()),2&n){const e=o.$implicit,a=t.oxw(2);t.xp6(3),t.Oqu(e.key),t.xp6(3),t.Oqu(a.readableValue(e.value))}}function p(n,o){if(1&n&&(t.TgZ(0,"div"),t._uU(1,"\n    "),t.TgZ(2,"h5"),t._uU(3,"Properties"),t.qZA(),t._uU(4,"\n\n    "),t.TgZ(5,"div",10),t._uU(6,"\n      "),t.TgZ(7,"table",11),t._uU(8,"\n        "),t.TgZ(9,"thead"),t._uU(10,"\n          "),t.TgZ(11,"tr"),t._uU(12,"\n            "),t.TgZ(13,"th",12),t._uU(14,"Name"),t.qZA(),t._uU(15,"\n            "),t.TgZ(16,"th",12),t._uU(17,"Value"),t.qZA(),t._uU(18,"\n          "),t.qZA(),t._uU(19,"\n        "),t.qZA(),t._uU(20,"\n        "),t.TgZ(21,"tbody"),t._uU(22,"\n          "),t.YNc(23,_,8,2,"tr",13),t.ALo(24,"keyvalue"),t._uU(25,"\n        "),t.qZA(),t._uU(26,"\n      "),t.qZA(),t._uU(27,"\n    "),t.qZA(),t._uU(28,"\n  "),t.qZA()),2&n){const e=t.oxw();t.xp6(23),t.Q6J("ngForOf",t.lcZ(24,1,e.health.value.details))}}let g=(()=>{class n{constructor(e){this.activeModal=e}readableValue(e){var a;if("diskSpace"===(null===(a=this.health)||void 0===a?void 0:a.key)){const i=e/1073741824;return i>1?`${i.toFixed(2)} GB`:`${(e/1048576).toFixed(2)} MB`}return"object"==typeof e?JSON.stringify(e):String(e)}dismiss(){this.activeModal.dismiss()}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(u.Kz))},n.\u0275cmp=t.Xpm({type:n,selectors:[["jhi-health-modal"]],decls:22,vars:2,consts:[[1,"modal-header"],["class","modal-title","id","showHealthLabel",4,"ngIf"],["aria-label","Close","data-dismiss","modal","type","button",1,"btn-close",3,"click"],["aria-hidden","true"],[1,"modal-body","pad"],[4,"ngIf"],[1,"modal-footer"],["data-dismiss","modal","type","button",1,"btn","btn-secondary","float-start",3,"click"],["id","showHealthLabel",1,"modal-title"],[1,"text-capitalize"],[1,"table-responsive"],["aria-describedby","showHealthLabel",1,"table","table-striped"],["scope","col",1,"text-start"],[4,"ngFor","ngForOf"],[1,"text-start"]],template:function(e,a){1&e&&(t.TgZ(0,"div",0),t._uU(1,"\n  "),t.YNc(2,d,5,1,"h4",1),t._uU(3,"\n\n  "),t.TgZ(4,"button",2),t.NdJ("click",function(){return a.dismiss()}),t._uU(5,"\n    "),t.TgZ(6,"span",3),t._uU(7,"\xd7"),t.qZA(),t._uU(8,"\n  "),t.qZA(),t._uU(9,"\n"),t.qZA(),t._uU(10,"\n\n"),t.TgZ(11,"div",4),t._uU(12,"\n  "),t.YNc(13,p,29,3,"div",5),t._uU(14,"\n"),t.qZA(),t._uU(15,"\n\n"),t.TgZ(16,"div",6),t._uU(17,"\n  "),t.TgZ(18,"button",7),t.NdJ("click",function(){return a.dismiss()}),t._uU(19,"Done"),t.qZA(),t._uU(20,"\n"),t.qZA(),t._uU(21,"\n")),2&e&&(t.xp6(2),t.Q6J("ngIf",a.health),t.xp6(11),t.Q6J("ngIf",a.health))},directives:[c.O5,c.sg],pipes:[c.Nd],encapsulation:2}),n})();var U=l(520),Z=l(1082);let m=(()=>{class n{constructor(e,a){this.http=e,this.applicationConfigService=a}checkHealth(){return this.http.get(this.applicationConfigService.getEndpointFor("management/health"))}}return n.\u0275fac=function(e){return new(e||n)(t.LFG(U.eN),t.LFG(Z.y))},n.\u0275prov=t.Yz7({token:n,factory:n.\u0275fac,providedIn:"root"}),n})();var f=l(9444);function T(n,o){if(1&n){const e=t.EpF();t.TgZ(0,"a",13),t.NdJ("click",function(){t.CHM(e);const i=t.oxw().$implicit;return t.oxw(2).showHealth({key:i.key,value:i.value})}),t._uU(1,"\n              "),t._UZ(2,"fa-icon",14),t._uU(3,"\n            "),t.qZA()}}function v(n,o){if(1&n&&(t.TgZ(0,"tr"),t._uU(1,"\n          "),t.TgZ(2,"td"),t._uU(3,"\n            "),t.TgZ(4,"span",9),t._uU(5),t.qZA(),t._uU(6,"\n          "),t.qZA(),t._uU(7,"\n          "),t.TgZ(8,"td",10),t._uU(9,"\n            "),t.TgZ(10,"span",11),t._uU(11),t.qZA(),t._uU(12,"\n          "),t.qZA(),t._uU(13,"\n          "),t.TgZ(14,"td",10),t._uU(15,"\n            "),t.YNc(16,T,4,0,"a",12),t._uU(17,"\n          "),t.qZA(),t._uU(18,"\n        "),t.qZA()),2&n){const e=o.$implicit,a=t.oxw(2);t.xp6(5),t.Oqu(e.key),t.xp6(5),t.Q6J("ngClass",a.getBadgeClass(e.value.status)),t.xp6(1),t.hij("\n              ",e.value.status,"\n            "),t.xp6(5),t.Q6J("ngIf",e.value.details)}}function A(n,o){if(1&n&&(t.TgZ(0,"tbody"),t._uU(1,"\n        "),t.YNc(2,v,19,4,"tr",8),t.ALo(3,"keyvalue"),t._uU(4,"\n      "),t.qZA()),2&n){const e=t.oxw();t.xp6(2),t.Q6J("ngForOf",t.lcZ(3,1,e.health.components))}}const b={path:"",component:(()=>{class n{constructor(e,a){this.modalService=e,this.healthService=a}ngOnInit(){this.refresh()}getBadgeClass(e){return"UP"===e?"bg-success":"bg-danger"}refresh(){this.healthService.checkHealth().subscribe({next:e=>this.health=e,error:e=>{503===e.status&&(this.health=e.error)}})}showHealth(e){this.modalService.open(g).componentInstance.health=e}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(u.FF),t.Y36(m))},n.\u0275cmp=t.Xpm({type:n,selectors:[["jhi-health"]],decls:38,vars:1,consts:[["id","health-page-heading","data-cy","healthPageHeading"],[1,"btn","btn-primary","float-end",3,"click"],["icon","sync"],[1,"table-responsive"],["id","healthCheck","aria-describedby","health-page-heading",1,"table","table-striped"],["scope","col"],["scope","col",1,"text-center"],[4,"ngIf"],[4,"ngFor","ngForOf"],[1,"text-capitalize"],[1,"text-center"],[1,"badge",3,"ngClass"],["class","hand",3,"click",4,"ngIf"],[1,"hand",3,"click"],["icon","eye"]],template:function(e,a){1&e&&(t.TgZ(0,"div"),t._uU(1,"\n  "),t.TgZ(2,"h2"),t._uU(3,"\n    "),t.TgZ(4,"span",0),t._uU(5,"Health Checks"),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"button",1),t.NdJ("click",function(){return a.refresh()}),t._UZ(8,"fa-icon",2),t._uU(9," "),t.TgZ(10,"span"),t._uU(11,"Refresh"),t.qZA()(),t._uU(12,"\n  "),t.qZA(),t._uU(13,"\n\n  "),t.TgZ(14,"div",3),t._uU(15,"\n    "),t.TgZ(16,"table",4),t._uU(17,"\n      "),t.TgZ(18,"thead"),t._uU(19,"\n        "),t.TgZ(20,"tr"),t._uU(21,"\n          "),t.TgZ(22,"th",5),t._uU(23,"Service Name"),t.qZA(),t._uU(24,"\n          "),t.TgZ(25,"th",6),t._uU(26,"Status"),t.qZA(),t._uU(27,"\n          "),t.TgZ(28,"th",6),t._uU(29,"Details"),t.qZA(),t._uU(30,"\n        "),t.qZA(),t._uU(31,"\n      "),t.qZA(),t._uU(32,"\n      "),t.YNc(33,A,5,3,"tbody",7),t._uU(34,"\n    "),t.qZA(),t._uU(35,"\n  "),t.qZA(),t._uU(36,"\n"),t.qZA(),t._uU(37,"\n")),2&e&&(t.xp6(33),t.Q6J("ngIf",a.health))},directives:[f.BN,c.O5,c.sg,c.mk],pipes:[c.Nd],encapsulation:2}),n})(),data:{pageTitle:"Health Checks"}};let H=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[[r.m,h.Bz.forChild([b])]]}),n})()}}]);