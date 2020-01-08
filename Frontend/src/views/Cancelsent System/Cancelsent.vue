<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบยกเลิกพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>แบบฟอร์มยกเลิกพัสดุ</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="1"></b-col>
                <b-col cols="5">

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="Cancelsent.employeeId" :options="this.employeeData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>
                    <label for="input-with-list">กรอก Package ID</label>
                    <b-form-input list="input-list" v-model="Cancelsent.packageId" id="input-with-list"></b-form-input>
                    <b-button class="mt-2" @click="this.SearchPackage">ตรวจสอบ Package</b-button>
                    <br>
                    <b-button v-if="this.foundPackage" class="mt-2" @click="this.CheckStatus">ตรวจสอบสถานะ</b-button>

                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <b>สถานะการค้นหา Package</b>
                    <div class="text-left mt-2 text-break">
                        <div>
                            สถานะ Package :
                            <span v-if="this.haveSearch1">
                                <div v-if="this.foundPackage" class="badge badge-success text-wrap" style="width: 6rem;">
                                    พบ Package
                                </div>
                                <div v-else class="badge badge-danger text-wrap" style="width: 6rem;">
                                    ไม่พบ Package
                                </div>
                            </span>

                            <span v-if="!this.haveSearch1">
                                <div class="badge badge-warning text-wrap" style="width: 8rem;">
                                    รอการค้นหา package
                                </div>
                            </span>

                        </div>

                        <div v-if="this.foundPackage">
                            <hr>
                            <div class="ml-2">
                                <p class="text-center mb-1"><b>ข้อมูล Package</b></p>
                                ชื่อผู้ส่ง : {{this.packageData.createBy.name}} <br>
                                ชื่อผู้รับ : {{this.packageData.receiever}} <br>
                                ต้นสถานี : {{this.packageData.station.name}} <br>
                                ปลายทางสถานี : {{this.packageData.place}} <br>
                            </div>
                        </div>

                        <div>
                            สถานะ การทำรายการ:
                            <span v-if="this.haveSearch2">
                                <div v-if="this.statusPackage" class="badge badge-success text-wrap" style="width: 6rem;">
                                    สามารถยกเลิกได้
                                </div>
                                <div v-else class="badge badge-danger text-wrap" style="width: 6rem;">
                                    ไม่สามารถยกเลิกได้
                                </div>
                            </span>

                            <span v-if="!this.haveSearch2">
                                <div class="badge badge-warning text-wrap" style="width: 8rem;">
                                    รอการค้นหา package
                                </div>
                            </span>

                        </div>
                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <hr v-if="this.statusPackage">
            <b-row class="mt-4 mb-4" v-if="this.statusPackage">
                <b-col cols="1"></b-col>
                <b-col>
                    <label for="selectList">เลือกวิธีการรับพัสดุ</label>
                    <b-form-select v-model="Cancelsent.senttobackId" :options="this.senttobackData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>
                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <label for="selectList">เลือกวิธีการจ่ายเงิน</label>
                    <b-form-select v-model="Cancelsent.howtopayId" :options="this.howtopayData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>
            <div v-if="this.foundPackage">
                <hr>
                <b-button variant="primary" @click="this.Save">บันทึก</b-button>
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
            statusPackage: false,
            haveSearch1: false,
            haveSearch2: false,
            Cancelsent: {
                packageId: null,
                employeeId: null,
                senttobackId: null,
                howtopayId: null,
                statusId: null,
            },
            packageData: {
                receiever: "None",
                place: "Arrive",
                createBy: {
                    name: "Annonymous"
                },
                station: {
                    "name": "Origin"
                }
            },
            employeeData: "",
            senttobackData: "",
            howtopayData: "",
            statusData: "",
            lastShippingState: ""
        }
    },
    methods: {
        SearchPackage() {
            this.findPackageById()
            this.haveSearch1 = true
        },
        CheckStatus() {
            this.checkPackageById()
            this.haveSearch2 = true
        },

        Save() {
            api.post("/addCancelsent", {
                    packageId: this.Cancelsent.packageId,
                    employeeId: this.Cancelsent.employeeId,
                    senttobackId: this.Cancelsent.senttobackId,
                    howtopayId: this.Cancelsent.howtopayId,
                    statusId: this.Cancelsent.statusId
                })
                .then(
                    response => {
                        if (response.data)
                            alert("ทำการpdg]สำเร็จ")

                        this.addShippingState()
                    },
                    error => {
                        if (error)
                            alert("ทำการบันทึกสถานะพัสดุไม่สำเร็จ")
                    }
                )

        },
        addShippingState() {
            api.post("/addShippingState", {
                    packageId: this.Cancelsent.packageId,
                    employeeId: this.Cancelsent.employeeId,
                    stationId: this.lastShippingState.atStation.id,
                    statusId: this.Cancelsent.statusId
                })
                .then(
                    response => {
                        if (response.data)
                            alert("ทำการบันทึกสถานะพัสดุสำเร็จ")
                    },
                    error => {
                        if (error)
                            alert("ทำการบันทึกสถานะพัสดุไม่สำเร็จ")
                    }
                )
        },
        getAllEmployees() {
            api.get("/getEmployees")
                .then(response => {
                    this.employeeData = response.data
                })
        },
        findPackageById() {
            api.get("/findPackageById/" + this.Cancelsent.packageId)
                .then(
                    response => {
                        this.packageData = response.data
                        this.lastShippingState = this.packageData.haveShippingState[this.packageData.haveShippingState.length - 1]
                        this.foundPackage = true
                    },
                    error => {
                        if (error)
                            alert("ไม่พบ package จากการค้นหากรุณาค้นหาอีกครั้ง !")
                    }
                )
        },
        checkPackageById() {
            api.get("/getShippingStateById/" + this.lastShippingState.id)
                .then(
                    response => {
                        if (response.data.onStatus.name == "ยกเลิก")
                            this.statusPackage = false
                        else
                            this.statusPackage = true

                    },
                    error => {
                        if (error)
                            alert("ไม่พบ status จากการค้นหากรุณาค้นหาอีกครั้ง !")
                    }
                )
        },
        getAllSenttoback() {
            api.get("/getSenttoback")
                .then(response => {
                    this.senttobackData = response.data
                })
        },
        getAllHowtopay() {
            api.get("/getHowtopay")
                .then(response => {
                    this.howtopayData = response.data
                })
        },
        getStatus() {
            api.get("/getStatus")
                .then(response => {
                    for (var x in response.data) {
                        if (x.name == "ยกเลิก")
                            this.Cancelsent.statusId = x.id
                    }
                })
        }
    },
    mounted() {
        this.getAllEmployees(),
            this.getAllSenttoback(),
            this.getAllHowtopay(),
            this.getStatus()

    }
}
</script>
