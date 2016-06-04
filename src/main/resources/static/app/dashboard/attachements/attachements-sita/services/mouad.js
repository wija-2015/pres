(function () {
'use strict';

angular.module('app.page')
                  .controller('tests',['logger','mo','$scope','$http',testfunction])
                  ;


function testfunction(logger,mo,$scope,$http) {
var vervalide='Bien';
var valider=0;
        $scope.testfucntionsend = function (itemdata) {
var data = $scope.itemdata;
alert('hello');
console.log("mouad");
if(valider==1 &&vervalide=='Bien') {
                            $http.post("http://localhost:8090/mouad", data, {

headers: {
'Content-Type': 'application/json; charset=utf-8'
}
                                      }
                            ).success(function (dat) {
alert(dat.msg)

console.log(dat.msg);
                            });
                  }else if(valider==0) {
alert("Veuillez valider le fichier")
                  }else if(vervalide=='No'){
alert("Veuillez verifier les valeur d'excel")
                  }
        };
		
              $scope.verificationid = function (itemdata) {
var data = $scope.itemdata;
alert('hello');
console.log("mouad");

                                  $http.post("http://localhost:8090/verification", data, {

headers: {
'Content-Type': 'application/json; charset=utf-8'
}
                                            }
                                  ).success(function (dat) {
alert(dat)
if(dat.msg[0]=='Bien'){
                                      logger.logSuccess('Bien');
vervalide='Bien'

}else{
for(var i=0;i<dat.msg.length;i++) {
logger.logError(dat.msg[i])
                                                      }
vervalide='No'
}



console.log(dat.msg);
                                  });

              };


/*    $scope.td={
                'id':0,
                  'designation':'veuillez choisir'
              };*/
var getdata=mo.fetchdataer();
getdata.then(
// OnSuccess function
function (answer) {
            $scope.tsad=answer;
                        },
// OnFailure function
function (reason) {
alert('pas de donnée');
                        });
						
var askFetchAllCompany = mo.fetchAllCompagnie();
askFetchAllCompany.then(
// OnSuccess function
function (answer) {
               $scope.ts = answer;

               },
// OnFailure function
function (reason) {
alert('pas de donnée');
               });
var datarem;
              $scope.etatChanged = function (itemdata) {


var select =  $scope.itemdata;

            $http.post("http://localhost:8090/select", select, {

headers: {
'Content-Type': 'application/json; charset=utf-8'
}
                }
            ).success(function (dat) {
                    $scope.shematable=dat;

console.log(dat);
            });
        };
                        $scope.etatChangedtl = function (itemdata) {


var selectlie =  $scope.itemdata;

                                  $http.post("http://localhost:8090//selectidtl", selectlie, {

headers: {
'Content-Type': 'application/json; charset=utf-8'
}
                                            }
                                  ).success(function (dat) {
                                            $scope.tablelshema=dat;

console.log(dat);
                                  });
                        },
                        $scope.uploadFile = function(){
var file = $scope.myFile;
console.log('file is ' );
console.dir(file);
var uploadUrl = "http://localhost:8090/sig";
var fetch= mo.uploadFileToUrl(file, uploadUrl);
valider=1;
fetch.then(
function (answer) {
                                                      $scope.itemheader = answer;

                                            },
// OnFailure function
function (reason) {
alert('pas de donnée');
                                            });



                        };
              $scope.checkAll=function (check) {
if(check=="no"){
alert('No');
                        }else{
alert('YEs');
                        }
              };


    };

    })();


<body  >
<div class="page" ng-controller="tests as mora">

<section  class="panel panel-default">
<div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Blank Page</strong></div>
<div class="panel-body">
<p>Formulaire</p>
<div class="panel-body">
<div class="col-sm-4">Table existe</div>
<div class="col-sm-8">
<label class="ui-switch"><input type="checkbox" ng-init="itemdata.etable=false"  ng-model="itemdata.etable" checked><i></i></label>
<span class="space"></span>

</div>
</div>

<div class="panel-body">
<div class="col-sm-4">Lié à une table</div>
<div class="col-sm-8">
<label class="ui-switch"><input ng-init="itemdata.tablel=false" type="checkbox" ng-model="itemdata.tablel" checked><i></i></label>
<span class="space"></span>

</div>
</div>



<div class="panel-body">
<div class="col-sm-4">Fichier EXCEL</div>
<div class="col-sm-8">
<input type="file" file-model="myFile" title="importer le fichier" data-ui-file-upload class="btn-primary">
<div class="space"></div>

</div>
</div>

<div class="panel-body">
<div class="col-sm-4"></div>
<div class="col-sm-8">
<button type="button" ng-click="uploadFile()"  class="btn btn-w-md btn-gap-v btn-primary"> Valider le fichier  </button>
<div class="space"></div>
</div>
</div>
<div ng-hide="itemdata.etable" class="panel-body">
<div class="col-sm-4">Nom du table</div>
<div class="col-sm-8">
<input ng-model="itemdata.username" name="itemdata.username" type="text" class="form-control input-primary">
</div>
</div>

