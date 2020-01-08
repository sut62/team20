<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบยืนยันการรับพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>แบบฟอร์มยืนยันการรับพัสดุ</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="1"></b-col>
                <b-col cols="5">

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="ConfirmPackage.employeeId" :options="this.employeeData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="input-with-list">กรอก Package ID</label>
                    <b-form-input list="input-list" v-model="ConfirmPackage.packageId" id="input-with-list"></b-form-input>
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
                                ที่อยู่ผู้รับ : {{this.packageData.place}} <br>
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
                    <label for="selectList">เลือกระดับความพึงพอใจ</label>
                    <b-form-select v-model="ConfirmPackage.satisfactionlevelId" :options="this.satisfactionlevelData" class="mb-3" value-field="satisfactionlevel_id" text-field="satisfactionlevel_name" disabled-field="notEnabled" id="selectList"></b-form-select>

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
            haveSearch: false,
            ConfirmPackage: {
                packageId: null,
                employeeId: null,
                satisfactionlevelId: null,
            },
            packageData: {
                receiever: "None",
                place: "Arrive",
                createBy: {
                    name: "Annonymous"
                }
            },
            employeeData: "",
            satisfactionlevelData: ""
        }
    },
    methods: {
        Search() {
            this.findPackageById()
            this.haveSearch = true
        },
        Save() {
            api.post("/addConfirmPackage", {
                    packageId: this.ConfirmPackage.packageId,
                    employeeId: this.ConfirmPackage.employeeId,
                    satisfactionLevelId: this.ConfirmPackage.satisfactionlevelId
                })
                .then(
                    response => {
                        if (response.data)
                            alert("ทำการบันทึกยืนยันการรับพัสดุสำเร็จ")
                    },
                    error => {
                        if (error)
                            alert("ทำการบันทึกยืนยันการรับพัสดุไม่สำเร็จ")
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
            api.get("/findPackageById/" + this.ConfirmPackage.packageId)
                .then(
                    response => {
                        this.packageData = response.data
                        this.foundPackage = true
                    },
                    error => {
                        if (error)
                            alert("ไม่พบ package จากการค้นหากรุณาค้นหาอีกครั้ง !")
                    }
                )

        },
        getAllSatisfactionLevel() {
            api.get("/satisfactionLevel")
                .then(response => {
                    this.satisfactionlevelData = response.data
                })
        },

    },
    mounted() {
        this.getAllEmployees(),
        this.getAllSatisfactionLevel()

    } 
}
</script>
