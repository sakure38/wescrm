var app = getApp();
var Server = require("../../server/Server.js");
var util = require("../../utils/util.js");

Page({

  data: {
    contextUrl: app.CONTEXT_URL,
  },

  onLoad: function (options) {
    var orderId = options.id;

    Server.getOrderById(orderId,res=>{
      var resData = res.data.data
      // console.log(resData)
      if(resData.order.createAt){
        resData.order.createAt = util.datetimeFormat(resData.order.createAt)
      }
      this.setData(resData)
    })
    
  }

})
