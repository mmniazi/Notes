angular.module('app', ['angular-meteor'])
	.controller('PartyDetailsCtrl', ($scope, $stateParams, $meteor) => {
		$scope.party = $meteor.object(Parties, $stateParams.partyId, false);
		$scope.save = () => $scope.party.save();
		$scope.reset = () => $scope.party.reset();
	});
