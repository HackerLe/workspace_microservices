
/**********************************************************************/
/********************Function For Food Service************************/
var foodStoreListMap = null;

$("#query_food_button").click(function(){
    $("#query_food_button").attr("disabled",true);

    var data = new Object();
    data.date = $('#food_date').val();
    data.startStation = $('#food_start_station').val();
    data.endStation = $('#food_end_station').val();
    data.tripId = $('#food_trip_id').val();

    // alert(JSON.stringify(data));
    $.ajax({
        type: "post",
        url: "/food/getFood",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(data),
        xhrFields: {
            withCredentials: true
        },
        success: function(result){
            console.log(result);

            var trainFoodList = result.trainFoodList;
            console.log("trainFoodList:" );
            console.log(trainFoodList[0]);

            $("#train_food_list_table").find("tbody").html("");
            for(var i = 0; trainFoodList[0] && i < trainFoodList[0]['foodList'].length; i++){
                console.log("trainfood"+ i + ":");
                console.log(trainFoodList[0]['foodList'][i]);
                $("#train_food_list_table").find("tbody").append(
                    "<tr>" +
                    "<td>" + (i+1) + "</td>" +
                    "<td>" + trainFoodList[0]["tripId"] + "</td>" +
                    "<td>" + trainFoodList[0]['foodList'][i]['foodName'] + "</td>" +
                    "<td>" + trainFoodList[0]['foodList'][i]['price'] + "</td>" +
                    "</tr>"
                );
            }

            var stationIds = new Array();
            foodStoreListMap = result.foodStoreListMap;
            for(var key in foodStoreListMap){
                stationIds.push(key);
                var fslist = foodStoreListMap[key];
                showFoodStores(key, fslist);
            }
            var stationSelect = document.getElementById ("food_station_select");
            var opt1 = document.createElement ("option");
            opt1.value = 0;
            opt1.innerText = "-- --";
            stationSelect.appendChild(opt1);
            for(var k = 0; k < stationIds.length; k++){
                var opt2 = document.createElement ("option");
                opt2.value = k + 1;
                opt2.innerText = stationIds[k];
                stationSelect.appendChild (opt2);
            }

        },
        complete: function(){
            $("#query_food_button").attr("disabled",false);
        }
    });

});

function changeFoodStation(){
    var station = $('#food_station_select').find("option:selected").text();
    var  foodStoreList = foodStoreListMap[station];
    console.log("foodStoreList:");
    console.log(foodStoreList);
    showFoodStores(station, foodStoreList);

}

function showFoodStores(station, list){
    console.log("showFoodStores:");
    console.log(list);
    $("#food_of_store").find("tbody").html("");
    $("#food_stores_of_station_list").find("tbody").html("");
    for(var j = 0; j < list.length; j++){
        $("#food_stores_of_station_list").find("tbody").append(
            "<tr>" +
            "<td ><input class='food_index_of_store' type='text' value='" + (j+1) + "'/></td>" +
            "<td>" + list[j]["storeName"] + "</td>" +
            "<td>" + list[j]['telephone'] + "</td>" +
            "<td>" + list[j]['businessTime']  + "</td>" +
            "<td>" + list[j]['deliveryFee']  + "</td>" +
            "<td>" + "<button class='show_store_foods btn btn-primary' >Show Foods</button>" + "</td>" +
            "</tr>"
        );
    }
    addListenerToShowFood();
}

function  addListenerToShowFood(){
    var showFoodBtn = $(".show_store_foods");
    for(var i = 0;i < showFoodBtn.length;i++){
        showFoodBtn[i].onclick = function(){
            var station = $('#food_station_select').find("option:selected").text();
            var storeIndex = parseInt($(this).parents("tr").find(".food_index_of_store").val()) - 1;
            console.log("storeIndex=" + storeIndex);
            showFoods(station, storeIndex);
        }
    }
}


function showFoods(station, j){
    var foodList = foodStoreListMap[station][j]['foodList'];
    console.log("foodlist:");
    console.log(foodList);
    $("#food_of_store").find("tbody").html("");
   for(var j = 0; j < foodList.length; j++){
       $("#food_of_store").find("tbody").append(
           "<tr>" +
           "<td>" + (j+1) + "</td>" +
           "<td>" + foodList[j]["foodName"] + "</td>" +
           "<td>" + foodList[j]['price'] + "</td>" +
           "</tr>"
       );
   }
}
