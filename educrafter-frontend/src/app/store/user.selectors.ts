import { createFeatureSelector, createSelector } from "@ngrx/store";
import { UserModel } from "../core/interfaces/user.model";

const getuserstate = createFeatureSelector<UserModel>('user');

export const getuserlist = createSelector(getuserstate,(state)=>{
    return state.list;
})