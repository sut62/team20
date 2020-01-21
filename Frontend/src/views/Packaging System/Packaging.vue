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
                    <label for="selectList">เลือกสถานีที่รับฝาก</label>
                    <b-form-select v-model="packageData.stationId" :options="this.stationData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="input-with-list">กรอกชื่อผู้รับ</label>
                    <b-form-input list="input-list" v-model="packageData.receieverName" id="input-with-list"></b-form-input>

                    <label for="input-with-list">กรอกที่อยู่ผู้รับ</label>
                    <b-form-input list="input-list" v-model="packageData.place" id="input-with-list"></b-form-input>

                    <label for="selectList">เลือกประเภทพัสดุ</label>
                    <b-form-select v-model="packageData.packageTypeId" :options="this.packageTypeData" class="mb-3" value-field="id" text-field="type" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="input-with-list">กรอกน้ำหนักพัสดุ</label>
                    <b-form-input list="input-list" v-model="packageData.weight" id="input-with-list"></b-form-input>

                    <label for="input-with-list">กรอกปริมาตรพัสดุ</label>
                    <b-form-input list="input-list" v-model="packageData.volume" id="input-with-list"></b-form-input>

                    <label for="selectList">เลือกประเภทการส่ง</label>
                    <b-form-select v-model="packageData.sendingTypeId" :options="this.sendingTypeData" class="mb-3" value-field="id" text-field="type" disabled-field="notEnabled" id="selectList"></b-form-select>
                    
                    <b-button variant="primary" style="width:80%" @click="this.Save">บันทึก</b-button>
                </b-col>

                <b-col cols="2"></b-col>
            </b-row>
            <b-alert class="mt-3 mb-4" :show="saveStatus.popup.dismissCountDown" dismissible fade :variant="saveStatus.popup.variant">
                {{this.saveStatus.popup.message}}
            </b-alert>
            <div v-if="this.foundPackage">
                <hr>

                

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
            findCustomerId: "",
            employeeData: JSON.parse(localStorage.getItem("employeeLogin")),
            stationData: "",
            sendingTypeData: "",
            packageTypeData: "",
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
                    dismissSecs: 3,
                    dismissCountDown: 0,
                    showDismissibleAlert: false,
                    variant: "danger",
                    message: ""
                }
            }
        }
    },
    methods: {
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
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "success"
                            this.saveStatus.popup.message = "ลงทะเบียนพัสดุสำเร็จ\nId package = " + response.data.id + "\nราคา = " + (parseFloat(this.packageData.volume) * parseFloat(this.packageData.weight))
                        }
                    },
                    error => {
                        if (error){
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ลงทะเบียนพัสดุไม่สำเร็จ"
                        }
                    })
        },
        findCustomer() {
            api.get("/FindMemberCustomerId/" + this.findCustomerId)
                .then(
                    response => {
                        alert("พบ id ของลูกค้า ชื่อ : " + response.data.name),
                            this.packageData.customerId = response.data.id
                    },
                    error => {
                        if (error)
                            alert("ไม่พบ id ของลูกค้า")
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
            this.getAllPackageType()
    }
}
</script>
