<template>
  <div style="margin:15px; ">
    <!-- 搜索表单   -->
    <div style="margin-bottom: 10px ; margin-left: 250px;">

      <el-input style="width:240px" size="medium" placeholder="输入检索信息" v-model="historyRequest.inform"></el-input>

      <el-select v-model="historyRequest.orderField" placeholder="请选择排序方式" size="medium" style="width:240px;margin-left: 5px;">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <el-date-picker
          style="width: 330px; margin-left: 5px;"
          size="medium"
          align="right"
          v-model="datetime"
          type="datetimerange"
          format="yyyy-MM-dd HH:mm:ss"
          value-format="yyyy-MM-dd HH:mm:ss"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
      </el-date-picker>
      <el-button style="margin-left: 5px;" type="primary" size="medium" @click="load"><i class="el-icon-search" ></i> 搜索</el-button>
      <el-button style="margin-left: 5px;" type="warning" size="medium" @click="reset"><i class="el-icon-refresh" ></i> 重置</el-button>

    </div>
    <!--  展示内容  -->
    <div style="margin-left: 250px; margin-top: 20px;" v-for="item in resultHistory " :key="item">

      <el-card class="box-card">
        <div style="position: absolute">
          <div class="txt">患者编号：{{item.patientId}}</div>
          <div class="txt">检测类型：{{item.dataSet}}</div>
          <div class="txt">应用模型：{{item.modelName}}</div>
          <div class="txt">检测时间：{{item.createtime}}</div>
        </div>
        <!--   原始图像     -->
        <div style="float: right;padding-right: 220px; font-size: 17px; "> 输出图像： </div>
        <div style="float: right;padding-right: 280px; font-size: 17px; "> 原始图像： </div>

        <div style="position: absolute; margin-top: 10px;margin-left: 400px; width: 200px; height: 200px;">
          <img :src="item.inputUrl"  class="img1">
        </div>

        <div style="position: absolute; margin-top: 10px;margin-left: 762px;  width: 200px; height: 200px;">
          <img :src="item.inform" alt=""  class="img1">
        </div>

      </el-card>

    </div>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "history",
  data(){
    return{
      pageNum: 1,
      pageSize: 10,
      total: 0,
      datetime:null,
      options: [{
        value: "1",
        label: '按照患者排序'
      }, {
        value: "2",
        label: '按照时间顺序'
      }, {
        value: "3",
        label: '按照时间逆序'
      }],
      resultHistory:[],
      historyRequest:{userId:"",start:null,end:null,orderField:"",orderType:"",inform:"",pageNum:1,pageSize:10}
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      if (this.datetime && this.datetime.length >= 2) {
        this.historyRequest.start = this.datetime[0]
        this.historyRequest.end = this.datetime[1]
        console.log(this.historyRequest)
      }
      request.post("resulthistory/page",this.historyRequest).then(
          res => {
            console.log(res)
            this.resultHistory = res.data.list
            this.total = res.data.total
      })
    },
    reset(){
      this.historyRequest = {}
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.historyRequest.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.historyRequest.pageNum = pageNum
      this.load()
    },

  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}
.txt{
  height: 45px;
  width: 300px;
  font-size: 17px;
}
.item {
  padding: 18px 0;
}

.box-card {
  width: 1000px;
  height: 200px;
  font-size: 20px;
}
.img1{
  height: 150px;
  width: 150px;
}
</style>