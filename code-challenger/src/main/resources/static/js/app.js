var app = angular.module('aluno', [ 'ngRoute', 'ngResource', 'ngAnimate' ]);

app.config(function($routeProvider) {

	$routeProvider.when("/alunolist", {
		controller : "controllerAluno",
		templateUrl : "list.html"
	})// listar

	.when("/alunoedit/:codigo", {
		controller : "controllerAluno",
		templateUrl : "cadastro.html"
	})// editar

	.when("/cadastro", {
		controller : "controllerAluno",
		templateUrl : "cadastro.html"
	})// novo

	.otherwise({
		redirectTo : "/"
	});
});

app.controller("controllerAluno", function($scope, $http, $location,
		$routeParams) {
	$scope.listAluno = [];
	
	if ($routeParams.codigo != null && $routeParams.codigo != undefined
			&& $routeParams.codigo != ''){// se estiver editando o fornecedor
		// entra pra editar
		$http.get("/alunos/" + $routeParams.codigo).success(function(data) {
			$scope.aluno = data;
			console.log($scope.aluno)
			
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
		
	}else { // novo aluno
		$scope.aluno = {};
	}
	
	
	$scope.salvarAluno = function(aluno){
		$http.post("/alunos", aluno).success(function(data, status, headers, config) {
			alert("Aluno salvo com sucesso!")
		}).error(function(data, status, headers, config) {
			alert("Erro ao salva aluno!");
		})
	};
	
	
	$scope.getListAluno = function(){
		console.log("teste");
		$http.get("/alunos").success(function(data, status, headers, config) {
			$scope.listAluno = data;
			console.log($scope.listAluno)
		}).error(function(data, status, headers, config) {
			alert("erro ao buscarr os alunos!")
		})
	};
	
	$scope.removerAluno = function(codidoAluno){
		$http.delete("/alunos/"+codidoAluno).success(function(data, status, headers, config) {
			alert("Aluno deletado com sucesso!")
			$scope.getListAluno();
		}).error(function(data, status, headers, config) {
			alert("Erro ao deletar Aluno!")
		})
	};
	
	$scope.editaraluno= function(codigo){
		$location.path('alunoedit/' + codigo);
	}
});