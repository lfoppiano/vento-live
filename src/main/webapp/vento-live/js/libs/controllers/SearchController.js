function SearchController($scope, $http) {

    $scope.excludeRetweets = true;

    $scope.onKeyPress = function ($event) {
        if ($event.keyCode == 13) {
            $scope.newSearch();
        }
    }

    $scope.newSearch = function () {

        searchString = $scope.searchQuery+ '+exclude:retweets+exclude:replies'

        //$http.get('http://vento-rest-test.elasticbeanstalk.com/classification/twitter/query/'+searchString +'/lang/en/')
        $http.get('http://localhost:8080/classification/twitter/query/' + searchString + '/lang/en')
        //$http.get('http://localhost:8081/vento-live/berlusconi.json')
            .success(function (data) {
                $scope.tweets = data.tweets;
            });


    };
}
