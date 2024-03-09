export type IUserRegister = IStudentRegister | ITeacherRegister | IParentRegister | IAdminRegister;
export type IUserResponse = IStudentResponse | ITeacherResponse | IParentResponse | IAdminResponse;

export interface IStudentRegister {
    email: string
    name: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    remarks: string
  }

  export interface IParentRegister {
    email: string
    name: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    studentId: string
    remarks: string
  }

  export interface ITeacherRegister {
    email: string
    name: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    designation: string
    remarks: string
  }

  export interface IAdminRegister {
    email: string
    name: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    remarks: string
  }
  
  export interface IStudentResponse {
    id: string
    createdDate: string
    lastModifiedDate: string
    name: string
    email: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    role: string
    parent: any
    remarks: string
  }
  
  export interface IParentResponse {
    id: string
    createdDate: string
    lastModifiedDate: string
    name: string
    email: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    role: string
    designation: string
    remarks: string
  }

  export interface ITeacherResponse {
    id: string
    createdDate: string
    lastModifiedDate: string
    name: string
    email: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    role: string
    designation: string
    remarks: string
    isDeleted: string
  }
  
  export interface IAdminResponse {
    id: string
    createdDate: string
    lastModifiedDate: string
    name: string
    email: string
    password: string
    dateOfBirth: string
    sex: string
    phone: string
    role: string
    designation: string
    remarks: string
  }
  