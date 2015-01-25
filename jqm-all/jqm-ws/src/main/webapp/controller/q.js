'use strict';

var jqmControllers = angular.module('jqmControllers');

jqmControllers.controller('µQueueListCtrl', function($scope, $http, µQueueDto)
{
    $scope.queues = null;
    $scope.sortvar = 'name';
    $scope.selected = [];

    $scope.newqueue = function()
    {
        $scope.queues.push(new µQueueDto({
            name : 'new queue',
            description : 'enter description',
            defaultQueue : false
        }));
        $scope.gridOptions.selectRow(1);
    };

    $scope.save = function()
    {
        // Save and refresh the table - ID may have been generated by the server.
        µQueueDto.saveAll({}, $scope.queues, $scope.refresh);
    };

    $scope.refresh = function()
    {
        $scope.selected.length = 0;
        $scope.queues = µQueueDto.query();
    };

    $scope.remove = function()
    {
        var q = null;
        for ( var i = 0; i < $scope.selected.length; i++)
        {
            q = $scope.selected[i];
            if (q.id !== null && q.id !== undefined)
            {
                q.$remove({
                    id : q.id
                });
            }
            $scope.queues.splice($scope.queues.indexOf(q), 1);
        }
        $scope.selected.length = 0;
    };

    $scope.gridOptions = {
        data : 'queues',
        enableCellSelection : true,
        enableRowSelection : true,
        enableCellEditOnFocus : true,
        multiSelect : true,
        showSelectionCheckbox : true,
        selectWithCheckboxOnly : true,
        selectedItems : $scope.selected,
        plugins :  [new ngGridFlexibleHeightPlugin()],
        columnDefs : [ {
            field : 'name',
            displayName : 'Name',
            width : '**',
        }, {
            field : 'description',
            displayName  :'Description',
            width : '*****',
        }, {
            field : 'defaultQueue',
            displayName : 'Is default',
            cellTemplate : '<div class="ngSelectionCell" ng-class="col.colIndex()"><span class="glyphicon {{ row.entity[col.field] ? \'glyphicon-ok\' : \'glyphicon-remove\' }}"></span></div>',
            editableCellTemplate : '<div class="ngSelectionCell" ng-class="col.colIndex()"><input type="checkbox" ng-input="COL_FIELD" ng-model="COL_FIELD"/></div>',
            width : '*',
        } ]
    };

    $scope.refresh();
});

/*
 * jqmControllers.controller('NodeDetailCtrl', [ '$scope', '$routeParams', 'µNodeItem', function($scope, $routeParams, µNodeItem) { $scope.nodeId =
 * $routeParams.nodeId; $scope.node = µNodeItem.get( { nodeId : $routeParams.nodeId }); } ]);
 */