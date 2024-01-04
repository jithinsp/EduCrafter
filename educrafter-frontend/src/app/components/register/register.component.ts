import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  registerForm: FormGroup;
  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router
  ){}
  ngOnInit(): void {
      this.registerForm = this.fb.group({
        name: ['',[Validators.required]],
        email: ['',[Validators.required, Validators.email]],
        password: ['',[Validators.required]],
        confirmPassword: ['',[Validators.required]]
      }, {
        validator: this.passwordMatchValidator
      })
  }
  passwordMatchValidator(formGroup: FormGroup){
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;

    if(password != confirmPassword){
      formGroup.get('confirmPassword')?.setErrors({passwordMismatch : true});
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  submitForm(){
    console.log(this.registerForm.value);
    this.service.register(this.registerForm.value).subscribe(
      (response)=> {
        console.log(response);
        
        if(response.id!=null){
          this.router.navigateByUrl("/login");
        }
      }
    )
  }

}
