angular.module('app', ['angular-meteor', 'ui.router'])
	.config(($urlRouterProvider, $stateProvider, $locationProvider) => {
  $locationProvider.html5Mode(true);

  $stateProvider
			.state('parties', {
  url: '/parties',
  templateUrl: 'client/parties/views/parties-list.ng.html',
  controller: 'PartiesListCtrl'
			})
			.state('partyDetails', {
  url: '/parties/:partyId',
  templateUrl: 'client/parties/views/party-details.ng.html',
  controller: 'PartyDetailsCtrl'
			});
  $urlRouterProvider.otherwise('/parties');
	});

angular.module('app', ['angular-meteor'])
.controller('PartyDetailsCtrl', ($scope, $stateParams, $meteor) => {
  $scope.party = $meteor.object(Parties, $stateParams.partyId, false);
  $scope.save = () => $scope.party.save();
  $scope.reset = () => $scope.party.reset();
	});

angular.module('app', ['angular-meteor'])
	.controller('PartiesListCtrl', ($scope, $meteor) => {
  $scope.parties = $meteor.collection(Parties);
  $scope.remove = (party) => $scope.parties.remove(party);
  $scope.removeAll = () => $scope.parties.remove();
	});
