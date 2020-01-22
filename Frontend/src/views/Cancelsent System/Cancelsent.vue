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
                    
                    <b-form-input list="my-list-id" name="packageCode" id="packageCode"  v-model="packageCode" placeholder="กรอก Package ID"></b-form-input>

                    <datalist id="my-list-id">
                        <option v-for="pack in allPackage" v-bind:key="pack">{{ pack.code }}</option>
                    </datalist>

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
                <b-col>
                <label for="input-with-list">*กรุณาใส่เหตุผล</label>
                    <b-form-input list="input-list" v-model="Cancelsent.cancelcomment" id="input-with-list"></b-form-input>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>
            <div v-if="this.statusPackage">
                <hr>
                  <div>

    <b-button variant="primary" @click="this.Save">บันทึก</b-button>
  </div>
            </div>
                  <b-alert class="mt-3 mb-4" :show="saveStatus.popup.dismissCountDown" dismissible fade :variant="saveStatus.popup.variant">
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
            foundPackage: false,
            statusPackage: false,
            haveSearch1: false,
            haveSearch2: false,
            Cancelsent: {
                packageId: null,
                cancelcomment: null,
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
            saveStatus: {
                popup: {
                    dismissSecs: 3,
                    dismissCountDown: 0,
                    showDismissibleAlert: false,
                    variant: "danger",
                    message: ""
                }
            },
            allPackage: "",
            packageCode: "",
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
                    cancelcomment: this.Cancelsent.cancelcomment,
                    employeeId: this.Cancelsent.employeeId,
                    senttobackId: this.Cancelsent.senttobackId,
                    howtopayId: this.Cancelsent.howtopayId,
                    statusId: this.Cancelsent.statusId
                })
                .then(
                     response => {
                        if (response.data) {
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "success"
                            this.saveStatus.popup.message = "ทำรายการสำเร็จ Id package = " + response.data.id + "\nราคา = " + ((parseFloat(this.packageData.volume) * parseFloat(this.packageData.weight)) / 2)
                        }
                    },
                    error => {
                        if (error) {
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ทำการบันทึกสถานะพัสดุไม่สำเร็จ"

                        }
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
            api.get("/findPackageByCode/" + this.packageCode)
                .then(
                    response => {
                        this.packageData = response.data
                        this.Cancelsent.packageId = this.packageData.id
                        this.lastShippingState = this.packageData.haveShippingState[this.packageData.haveShippingState.length - 1]
                        this.foundPackage = true
                    },
                    error => {
                        if (error)
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ไม่พบ package จากการค้นหากรุณาค้นหาอีกครั้ง !"
                    }
                )
        },
        checkPackageById() {
            api.get("/getShippingStateById/" + this.lastShippingState.id)
                .then(
                    response => {
                        if (response.data.onStatus.name == "ยกเลิก" || response.data.onStatus.name == "สำเร็จ"){
                            this.statusPackage = false
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ไม่พบ status จากการค้นหากรุณาค้นหาอีกครั้ง !"
                        }
                        else
                            this.statusPackage = true
                    },
                    error => {
                        if (error)
                        this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "ไม่พบ status จากการค้นหากรุณาค้นหาอีกครั้ง !"
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
        getAllPackage(){
            api.get("/getAllPackage")
                .then(response => {
                    this.allPackage = response.data
                })
        },
        getStatus() {
            api.get("/getStatus")
                .then(response => {
                    for (var x in response.data) {
                        if (response.data[x].name == "ยกเลิก")
                            this.Cancelsent.statusId = response.data[x].id
                    }
                })
        },
    },
    mounted() {
            this.getAllEmployees()
            this.getAllSenttoback()
            this.getAllHowtopay()
            this.getStatus()
            this.getAllPackage()
    }
}
</script>