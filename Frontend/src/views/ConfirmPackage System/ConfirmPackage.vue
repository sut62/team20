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
                <b-col cols="3"></b-col>
                <b-col cols="6">

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="ConfirmPackage.employeeId" :options="this.employeeData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>
                    
                    <label for="selectList">เลือกID package</label>
                    <b-form-select v-model="ConfirmPackage.packageId" :options="this.packageData" class="mb-3" value-field="id" text-field="id" disabled-field="notEnabled" id="selectList"></b-form-select>
    
                    <label for="selectList">เลือกระดับความพึงพอใจ</label>
                    <b-form-select v-model="ConfirmPackage.satisfactionlevelId" :options="this.satisfactionlevelData" class="mb-3" value-field="satisfactionlevel_id" text-field="satisfactionlevel_name" disabled-field="notEnabled" id="selectList"></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

                <b-button variant="primary" @click="this.Save">บันทึก</b-button>
            <hr>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            ConfirmPackage: {
                packageId: null,
                employeeId: null,
                satisfactionlevelId: null,
            },
            employeeData: "",
            packageData: "",
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
        getAllPackage() {
            api.get("/getAllPackage")
                .then(response => {
                    this.packageData = response.data
                })
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
        this.getAllPackage(),
        this.getAllSatisfactionLevel()
    } 
}
</script><template>
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
                <b-col cols="3"></b-col>
                <b-col cols="6">

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="ConfirmPackage.employeeId" :options="this.employeeData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>
                    
                    <label for="selectList">เลือกID package</label>
                    <b-form-select v-model="ConfirmPackage.packageId" :options="this.packageData" class="mb-3" value-field="id" text-field="id" disabled-field="notEnabled" id="selectList"></b-form-select>
    
                    <label for="selectList">เลือกระดับความพึงพอใจ</label>
                    <b-form-select v-model="ConfirmPackage.satisfactionlevelId" :options="this.satisfactionlevelData" class="mb-3" value-field="satisfactionlevel_id" text-field="satisfactionlevel_name" disabled-field="notEnabled" id="selectList"></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

                <b-button variant="primary" @click="this.Save">บันทึก</b-button>
            <hr>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            ConfirmPackage: {
                packageId: null,
                employeeId: null,
                satisfactionlevelId: null,
            },
            employeeData: "",
            packageData: "",
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
        getAllPackage() {
            api.get("/getAllPackage")
                .then(response => {
                    this.packageData = response.data
                })
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
        this.getAllPackage(),
        this.getAllSatisfactionLevel()
    } 
}
</script>