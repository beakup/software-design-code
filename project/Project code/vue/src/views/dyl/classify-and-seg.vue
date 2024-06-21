<template>
  <div>
    <el-card class="box-card">
      <el-card class="card1">
        <div class="txt1">上传病例图像(.jpg,.png,.jpeg)</div>


        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/file/upload?token='+userEdge.token"
            :show-file-list="false"
            :on-success="handleFileUploadSuccess">
          <img v-if="imagUrl" :src="imagUrl" class="avatar1">
          <i  v-else class="el-icon-plus avatar-uploader-icon" ></i>
        </el-upload>

        <div class="txt1">输入病人信息</div>
        <div class="inputGroup">
          <input v-model="patient.id" type="text" required="" autocomplete="off">
          <label>ID</label>
        </div>

        <div class="txt1">选择类型</div>

        <div style="padding-left: 5px; width: 120px; float: left">
          <div class="container">
            <form>
              <label>
                <input type="radio" name="radio" checked="" @click="detectRequst.task='cls';loadInformation()">
                <span>分类</span>
              </label>
              <label>
                <input type="radio" name="radio" @click="detectRequst.task='seg';loadInformation()">
                <span>分割</span>
              </label>
            </form>
          </div>
        </div>

        <div style="padding-left:25px; padding-top: 20px; float: left">
          <el-select v-model="detectRequst.modelName" placeholder="请选择模型" size="medium" style="width:120px " @change="chooseModel=true" >
            <el-option v-for="item in options.model" :key="item.name" :value="item.name">
            </el-option>
          </el-select>
        </div>

        <div style="padding-left:40px; padding-top: 20px; float:left;">
          <el-select v-model="detectRequst.dataSet" placeholder="请选择数据集" size="medium" style="width:140px" @change="chooseDataset=true" >
            <el-option v-for="item in options.dataset" :key="item.name" :value="item.name">
            </el-option>
          </el-select>
        </div>

      </el-card>


      <div style="position:absolute; margin-top: 138px; margin-left: 220px; color: #2f60bd;" v-if="if_img">点击上传图像</div>
      <el-card class="card2">
        <div class="txt1">输出图像结果</div>

        <el-card class="card3">
          <!--          分割图-->
          <img v-if="segOutUrl" :src="segOutUrl" class="avatar">
          <!--          分类图-->
          <div v-else-if="classify" class="echart" id="mychart" :style="myChartStyle" ref="bigChart"></div>
          <!--          没有预测的情况-->
          <el-empty v-else description="暂无图像" ></el-empty>
        </el-card>

        <div style="padding-top: 30px;padding-left: 60px;">
          <div style="width: 200px; float:left;">
            <el-button type="warning" size="medium"
                       @click="detectImage"
                       :disabled="!uploadFile ||!chooseModel || !chooseDataset "
                       plain>输出结果</el-button>
          </div>
          <div style="width: 200px; padding-left: 150px; float:left;">
            <el-button type="success" size="medium" plain
                       :disabled="!fileDetect"
                       @click="downloadImage">导出结果</el-button>
          </div>
        </div>



      </el-card>

    </el-card>
  </div>
</template>


<script>
import request from "@/utils/request";
import Cookies from "js-cookie";
import * as echarts from 'echarts'
// import html2canvas from "html2canva"

export default {
  name: "classify-and-seg",
  data() {
    return {
      imagUrl:"",
      detectRequst:{task:"cls",modelName:null,dataSet:null},
      patient:{id:null},
      segOutUrl: '',
      classify:true,
      options: {model:[],dataset:[]},
      userEdge:Cookies.get("userEdge") ? JSON.parse(Cookies.get("userEdge")) : {},
      uploadFile:false,
      chooseModel:false,
      chooseDataset:false,
      fileDetect:false,
      myChart: {},
      pieData: [],
      pieName: [],
      myChartStyle: { float: "left", width: "100%", height: "350px" },
    }
  },
  created() {
    this.loadInformation()
  },
  methods: {
    download() { //**
      // 图表转换成canvas
      html2canvas(document.getElementById("mychart")).then(function (canvas) {
        var img = canvas
            .toDataURL("image/png")
            .replace("image/png", "image/octet-stream");
        // 创建a标签，实现下载
        var creatIMg = document.createElement("a");
        creatIMg.download = "图表.png"; // 设置下载的文件名，
        creatIMg.href = img; // 下载url
        document.body.appendChild(creatIMg);
        creatIMg.click();
        creatIMg.remove(); // 下载之后把创建的元素删除
      });
    },
    initDate() {//**
      for (let i = 0; i < this.pieData.length; i++) {
        this.pieName[i] = this.pieData[i].name;
      }
    },
    initEcharts() {//**

      // 饼图
      let option = {
        legend: {
          // 图例
          data: this.pieName,
          right: "2%",
          top: "25%",
          orient: "vertical"
        },
        title: {
          // 设置饼图标题，位置设为顶部居中
          text: "分类结果",
          top: "5%",
          left: "center"
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            saveAsImage: {show: true},
          }
        },
        series: [
          {
            type: "pie",
            label: {
              show: true,
              formatter: "{c}%" // b代表名称，c代表对应值，d代表百分比
            },
            top: "20%",
            radius: "44%", //饼图半径
            data: this.pieData
          }
        ]
      };
      console.log(this.seriesData);
      const optionFree = {
        xAxis: {},
        yAxis: {},
        series: [
          {
            data: this.seriesData,
            type: "line",
            smooth: true
          }
        ]
      };
      this.myChart = echarts.init(this.$refs.bigChart)
      this.myChart.setOption(option);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        this.myChart.resize();
      });
    },
    handleFileUploadSuccess(res) {
      this.imagUrl = res
      this.detectRequst.inputUrl = res
      this.uploadFile = true
      console.log(this.detectRequst.inputUrl)
    },
    loadInformation(){
      this.detectRequst.modelName = null
      this.detectRequst.dataSet = null
      this.chooseModel=false
      this.chooseDataset=false
      request.get("model/list",{
        params:{
          task:this.detectRequst.task
        }
      }).then(res => {
        this.options.model = res.data
      })
      request.get("dataset/list",{
        params:{
          task:this.detectRequst.task
        }
      }).then(res => {
        this.options.dataset = res.data
      })
    },
    detectImage(){
      request.post("patien/save",this.patient).then(
          res => {
            this.detectRequst.patientId = this.patient.id
            request.post('detectImage/OutWithCache',
                this.detectRequst
            ).then(
                res => {
                  if(res.code === '200'){
                    if(this.detectRequst.task === "seg"){
                      this.classify = false
                      this.fileDetect = true
                      this.segOutUrl= res.data
                    }
                    else{
                      console.log("pie data",this.pieData)
                      this.segOutUrl = ""
                      this.classify = true
                      this.fileDetect=true
                      this.pieData = res.data
                      console.log("cls result", this.pieData)
                      this.initDate()
                      setTimeout(()=>{
                        this.initEcharts()
                      },10)

                    }
                  }
                }
            )
          }
      )
    },
    downloadImage(){
      if(this.detectRequst.task === "seg"){
        window.open(this.segOutUrl)
      }
    }
  }
}

