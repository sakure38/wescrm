// index.js
// 获取应用实例
const app = getApp()
var Server = require("../../server/Server.js")

Page({
  
  data: {
    contextUrl: app.CONTEXT_URL,
    recommendList:[],
    products: [],
    pageNum:1,
    hasMore:true
  },

  //在小程序进入页面的时候，会调用onLoad
  onLoad() {
    Server.getRecommend(res =>{
      var recommendList = res.data.data.recommendList;
      this.setData({
        recommendList:recommendList
      })
    })

    this.queryProducts();
  },

  queryProducts: function(){
    if(!this.data.hasMore){return}
    var pageNum = this.data.pageNum;
    Server.queryProducts(pageNum, 0, res =>{
      console.log(res)
      var products = res.data.data.items;
      var hasMore = false;
      if(products.length > 0){
        hasMore = true;
      }
      this.setData({
        hasMore:hasMore,
        pageNum: this.data.pageNum + 1,
        products:this.data.products.concat(products)
      })
    })
  },

 onReachBottom: function(){
   this.queryProducts();
 }
  
})
