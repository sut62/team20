<template>
    <div>
        <b-card no-body>
            <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบจัดการสถานะพัสดุ</b-nav-item>
            </b-nav>
            </b-card-header>

            <b-card-body class="text-center">
            <b-card-title>แบบฟอร์มเพิ่มสถานะพัสดุ</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="1"></b-col>
                <b-col cols="5">
                    
                    <b-button class="mt-2"  variant="warning" @click="this.Search" >ค้นหา</b-button>
                </b-col>

                <b-col cols="1"></b-col>
                <b-col>
                    <b>ข้อมูล Package</b>
                    <div class="text-left mt-2 text-break">

                        <div v-if="this.foundPackage">
                            <hr>
                            <div class="ml-2">
                                <b-table sticky-header :items="items" head-variant="light"></b-table>
                            </div>
                        </div>
                        
                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <hr v-if="this.foundPackage">

            <b-row class="mt-4 mb-4" v-if="this.foundPackage">
                
                <b-col cols="1"></b-col>
                <b-col>
                <label for="input-with-list">กรอก Package ID</label>
                <b-form-input list="input-list" v-model="this.SentParcel.packageId" id="input-with-list"></b-form-input>
                </b-col>
                <b-col cols="1"></b-col>
                
                <b-col cols="1"></b-col>
                <b-col>
                    <label for="selectList">เลือกสถานีปลายทาง</label>
                    <b-form-select
                    v-model="this.SentParcel.stationId"
                    :options="this.stationData"
                    class="mb-3"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
                    id="selectList"
                    ></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                <label for="selectList">เวลาส่งพัสดุ</label>
                    <b-form-select
                    v-model="this.SentParcel.senttimeId"
                    :options="this.sentTime"
                    class="mb-3"
                    value-field="id"
                    text-field="rangeTime"
                    disabled-field="notEnabled"
                    id="selectList"
                    ></b-form-select>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            

            <div v-if="this.foundPackage">
                <hr>

                <b-button variant="warning" @click="this.Save">บันทึก</b-button>

            </div>
            </b-card-body>
        </b-card>
    </div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            foundPackage: false,
            stationData: "",
            sentTime: "",
            SentParcel:{
                packageId: null,
                stationId: null,
                senttimeId: null,
                receiveId: null
            },
             items: [
             { 'Packkage ID': "100", 'Station': this.stationData, 'Receive': 'มมส.' },
         ]
        }
    },
    methods:{
        Search(){
            this.foundPackage = true
        },
        Save(){
            api.post("/addSentParcel", {
                packageId: this.SentParcel.packageId,
                stationId: this.SentParcel.stationId,
                senttimeId: this.SentParcel.senttimeId,
                receiveId: this.SentParcel.receiveId
            })

        },
        getpackage() {
            api.get("/getAllPackagee")
                .then(response => {
                    this.SentParcel.packageId = response.data
                })
        },
        getAllStation() {
            api.get("/getStations")
                .then(response => {
                    this.stationData = response.data
                })
        },
        getAllSenttime() {
            api.get("/getAllRangeTimes")
                .then(response => {
                    this.sentTime = response.data
                })
        }
    },
    mounted() {
        this.getAllStation(),
        this.getAllSenttime()
    }
}
</script>