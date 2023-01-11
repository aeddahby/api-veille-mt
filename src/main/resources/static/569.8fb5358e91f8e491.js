"use strict";(self.webpackChunkng_veille_mt=self.webpackChunkng_veille_mt||[]).push([[569],{6569:(Z,_,i)=>{i.r(_),i.d(_,{ConfigurationModule:()=>S});var g=i(6696),l=i(9136),n=i(5e3),p=i(4004),o=i(520),U=i(1082);let m=(()=>{class e{constructor(t,s){this.http=t,this.applicationConfigService=s}getBeans(){return this.http.get(this.applicationConfigService.getEndpointFor("management/configprops")).pipe((0,p.U)(t=>Object.values(Object.values(t.contexts).map(s=>s.beans).reduce((s,c)=>Object.assign(Object.assign({},s),c)))))}getPropertySources(){return this.http.get(this.applicationConfigService.getEndpointFor("management/env")).pipe((0,p.U)(t=>t.propertySources))}}return e.\u0275fac=function(t){return new(t||e)(n.LFG(o.eN),n.LFG(U.y))},e.\u0275prov=n.Yz7({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();var u=i(9808),d=i(2382),a=i(1427),h=i(1408),C=i(9444);function v(e,r){if(1&e&&(n.TgZ(0,"div",11),n._uU(1,"\n            "),n.TgZ(2,"div",12),n._uU(3),n.qZA(),n._uU(4,"\n            "),n.TgZ(5,"div",13),n._uU(6,"\n              "),n.TgZ(7,"span",14),n._uU(8),n.ALo(9,"json"),n.qZA(),n._uU(10,"\n            "),n.qZA(),n._uU(11,"\n          "),n.qZA()),2&e){const t=r.$implicit;n.xp6(3),n.Oqu(t.key),n.xp6(5),n.Oqu(n.lcZ(9,2,t.value))}}function A(e,r){if(1&e&&(n.TgZ(0,"tr"),n._uU(1,"\n        "),n.TgZ(2,"td"),n._uU(3,"\n          "),n.TgZ(4,"span"),n._uU(5),n.qZA(),n._uU(6,"\n        "),n.qZA(),n._uU(7,"\n        "),n.TgZ(8,"td"),n._uU(9,"\n          "),n.YNc(10,v,12,4,"div",10),n.ALo(11,"keyvalue"),n._uU(12,"\n        "),n.qZA(),n._uU(13,"\n      "),n.qZA()),2&e){const t=r.$implicit;n.xp6(5),n.Oqu(t.prefix),n.xp6(5),n.Q6J("ngForOf",n.lcZ(11,2,t.properties))}}function y(e,r){if(1&e&&(n.TgZ(0,"tr"),n._uU(1,"\n          "),n.TgZ(2,"td",18),n._uU(3),n.qZA(),n._uU(4,"\n          "),n.TgZ(5,"td",18),n._uU(6,"\n            "),n.TgZ(7,"span",14),n._uU(8),n.qZA(),n._uU(9,"\n          "),n.qZA(),n._uU(10,"\n        "),n.qZA()),2&e){const t=r.$implicit;n.xp6(3),n.Oqu(t.key),n.xp6(5),n.Oqu(t.value.value)}}function T(e,r){if(1&e&&(n.TgZ(0,"div"),n._uU(1,"\n    "),n.TgZ(2,"h4",15),n._uU(3,"\n      "),n.TgZ(4,"span"),n._uU(5),n.qZA(),n._uU(6,"\n    "),n.qZA(),n._uU(7,"\n\n    "),n.TgZ(8,"table",16),n._uU(9,"\n      "),n.TgZ(10,"thead"),n._uU(11,"\n        "),n.TgZ(12,"tr"),n._uU(13,"\n          "),n.TgZ(14,"th",17),n._uU(15,"Property"),n.qZA(),n._uU(16,"\n          "),n.TgZ(17,"th",8),n._uU(18,"Value"),n.qZA(),n._uU(19,"\n        "),n.qZA(),n._uU(20,"\n      "),n.qZA(),n._uU(21,"\n      "),n.TgZ(22,"tbody"),n._uU(23,"\n        "),n.YNc(24,y,11,2,"tr",9),n.ALo(25,"keyvalue"),n._uU(26,"\n      "),n.qZA(),n._uU(27,"\n    "),n.qZA(),n._uU(28,"\n  "),n.qZA()),2&e){const t=r.$implicit,s=r.index;n.xp6(2),n.Q6J("id","property-source-"+s),n.xp6(3),n.Oqu(t.name),n.xp6(3),n.uIk("aria-describedby","property-source-"+s),n.xp6(16),n.Q6J("ngForOf",n.lcZ(25,4,t.properties))}}function b(e,r){if(1&e){const t=n.EpF();n.TgZ(0,"div"),n._uU(1,"\n  "),n.TgZ(2,"h2",1),n._uU(3,"Configuration"),n.qZA(),n._uU(4,"\n\n  "),n.TgZ(5,"span"),n._uU(6,"Filter (by prefix)"),n.qZA(),n._uU(7,"\n  "),n.TgZ(8,"input",2),n.NdJ("ngModelChange",function(c){return n.CHM(t),n.oxw().beansFilter=c})("ngModelChange",function(){return n.CHM(t),n.oxw().filterAndSortBeans()}),n.qZA(),n._uU(9,"\n\n  "),n.TgZ(10,"h3",3),n._uU(11,"Spring configuration"),n.qZA(),n._uU(12,"\n\n  "),n.TgZ(13,"table",4),n._uU(14,"\n    "),n.TgZ(15,"thead"),n._uU(16,"\n      "),n.TgZ(17,"tr",5),n.NdJ("ascendingChange",function(c){return n.CHM(t),n.oxw().beansAscending=c})("sortChange",function(){return n.CHM(t),n.oxw().filterAndSortBeans()}),n._uU(18,"\n        "),n.TgZ(19,"th",6)(20,"span"),n._uU(21,"Prefix"),n.qZA(),n._uU(22," "),n._UZ(23,"fa-icon",7),n.qZA(),n._uU(24,"\n        "),n.TgZ(25,"th",8)(26,"span"),n._uU(27,"Properties"),n.qZA()(),n._uU(28,"\n      "),n.qZA(),n._uU(29,"\n    "),n.qZA(),n._uU(30,"\n    "),n.TgZ(31,"tbody"),n._uU(32,"\n      "),n.YNc(33,A,14,4,"tr",9),n._uU(34,"\n    "),n.qZA(),n._uU(35,"\n  "),n.qZA(),n._uU(36,"\n\n  "),n.YNc(37,T,29,6,"div",9),n._uU(38,"\n"),n.qZA()}if(2&e){const t=n.oxw();n.xp6(8),n.Q6J("ngModel",t.beansFilter),n.xp6(9),n.Q6J("ascending",t.beansAscending),n.xp6(16),n.Q6J("ngForOf",t.beans),n.xp6(4),n.Q6J("ngForOf",t.propertySources)}}const x={path:"",component:(()=>{class e{constructor(t){this.configurationService=t,this.beans=[],this.beansFilter="",this.beansAscending=!0,this.propertySources=[]}ngOnInit(){this.configurationService.getBeans().subscribe(t=>{this.allBeans=t,this.filterAndSortBeans()}),this.configurationService.getPropertySources().subscribe(t=>this.propertySources=t)}filterAndSortBeans(){const t=this.beansAscending?-1:1,s=this.beansAscending?1:-1;this.beans=this.allBeans.filter(c=>!this.beansFilter||c.prefix.toLowerCase().includes(this.beansFilter.toLowerCase())).sort((c,f)=>c.prefix<f.prefix?t:s)}}return e.\u0275fac=function(t){return new(t||e)(n.Y36(m))},e.\u0275cmp=n.Xpm({type:e,selectors:[["jhi-configuration"]],decls:2,vars:1,consts:[[4,"ngIf"],["id","configuration-page-heading","data-cy","configurationPageHeading"],["type","text",1,"form-control",3,"ngModel","ngModelChange"],["id","spring-configuration"],["aria-describedby","spring-configuration",1,"table","table-striped","table-bordered","table-responsive","d-table"],["jhiSort","","predicate","prefix",3,"ascending","ascendingChange","sortChange"],["jhiSortBy","prefix","scope","col",1,"w-40"],["icon","sort"],["scope","col",1,"w-60"],[4,"ngFor","ngForOf"],["class","row",4,"ngFor","ngForOf"],[1,"row"],[1,"col-md-4"],[1,"col-md-8"],[1,"float-end","bg-secondary","break"],[3,"id"],[1,"table","table-sm","table-striped","table-bordered","table-responsive","d-table"],["scope","col",1,"w-40"],[1,"break"]],template:function(t,s){1&t&&(n.YNc(0,b,39,4,"div",0),n._uU(1,"\n")),2&t&&n.Q6J("ngIf",s.allBeans)},directives:[u.O5,d.Fj,d.JJ,d.On,a.b,h.T,C.BN,u.sg],pipes:[u.Nd,u.Ts],encapsulation:2}),e})(),data:{pageTitle:"Configuration"}};let S=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=n.oAB({type:e}),e.\u0275inj=n.cJS({imports:[[l.m,g.Bz.forChild([x])]]}),e})()},1408:(Z,_,i)=>{i.d(_,{T:()=>m});var g=i(7579),l=i(2722),n=i(9444),p=i(801),o=i(5e3),U=i(1427);let m=(()=>{class u{constructor(a){this.sort=a,this.sortIcon=p.CmM,this.sortAscIcon=p.foy,this.sortDescIcon=p.u9C,this.destroy$=new g.x,a.predicateChange.pipe((0,l.R)(this.destroy$)).subscribe(()=>this.updateIconDefinition()),a.ascendingChange.pipe((0,l.R)(this.destroy$)).subscribe(()=>this.updateIconDefinition())}onClick(){this.iconComponent&&this.sort.sort(this.jhiSortBy)}ngAfterContentInit(){this.updateIconDefinition()}ngOnDestroy(){this.destroy$.next(),this.destroy$.complete()}updateIconDefinition(){if(this.iconComponent){let a=this.sortIcon;this.sort.predicate===this.jhiSortBy&&(a=this.sort.ascending?this.sortAscIcon:this.sortDescIcon),this.iconComponent.icon=a.iconName,this.iconComponent.render()}}}return u.\u0275fac=function(a){return new(a||u)(o.Y36(U.b,1))},u.\u0275dir=o.lG2({type:u,selectors:[["","jhiSortBy",""]],contentQueries:function(a,h,C){if(1&a&&o.Suo(C,n.BN,5),2&a){let v;o.iGM(v=o.CRH())&&(h.iconComponent=v.first)}},hostBindings:function(a,h){1&a&&o.NdJ("click",function(){return h.onClick()})},inputs:{jhiSortBy:"jhiSortBy"}}),u})()},1427:(Z,_,i)=>{i.d(_,{b:()=>l});var g=i(5e3);let l=(()=>{class n{constructor(){this.predicateChange=new g.vpe,this.ascendingChange=new g.vpe,this.sortChange=new g.vpe}get predicate(){return this._predicate}set predicate(o){this._predicate=o,this.predicateChange.emit(o)}get ascending(){return this._ascending}set ascending(o){this._ascending=o,this.ascendingChange.emit(o)}sort(o){this.ascending=o!==this.predicate||!this.ascending,this.predicate=o,this.predicateChange.emit(o),this.ascendingChange.emit(this.ascending),this.sortChange.emit({predicate:this.predicate,ascending:this.ascending})}}return n.\u0275fac=function(o){return new(o||n)},n.\u0275dir=g.lG2({type:n,selectors:[["","jhiSort",""]],inputs:{predicate:"predicate",ascending:"ascending"},outputs:{predicateChange:"predicateChange",ascendingChange:"ascendingChange",sortChange:"sortChange"}}),n})()}}]);