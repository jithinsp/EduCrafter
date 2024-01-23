import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { JwtService } from "../core/services/auth/jwt.service";
import { loadUser, loadUserFail, loadUsersSuccess } from "./user.actions";
import { catchError, exhaustMap, map, of } from "rxjs";
import { AdminService } from "../core/services/admin/admin.service";
import { TeacherService } from "../core/services/teacher/teacher.service";

@Injectable()
export class UserEffects{
    constructor(private actin$: Actions, 
        private service: JwtService,
        private adminService: AdminService,
        private teacherService: TeacherService){}

    _loaduser=createEffect(()=>
    this.actin$.pipe(
        ofType(loadUser),
        exhaustMap((action)=>{
            return this.teacherService.getAllStudents().pipe(
                map((data)=>{
                    return loadUsersSuccess({list:data.list})
                }),
                catchError((_error)=>of(loadUserFail({errormessage:_error.message})))
            )
        })
    ))
}