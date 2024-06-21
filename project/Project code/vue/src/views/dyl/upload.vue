<template>
  <div style="background-color: white;height: 615px;">

    <!--    左边上传数据集-->
    <div style="float: left; width: 550px;">
      <div style="padding-top: 5px;">
        <el-card class="card1" style="position: absolute;">
          <div>上传数据集（.zip，.7z，.gz，.dicom）</div>
        </el-card>
      </div>

      <el-card class="box-card" >
        <div style="width: 480px; height: 250px; padding-top: 30px;padding-left: 25px; position: relative;">
          <el-upload
              class="upload-demo"
              drag
              limit="1"
              show-file-list="True"
              ref="upload"
              :http-request="httpRequest"
              :on-remove="handleRemove"
              :on-change="fileChange"
              :before-remove="beforeRemove"
              :file-list="fileList"
              :auto-upload="false">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </div>

        <div style="padding-top: 90px;padding-left: 20px; width: 150px;">
          <el-card class="card2"  style="position: absolute;"></el-card>
          <div class="txt" style="position: absolute">数据集名称：</div>
          <el-input v-model="dataset.name" placeholder="请输入数据集名称" style="width: 300px;border-color: black;color: black; float: left; margin-left: 150px;"></el-input>
        </div>

        <div style="padding-top: 60px;padding-left: 20px; width: 150px;">
          <el-card style="position: absolute;" class="card2" >
          </el-card>
          <div class="txt" style="position: absolute">数据集用途：</div>

          <div class="check_container" style="float:left; margin-left: 150px; margin-top: 5px; z-index: 3">
            <input id="checkbox" class="hidden" type="checkbox">
            <label class="checkbox" for="checkbox"></label>
          </div>

          <div style="float:left; position: absolute;font-size: 14px;margin-left: 182px; margin-top: 5px;">分类</div>

          <div class="check_container" style="float:left; margin-left: 250px; margin-top: -32px; z-index: 2" @click="">
            <input id="seg" class="hidden" type="checkbox">
            <label class="checkbox" for="seg"></label>
          </div>

          <div style="float:left; position: absolute;font-size: 14px;margin-left: 280px; margin-top: 5px;" @click="">分割</div>

          <div style="width: 150px;float:left; ; padding-left: 355px; position: absolute;z-index: 1">
            <el-button type="warning" size="medium" @click="Fileupload" plain>确认上传</el-button>
          </div>

        </div>

        <div style="padding-top: 60px;padding-left: 20px; width: 150px;">
          <el-card style="position: absolute;" class="card2" >
          </el-card>
          <div class="txt" style="position: absolute">数据集描述：</div>
          <el-input
              style="margin-left: 150px;position: absolute;float: left;width: 300px;"
              type="textarea"
              :rows="3"
              placeholder="请输入描述内容"
              v-model="dataset.inform"></el-input>
        </div>
      </el-card>
    </div>

    <!--右边历史记录-->
    <div style="float: left;margin-left: 15px;">

      <div style="padding-top: 5px;">
        <el-card class="card1" style="position: absolute;">
          <div>上传数据集历史记录</div>
        </el-card>
      </div>

      <el-card class="box-card" >
        <div style="width: 480px; padding-top: 40px;margin-left: 100px; position: relative;">
          <el-input style="width:240px" size="small" placeholder="请输入检索信息" v-model="inform"></el-input>
          <el-button style="margin-left: 5px;" type="primary" size="small" @click="load"><i class="el-icon-search" ></i> 搜索</el-button>
          <el-button style="margin-left: 5px;" type="warning" size="small" @click="reset"><i class="el-icon-refresh" ></i> 重置</el-button>
        </div>

        <div style="margin-top: 20px;width: 500px;margin-left: 50px;" v-for="item in detail_data":key="item">
          <div class="card">

            <div>数据集名称：{{item.name}} &nbsp&nbsp&nbsp&nbsp   上传时间：{{item.createtime}}</div>

            <div class="card__content">
              <p class="card__title">类别:{{item.inform}}</p>
            </div>
          </div>

        </div>


      </el-card>
    </div>

  </div>
</template>

<script>