<div ng-show="itemdata.etable" class="panel-body"  >
<div class="col-sm-4">Choix table:</div>
<span class="ui-select">
<select data-ng-model="itemdata.id"
ng-change="etatChanged(itemdata.id)" ng-options="s.id as s.nom_canva for s in ts"  class="col-sm-8" >
<option value=''>Select</option>
</select>
</span>
</div>
<div ng-show="itemdata.tablel" class="panel-body"  >
<div class="col-sm-4">Choix table lié:</div>
<span class="ui-select">
<select data-ng-model="itemdata.idtablel" ng-change="etatChangedtl(itemdata.id)"
ng-options="s.id as s.nom_canva for s in tsad"  class="col-sm-8" >
<option value=''>Selecet</option>
</select>
</span>
</div>
<div ng-show="itemdata.tablel && itemdata.etable " class="panel-body"  >
<div class="col-sm-4">Choix schema table:</div>
<span class="ui-select">
<select data-ng-model="itemdata.schemate"
ng-options="s as s for s in shematable"  class="col-sm-8" >
<option value=''>Selecet</option>
</select>
</span>
</div>
<div ng-show="itemdata.tablel" class="panel-body"  >
<div class="col-sm-4">Choix schema table lié:</div>
<span class="ui-select">
<select data-ng-model="itemdata.shematl"
ng-options="s as s for s in tablelshema"  class="col-sm-8" >
<option value=''>Selecet</option>
</select>
</span>
</div>
<div ng-show="itemdata.tablel && !itemdata.etable"   class="panel-body"  >
<div class="col-sm-4">Choix schema Excel:</div>
<span class="ui-select">
<select data-ng-model="itemdata.shemaexcel"
ng-options="s as s for s in itemheader"  class="col-sm-8" >
<option value=''>Selecet</option>
</select>
</span>
</div>
<div ng-show="itemdata.tablel" class="panel-body">
<div class="col-sm-4"></div>
<div class="col-sm-8"  >
<button  ng-click="verificationid(itemdata) " class="btn btn-w-md btn-gap-v btn-line-primary">Verification</button>
<div class="space"></div>
</div>
</div>
<div class="panel-body">
<div class="col-sm-4"></div>
<div class="col-sm-8"  >
<button  ng-click="testfucntionsend(itemdata) " class="btn btn-w-md btn-gap-v btn-line-primary">Valider</button>
<div class="space"></div>
</div>
</div>


</div>
</section>
</div>
<script src="app/page/page.controller.js"></script>
</body>
(function () {
'use strict';
angular.module('app.page')
        .factory('logger', logger)
        .service('mo', ['$http','$q',functionservice]);
//  app.factory('mo', ["$http", "$q", function($http, $q){


function functionservice($http,$q){

this.uploadFileToUrl = function(file, uploadUrl){
var fd = new FormData();
var deferObjectCompany;
fd.append('file', file);
var promise=  $http.post(uploadUrl, fd, {
method: 'POST',
transformRequest: angular.identity,
headers: {'Content-Type': undefined},

                              });

deferObjectCompany = deferObjectCompany || $q.defer();

promise.then(
// OnSuccess function
function (answer) {
// This code will only run if we have a successful promise.
deferObjectCompany.resolve(answer.data);
var item = answer.data;
return item;
                              },
// OnFailure function
function (reason) {
// This code will only run if we have a failed promise.
deferObjectCompany.reject(reason);
                              });

return deferObjectCompany.promise;



          },

this.fetchAllCompagnie=function () {
var deferObjectCompany;
var promise = $http({
method: 'POST',
url: 'http://localhost:8090/data',
//url: 'http://rest-service.guides.spring.io/greeting',
headers: {
'Content-Type': 'application/json; charset=utf-8'
}

                    });
deferObjectCompany = deferObjectCompany || $q.defer();

promise.then(
// OnSuccess function
function (answer) {
// This code will only run if we have a successful promise.
deferObjectCompany.resolve(answer.data);
var item = answer.data;
return item;
                        },
// OnFailure function
function (reason) {
// This code will only run if we have a failed promise.
deferObjectCompany.reject(reason);
                        });

return deferObjectCompany.promise;
                },
				
				
this.fetchdataer=function () {
var deferObjectCompany;
var promise = $http({
method: 'POST',
url: 'http://localhost:8090/datarequest',
//url: 'http://rest-service.guides.spring.io/greeting',
headers: {
'Content-Type': 'application/json; charset=utf-8'
   }
                                   });
deferObjectCompany = deferObjectCompany || $q.defer();

promise.then(
// OnSuccess function
function (answer) {
// This code will only run if we have a successful promise.
deferObjectCompany.resolve(answer.data);
var item = answer.data;
return item;
                                             },
// OnFailure function
function (reason) {
// This code will only run if we have a failed promise.
deferObjectCompany.reject(reason);
                          });

return deferObjectCompany.promise;
                         }

};

function logger() {

var logIt;

// toastr setting.
toastr.options = {
"closeButton": true,
"positionClass": "toast-bottom-right",
"timeOut": "3000"
};

logIt = function(message, type) {
return toastr[type](message);
        };

return {
log: function(message) {
logIt(message, 'info');
            },
logWarning: function(message) {
logIt(message, 'warning');
            },
logSuccess: function(message) {
logIt(message, 'success');
            },
logError: function(message) {
logIt(message, 'error');
            }
        };

    }

        })();

