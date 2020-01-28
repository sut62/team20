<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบรับฝากพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>รับฝากพัสดุ</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="2"></b-col>
                <b-col cols="8">
                    <label for="input-with-list">กรอก Customer ID</label>

                    <b-row>
                        <b-col cols="8">
                            <b-form-input list="input-list" v-model="findCustomerId" id="input-with-list"></b-form-input>
                        </b-col>
                        <b-col cols="4">
                            <b-button style="width:100%" @click="this.findCustomer">ค้นหา</b-button>
                        </b-col>
                    </b-row>
                    <b-alert class="mt-3 mb-4" :show="saveStatus1.popup.showAlert" dismissible fade :variant="saveStatus1.popup.variant">
                        {{this.saveStatus1.popup.message}}
                    </b-alert>
                    <label for="selectList">เลือกสถานีที่รับฝาก</label>
                    <b-form-select v-model="packageData.stationId" :options="this.stationData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="input-with-list">กรอกชื่อผู้รับ</label>
                    <b-form-input list="input-list" v-model="packageData.receieverName" id="input-with-list1"></b-form-input>

                    <label for="input-with-list">กรอกที่อยู่ผู้รับ</label>
                    <b-form-input list="input-list" v-model="packageData.place" id="input-with-list2"></b-form-input>

                    <label for="selectList">เลือกประเภทพัสดุ</label>
                    <b-form-select v-model="packageData.packageTypeId" :options="this.packageTypeData" class="mb-3" value-field="id" text-field="type" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="input-with-list">กรอกน้ำหนักพัสดุ</label>
                    <b-form-input list="input-list" v-model="packageData.weight" id="input-with-list3"></b-form-input>

                    <label for="input-with-list">กรอกปริมาตรพัสดุ</label>
                    <b-form-input list="input-list" v-model="packageData.volume" id="input-with-list4"></b-form-input>

                    <label for="selectList">เลือกประเภทการส่ง</label>
                    <b-form-select v-model="packageData.sendingTypeId" :options="this.sendingTypeData" class="mb-3" value-field="id" text-field="type" disabled-field="notEnabled" id="selectList"></b-form-select>
                    
                    <b-button deck v-if="this.saveState == 1" variant="primary" style="width:80%" @click="this.Save">บันทึก</b-button>
                    <b-button deck v-if="this.saveState == 2" variant="primary" style="width:80%" @click="this.DisplayPayment">แสดงค่าใช้จ่ายการรับฝากพัสดุ</b-button>
                </b-col>

                <b-col cols="2"></b-col>
            </b-row>
            <b-alert class="mt-3 mb-4" :show="saveStatus.popup.showAlert" dismissible fade :variant="saveStatus.popup.variant">
                {{this.saveStatus.popup.message}}
            </b-alert>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            findCustomerId: "",
            employeeData: JSON.parse(localStorage.getItem("employeeLogin")),
            stationData: [],
            sendingTypeData: [],
            packageTypeData: [],
            saveState: "",
            packageData: {
                receieverName: "",
                place: "",
                customerId: "",
                stationId: "",
                packageTypeId: "",
                sendingTypeId: "",
                volume: "",
                weight: "",
            },
            saveStatus: {
                popup: {
                    showAlert: false,
                    variant: "danger",
                    message: ""
                }
            },
            saveStatus1: {
                popup: {
                    showAlert: false,
                    variant: "danger",
                    message: ""
                }
            }
        }
    },
    methods: {
        DisplayPayment(){
            this.$router.push("displaypackagepayment")
        },
        Save() {
            api.post("/addPackaging", {
                    customerId: this.packageData.customerId,
                    employeeId: this.employeeData.id,
                    stationId: this.packageData.stationId,
                    pTypeId: this.packageData.packageTypeId,
                    sTypeId: this.packageData.sendingTypeId,
                    receiever: this.packageData.receieverName,
                    place: this.packageData.place,
                    volume: this.packageData.volume,
                    weight: this.packageData.weight
                })
                .then(response => {
                        if (response.data){
                            this.saveStatus.popup.showAlert = true
                            setTimeout(()=> {
                                this.saveStatus.popup.showAlert = false
                            }, 3000);
                            this.saveStatus.popup.variant = "success"
                            this.saveStatus.popup.message = "ลงทะเบียนพัสดุสำเร็จ"
                            localStorage.setItem("PackageCode",JSON.stringify(response.data.code))    
                            this.saveState = 2                    
                        }
                    },
                    error => {
                        if (error){
                            this.saveStatus.popup.showAlert = true
                            setTimeout(()=> {
                                this.saveStatus.popup.showAlert = false
                            }, 3000);
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ลงทะเบียนพัสดุไม่สำเร็จ"
                        }
                    })
        },
        findCustomer() {
            api.get("/FindMemberCustomerId/" + this.findCustomerId)
                .then(
                    response => {
                        this.saveStatus1.popup.showAlert = true
                        setTimeout(()=> {
                            this.saveStatus1.popup.showAlert = false
                        }, 3000);
                        this.saveStatus1.popup.variant = "success"
                        this.saveStatus1.popup.message = "พบ id ของลูกค้า ชื่อ : " + response.data.name
                        this.packageData.customerId = response.data.id
                    },
                    error => {
                        if (error){
                            this.saveStatus1.popup.showAlert = true
                            setTimeout(()=> {
                                this.saveStatus1.popup.showAlert = false
                            }, 3000);
                            this.saveStatus1.popup.variant = "danger"
                            this.saveStatus1.popup.message = "ไม่พบ id ของลูกค้า"
                        }
                    }
                )
        },
        getAllStation() {
            api.get("/getStations")
                .then(response => {
                    this.stationData = response.data
                })
        },
        getAllSendingType() {
            api.get("/getSendingType")
                .then(response => {
                    this.sendingTypeData = response.data
                })
        },
        getAllPackageType() {
            api.get("/getPackageType")
                .then(response => {
                    this.packageTypeData = response.data
                })
        }
    },
    mounted() {
        this.getAllStation(),
            this.getAllSendingType(),
            this.getAllPackageType(),
            this.saveState = 1
    }
}
</script>
