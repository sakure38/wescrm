var app = getApp();
var Server = require("../../server/Server.js");

Page({

  /**
   * 页面的初始数据
   */
  data: {
   
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    Server.login(res=>{
      var resData = res.data.data;
      if(resData.openid){//登录成功，获取用户信息
        Server.getUserInfo(res=>{
          var resData = res.data.data;
          this.setData({
            userInfo :resData,
            totalMoney:resData.money + resData.rebateMoney
          })
        })
      }
    });
  },

  doSubmit: function(e) {
    var total = e.detail.value.total;
    console.log(total)
    if(total > this.data.totalMoney){
      //没有实现
      wx.showModal({
        title: '提示',
        content: '提现金额错误',
        showCancel: false
      })
      return;
    }
    //没有实现
    wx.showModal({
      title: '提示',
      content: '提现成功',
      showCancel: false
    })
  },
  
})

