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

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="ShippingState.employeeId" :options="this.employeeData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="input-with-list">กรอก Package ID</label>
                    <b-form-input list="input-list" v-model="ShippingState.packageId" id="input-with-list"></b-form-input>
                    <b-button class="mt-2" @click="this.Search">ค้นหา</b-button>
                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <b>สถานะการค้นหา Package</b>
                    <div class="text-left mt-2 text-break">
                        <div>
                            สถานะ :
                            <span v-if="this.haveSearch">
                                <div v-if="this.foundPackage" class="badge badge-success text-wrap" style="width: 6rem;">
                                    พบ Package
                                </div>
                                <div v-else class="badge badge-danger text-wrap" style="width: 6rem;">
                                    ไม่พบ Package
                                </div>
                            </span>

                            <span v-if="!this.haveSearch">
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

                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <hr v-if="this.foundPackage">

            <b-row class="mt-4 mb-4" v-if="this.foundPackage">

                <b-col cols="1"></b-col>
                <b-col>
                    <label for="selectList">เลือกชื่อสถานี</label>
                    <b-form-select v-model="ShippingState.stationId" :options="this.stationData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <label for="selectList">เลือกสถานะ</label>
                    <b-form-select v-model="ShippingState.statusId" :options="this.statusData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <b-alert class="mt-3 mb-4" :show="saveStatus.popup.dismissCountDown" dismissible fade :variant="saveStatus.popup.variant">
                {{this.saveStatus.popup.message}}
            </b-alert>

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
            haveSearch: false,
            ShippingState: {
                packageId: null,
                employeeId: null,
                stationId: null,
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
            stationData: "",
            statusData: "",
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
        Search() {
            this.findPackageById()
            this.haveSearch = true
        },
        Save() {
            api.post("/addShippingState", {
                    packageId: this.ShippingState.packageId,
                    employeeId: this.ShippingState.employeeId,
                    stationId: this.ShippingState.stationId,
                    statusId: this.ShippingState.statusId
                })
                .then(
                    response => {
                        if (response.data) {
                            this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                            this.saveStatus.popup.variant = "success"
                            this.saveStatus.popup.message = "ทำการบันทึกสถานะพัสดุสำเร็จ"
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
            api.get("/findPackageById/" + this.ShippingState.packageId)
                .then(
                    response => {
                        this.packageData = response.data
                        this.foundPackage = true
                        this.getAllStatus()
                        this.getAllStation()
                    },
                    error => {
                        if (error)
                            alert("ไม่พบ package จากการค้นหากรุณาค้นหาอีกครั้ง !")
                    }
                )

        },
        getAllStation() {
            api.get("/getStations")
                .then(response => {
                    this.stationData = response.data
                })
        },
        getAllStatus() {
            api.get("/getStatus")
                .then(response => {
                    this.statusData = response.data
                })
        },

    },
    mounted() {
        this.getAllEmployees()
    }
}
</script>