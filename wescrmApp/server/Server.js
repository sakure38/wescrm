// 获取应用实例
const app = getApp()
var Cache = require("./Cache")

//实现用户登录
function login(cb){
  wx.login({
    success: res=>{
      if(res.code){
        wx.showLoading({
          mask:true,
          title: '登录中...',
        })
        //发起登录
        wx.request({
          url: app.CONTEXT_URL + '/api/jscode2session', 
          method:"POST",
          data:{jscode:res.code},
          header: {
            'content-type': 'application/x-www-form-urlencoded' // 默认值
          },
          success : res => {
            Cache.setOpenid(res.data.data.openid)
            Cache.setSessionKey(res.data.data.session_key)
            if(typeof cb  === "function"){
              cb(res);
            }
          },
          fail(res){
      
          },
          complete(res){
            wx.hideLoading()
          }
        })

      }
    },

    fail: res=>{
    }
  })
}

//获取首页推荐的产品
function getRecommend(cb){
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/getRecommend', 
    method:"POST",
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success : res => {
      if(typeof cb  === "function"){
        cb(res);
      }
    },
    fail(res){

    },
    complete(res){

    }
  })
}

//获取首页推荐的产品
function queryProducts(pageNum, curCategory, cb){
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/queryProducts', 
    method:"POST",
    data:{pageNum:pageNum,category:curCategory},
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success : res => {
      if(typeof cb  === "function"){
        cb(res);
      }
    },
    fail(res){

    },
    complete(res){

    }
  })
}

//获取首页推荐的产品
function getProduct(id, cb){
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/getProduct', 
    method:"POST",
    data:{id:id},
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success : res => {
      if(typeof cb  === "function"){
        cb(res);
      }
    },
    fail(res){

    },
    complete(res){

    }
  })
}


//获取所有的分类
function queryCategory(cb){
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/queryCategory', 
    method:"POST",
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success : res => {
      if(typeof cb  === "function"){
        cb(res);
      }
    },
    fail(res){
    },
    complete(res){
    }
  })
}

//创建订单
function createOrder(pid, count, address, shippingTime,  invoice, remark, cb) {
  wx.showLoading({ mask: true, title: '数据加载中',});
  var openid = Cache.getOpenid();
  var sessionKey = Cache.getSessionKey();
  var paramData = {
    openid:openid, 
    sessionKey:sessionKey,
    pid:pid, 
    count:count,
    address:address,
    invoice:invoice,
    shippingTime:shippingTime,
    remark:remark
  }
  var parentOpenid = Cache.getParentOpenid();
  if(parentOpenid){
    paramData.parentOpenid = parentOpenid;
  }
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/createOrder',
    method: "POST",
    data: paramData,
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      console.log(res)
      if(res.data.errcode == 0){
        if (typeof cb === "function") {
          cb(res);
        }
      }else{
        wx.showModal({
          title: '提示',
          content: res.data.message,
          showCancel: false,
        });
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}


//创建订单
function getUserInfo(cb) {
  wx.showLoading({ mask: true, title: '数据加载中',});
  var openid = Cache.getOpenid();
  var sessionKey = Cache.getSessionKey();
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/getUserInfo',
    method: "POST",
    data: {openid:openid, sessionKey:sessionKey},
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}

//获取订单
function getOrders(pageNum, status, cb){
  wx.showLoading({ mask: true, title: '数据加载中',});
  var openid = Cache.getOpenid();
  var sessionKey = Cache.getSessionKey();
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/getOrders',
    method: "POST",
    data: {
      openid:openid, 
      sessionKey:sessionKey,
      pageNum:pageNum,
      status:status
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}

//获取订单数据
function getOrderById(id, cb) {
  wx.showLoading({ mask: true, title: '数据加载中',});
  var openid = Cache.getOpenid();
  var sessionKey = Cache.getSessionKey();
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/getOrderById',
    method: "POST",
    data: {
      openid:openid,
      sessionKey:sessionKey,
      id: id
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}

function updateUserInfo(user, cb){
  wx.showLoading({ mask: true, title: '数据加载中',});
  var openid = Cache.getOpenid();
  var sessionKey = Cache.getSessionKey();
  user.openid = openid;
  user.sessionKey = sessionKey;
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/updateUserInfo',
    method: "POST",
    data: user,
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}

function encryptWeRunData(encryptedData, iv, code, cb){
  wx.showLoading({ mask: true, title: '数据加载中',});
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/encryptWeRunData',
    method: "POST",
    data: {encryptedData:encryptedData, iv:iv, code:code},
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}

function giveSteps(toOpenid, openid, steps, cb){
  wx.showLoading({ mask: true, title: '数据加载中',});
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/giveSteps',
    method: "POST",
    data: {toOpenid:toOpenid, openid:openid, steps:steps},
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    },
    complete(){
      wx.hideLoading();//数据加载完成
    }
  });
}

function changePoints(openid, steps, cb){
  var sessionKey = Cache.getSessionKey();
  wx.request({
    url: app.CONTEXT_URL + '/api/mapp/changePoint',
    method: "POST",
    data: {
      "openid":openid,
      "sessionKey":sessionKey,
      "steps":steps
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    success: function (res) {
      if (typeof cb === "function") {
        cb(res);
      }
    },
    fail: function (res) {
      console.log(res);
    }
  });
}

module.exports = {
  login:login,
  getRecommend: getRecommend,
  queryProducts: queryProducts,
  queryCategory:queryCategory,
  getProduct:getProduct,
  createOrder:createOrder,
  getUserInfo:getUserInfo,
  getOrders:getOrders,
  getOrderById:getOrderById,
  updateUserInfo:updateUserInfo,
  encryptWeRunData:encryptWeRunData,
  giveSteps:giveSteps,
  changePoints:changePoints
}