import request from "@/utils/request";
import axios from "axios";
import Cookies from "js-cookie";
export default {
  name: "upload",
  components: {},
  data(){
    return{
      userEdge:Cookies.get("userEdge") ? JSON.parse(Cookies.get("userEdge")) : {},
      showBox: false,
      textarea:"",
      params:"",
      detail_data:[],
      file:null,
      fileList: [],
      inform:"",
      dataset:{task:""}
    }
  },
  created() {
    this.load()
  },
  methods:{
    fileChange(file, fileList){
      this.file = file
      this.fileList = fileList
      console.log(this.fileList)
    },
    handleRemove(file, fileList){
      this.file = null
      this.fileList = []
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    httpRequest(param) {

      let fileObj = param.file // 相当于input里取得的files
      let fd = new FormData()// FormData 对象
      fd.append('file', fileObj)// 文件对象
      fd.append('platNum', this.fileList.platNum)
      fd.append('taskName', this.fileList.taskName)

      let url ='http://localhost:9090/api/file/upload?token='+this.userEdge.token
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios.post(url, fd, config).then(res=>{
        this.dataset.filePath = res
        request.post("dataset/save",this.dataset).then(res=>{
          if (res.code === 200){
            this.$message.success("上传成功")
          }
        })
      })

    },
    Fileupload(){
      this.$refs.upload.submit();
    },
    load(){
      request.get("dataset/list",{params:{
          inform:this.inform
        }}).then(res=>{
          this.detail_data = res.data
      })
    },
    reset(){
    }
  }
}
</script>

<style scoped>
.check_container {
  position: relative;
}

.checkbox {
  position: relative;
  top: 1px;
  width: 25px;
  height: 25px;
  border: 2px solid #212fab;
  border-radius: 50px;
  cursor: pointer;
  display: block;
  transition: all 0.3s linear;
}

.checkbox::after {
  content: "";
  position: absolute;
  top: 25%;
  left: 20%;
  width: 6px;
  height: 12px;
  opacity: 0;
  transform: rotate(45deg) scale(0) translate(-50%, -50%);
  border-right: 4px solid #ffffff;
  border-bottom: 4px solid #ffffff;
  transition: all 0.3s linear;
}

#checkbox:checked ~ .checkbox::after {
  opacity: 1 !important;
  transform: rotate(50deg) scale(1) translate(20%, -25%) !important;
}

#checkbox:checked ~ .checkbox {
  background: #212fab;
  border: none;
}

#seg:checked ~ .checkbox::after {
  opacity: 1 !important;
  transform: rotate(50deg) scale(1) translate(20%, -25%) !important;
}

#seg:checked ~ .checkbox {
  background: #212fab;
  border: none;
}

.hidden {
  display: none !important;
}

.card {
  position: relative;
  width: 450px;
  height: 80px;
  background-color: #f2f2f2;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  perspective: 1000px;
  box-shadow: 0 0 0 5px #ffffff80;
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.card svg {
  width: 48px;
  fill: #333;
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.card:hover {
  transform: scale(1.05);
  box-shadow: 0 8px 16px rgba(255, 255, 255, 0.2);
}

.card__content {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 20px;
  box-sizing: border-box;
  background-color: #f2f2f2;
  transform: rotateX(-90deg);
  transform-origin: bottom;
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.card:hover .card__content {
  transform: rotateX(0deg);
}

.card__title {
  margin: 0;
  font-size: 24px;
  color: #333;
  font-weight: 700;
}

.card:hover svg {
  scale: 0;
}

.card__description {
  margin: 10px 0 0;
  font-size: 14px;
  color: #777;
  line-height: 1.4;
}

.sub_card{
  width: 450px;
  height: 100px;
}
.sub_txt{
  font-size: 16px;
}
/deep/ .el-upload{
  width: 100%;
  height: 250px;
}
/deep/ .el-upload .el-upload-dragger{
  width: 100%;
  border: 2px dashed #2f60bd;
  height: 250px;
}
/deep/ .el-upload .el-upload-dragger:hover{
  border-color: #1e7e34;
}
.el-icon-upload{
  padding-top: 15px;
  font-size: 100px;
}
.el-upload__text{
  font-size: 16px;
  padding-top: 25px;
}

.card1{
  background-color: #17a2b8;
  height: 70px;
  width: 400px;
  margin-left: 280px;
  color: black;
  text-align: center;
  font-size: 18px;
  z-index: 2;
}

.card2{
  background-color: #17a2b8;
  height: 35px;
  width: 110px;
  text-align: center;
}
.txt{
  color: white;
  padding-left: 15px;
  padding-top: 6px;
}

.box-card {
  width: 550px;
  height: 560px;
  margin-top: 30px;
  border-color: #01B2B7;
  background-color: white;
  margin-left: 200px;
  margin-bottom: 25px;
  border-radius:2% ;
  z-index: 1;
}
</style>