var GMPlugin = {
    startGM: function(success, fail, params) {
        if(success !== "function" || error !== "function") {
            console.log("Please provide success & failure callbacks");
        }
        // you can imagine passing some data here in params like 
        // {label: 'label', latitude: "46.62754", longitude: '-123.34535'}
        // and show them on the map
        // right now it just shows the wikipedia point of interests near the current location
        // this can be tweaked in the plugin
        Cordova.exec(success, fail, "GMPlugin", "startGM", [params]);
    },

};
