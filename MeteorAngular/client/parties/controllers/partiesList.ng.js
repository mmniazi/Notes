angular.module('app', ['angular-meteor'])
	.controller('PartiesListCtrl', ($scope, $meteor) => {
		$scope.parties = $meteor.collection(Parties);
		$scope.remove = (party) => $scope.parties.remove(party);
		$scope.removeAll = () => $scope.parties.remove();
	});
