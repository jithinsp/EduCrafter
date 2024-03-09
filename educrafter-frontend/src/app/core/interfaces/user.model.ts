export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
    role: string;
    isActive: boolean;
  }

  export interface UserModel{
    list: User[],
    userObj: User,
    errormessage: string
  }

  export interface CustomMessage{
    message: string,
    description: string,
    messageDate: string,
    enabled: boolean,
    messageId: string,


  }