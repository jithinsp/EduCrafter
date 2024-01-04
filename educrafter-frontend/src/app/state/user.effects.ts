import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { JwtService } from "../service/jwt.service";
import { loadUser, loadUserFail, loadUsersSuccess } from "./user.actions";
import { catchError, exhaustMap, map, of } from "rxjs";

@Injectable()
export class UserEffects{
    constructor(private actin$: Actions, private service: JwtService){}

    _loaduser=createEffect(()=>
    this.actin$.pipe(
        ofType(loadUser),
        exhaustMap((action)=>{
            return this.service.getAllUsers().pipe(
                map((data)=>{
                    return loadUsersSuccess({list:data})
                }),
                catchError((_error)=>of(loadUserFail({errormessage:_error.message})))
            )
        })
    ))
}