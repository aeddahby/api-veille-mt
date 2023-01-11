"use strict";(self.webpackChunkng_veille_mt=self.webpackChunkng_veille_mt||[]).push([[592],{2766:(f,c,t)=>{t.d(c,{W:()=>_,e:()=>a});class _{constructor(i,n){this.id=i,this.name=n}}function a(r){return r.id}},9334:(f,c,t)=>{t.d(c,{H:()=>v});var _=t(6037),a=t(5929),r=t(2766),i=t(5e3),n=t(520),E=t(1082);let v=(()=>{class s{constructor(e,o){this.http=e,this.applicationConfigService=o,this.resourceUrl=this.applicationConfigService.getEndpointFor("api/categories")}create(e){return this.http.post(this.resourceUrl,e,{observe:"response"})}update(e){return this.http.put(`${this.resourceUrl}/${(0,r.e)(e)}`,e,{observe:"response"})}partialUpdate(e){return this.http.patch(`${this.resourceUrl}/${(0,r.e)(e)}`,e,{observe:"response"})}find(e){return this.http.get(`${this.resourceUrl}/${e}`,{observe:"response"})}query(e){const o=(0,a.b)(e);return this.http.get(this.resourceUrl,{params:o,observe:"response"})}delete(e){return this.http.delete(`${this.resourceUrl}/${e}`,{observe:"response"})}addCategoryToCollectionIfMissing(e,...o){const u=o.filter(_.E);if(u.length>0){const p=e.map(d=>(0,r.e)(d));return[...u.filter(d=>{const h=(0,r.e)(d);return null!=h&&!p.includes(h)&&(p.push(h),!0)}),...e]}return e}}return s.\u0275fac=function(e){return new(e||s)(i.LFG(n.eN),i.LFG(E.y))},s.\u0275prov=i.Yz7({token:s,factory:s.\u0275fac,providedIn:"root"}),s})()},1040:(f,c,t)=>{t.d(c,{H:()=>a,c:()=>_});class _{constructor(i,n){this.id=i,this.name=n}}function a(r){return r.id}},6946:(f,c,t)=>{t.d(c,{P:()=>v});var _=t(6037),a=t(5929),r=t(1040),i=t(5e3),n=t(520),E=t(1082);let v=(()=>{class s{constructor(e,o){this.http=e,this.applicationConfigService=o,this.resourceUrl=this.applicationConfigService.getEndpointFor("api/direction-regionales")}create(e){return this.http.post(this.resourceUrl,e,{observe:"response"})}update(e){return this.http.put(`${this.resourceUrl}/${(0,r.H)(e)}`,e,{observe:"response"})}partialUpdate(e){return this.http.patch(`${this.resourceUrl}/${(0,r.H)(e)}`,e,{observe:"response"})}find(e){return this.http.get(`${this.resourceUrl}/${e}`,{observe:"response"})}query(e){const o=(0,a.b)(e);return this.http.get(this.resourceUrl,{params:o,observe:"response"})}delete(e){return this.http.delete(`${this.resourceUrl}/${e}`,{observe:"response"})}addDirectionRegionaleToCollectionIfMissing(e,...o){const u=o.filter(_.E);if(u.length>0){const p=e.map(d=>(0,r.H)(d));return[...u.filter(d=>{const h=(0,r.H)(d);return null!=h&&!p.includes(h)&&(p.push(h),!0)}),...e]}return e}}return s.\u0275fac=function(e){return new(e||s)(i.LFG(n.eN),i.LFG(E.y))},s.\u0275prov=i.Yz7({token:s,factory:s.\u0275fac,providedIn:"root"}),s})()},8430:(f,c,t)=>{t.d(c,{b:()=>a,r:()=>_});class _{constructor(i,n){this.id=i,this.title=n}}function a(r){return r.id}},752:(f,c,t)=>{t.d(c,{a:()=>v});var _=t(6037),a=t(5929),r=t(8430),i=t(5e3),n=t(520),E=t(1082);let v=(()=>{class s{constructor(e,o){this.http=e,this.applicationConfigService=o,this.resourceUrl=this.applicationConfigService.getEndpointFor("api/entity-ms")}create(e){return this.http.post(this.resourceUrl,e,{observe:"response"})}update(e){return this.http.put(`${this.resourceUrl}/${(0,r.b)(e)}`,e,{observe:"response"})}partialUpdate(e){return this.http.patch(`${this.resourceUrl}/${(0,r.b)(e)}`,e,{observe:"response"})}find(e){return this.http.get(`${this.resourceUrl}/${e}`,{observe:"response"})}query(e){const o=(0,a.b)(e);return this.http.get(this.resourceUrl,{params:o,observe:"response"})}delete(e){return this.http.delete(`${this.resourceUrl}/${e}`,{observe:"response"})}addEntityMToCollectionIfMissing(e,...o){const u=o.filter(_.E);if(u.length>0){const p=e.map(d=>(0,r.b)(d));return[...u.filter(d=>{const h=(0,r.b)(d);return null!=h&&!p.includes(h)&&(p.push(h),!0)}),...e]}return e}}return s.\u0275fac=function(e){return new(e||s)(i.LFG(n.eN),i.LFG(E.y))},s.\u0275prov=i.Yz7({token:s,factory:s.\u0275fac,providedIn:"root"}),s})()},6117:(f,c,t)=>{t.d(c,{c:()=>s});var _=t(9646),a=t(515),r=t(5577),i=t(7260),n=t(5e3),E=t(6554),v=t(6696);let s=(()=>{class l{constructor(o,u){this.service=o,this.router=u}resolve(o){const u=o.params.id;return u?this.service.find(u).pipe((0,r.z)(p=>p.body?(0,_.of)(p.body):(this.router.navigate(["404"]),a.E))):(0,_.of)(new i.S)}}return l.\u0275fac=function(o){return new(o||l)(n.LFG(E.w),n.LFG(v.F0))},l.\u0275prov=n.Yz7({token:l,factory:l.\u0275fac,providedIn:"root"}),l})()}}]);