</script>

//请把这个地方都复制一下
<style scoped>
.el-empty_description{
  color: #2f60bd;
}
.el-dropdown {
  vertical-align: top;
}
.el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.el-icon-arrow-down {
  font-size: 12px;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 1200px;
  height: 560px;
  border-color: #01B2B7;
  margin-top:30px;
  margin-left: 150px;
  margin-bottom: 25px;
  border-radius:2% ;
}
.card1{
  width: 570px;
  height: 520px;
  border-radius: 5%;
  position: absolute;
  border-color: #2f60bd;
}
.card2{
  width: 580px;
  position: absolute;
  border-radius: 5%;
  height: 520px;
  margin-left: 582px;
  border-color: #2f60bd;
}
.card3{
  width: 350px;
  height: 350px;
  margin-left: 100px;
}
.avatar-uploader .el-upload {
  border: 2px solid #2f60bd;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  margin-left: 45px;
  border:2px dashed #2f60bd;
  border-radius: 5%;
  font-size: 20px;
  color: #2f60bd;
  width: 400px;
  height: 200px;
  line-height: 200px;
  text-align: center;

}
.avatar {
  padding-bottom:10px;
  width: 320px;
  height: 320px;
  display: block;
}
.avatar1{
  height: 200px;
  width: 200px;
  margin-left: 100px;
}
.txt1{
  padding-top: 10px;
  padding-bottom: 13px;
  font-size: 18px;
  color: #1e7e34;
}
.inputGroup {
  font-family: 'Segoe UI', sans-serif;
  margin: 0 0 1em 0;
  max-width: 450px;
  position: relative;
  padding-left: 40px;
  color: #2f60bd;
}

.inputGroup input {
  font-size: 100%;
  padding: 0.8em;
  outline: none;
  border: 2px solid #2f60bd;
  background-color: transparent;
  border-radius: 12px;
  width: 100%;
}

.inputGroup label {
  font-size: 100%;
  position: absolute;
  left: 0;
  padding: 0.8em;
  margin-left: 40px;
  pointer-events: none;
  transition: all 0.3s ease;
  color: #FFFFFF;
}

.inputGroup :is(input:focus, input:valid)~label {
  transform: translateY(-50%) scale(.9);
  margin: 0em;
  margin-left: 1.3em;
  padding: 0.4em;
  background-color: rgb(100, 150, 200);
}

.inputGroup :is(input:focus, input:valid) {
  border-color: rgb(150, 150, 200);
}
.container form {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
}

.container label {
  display: flex;
  cursor: pointer;
  font-weight: 500;
  position: relative;
  overflow: hidden;
  margin-bottom: 0.375em;
}

.container  label input {
  position: absolute;
  left: -9999px;
}

.container label input:checked + span {
  background-color: #414181;
  color: white;
}

.container label input:checked + span:before {
  box-shadow: inset 0 0 0 0.4375em #00005c;
}

.container label span {
  display: flex;
  align-items: center;
  padding: 0.375em 0.75em 0.375em 0.375em;
  border-radius: 99em;
  transition: 0.25s ease;
  color: #414181;
}

.container label span:hover {
  background-color: #d6d6e5;
}

.container label span:before {
  display: flex;
  flex-shrink: 0;
  content: "";
  background-color: #fff;
  width: 1.5em;
  height: 1.5em;
  border-radius: 50%;
  margin-right: 0.375em;
  transition: 0.25s ease;
  box-shadow: inset 0 0 0 0.125em #00005c;
}

</style>