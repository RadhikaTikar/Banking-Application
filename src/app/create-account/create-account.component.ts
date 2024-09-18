import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { Account } from '../account';
import { Router } from '@angular/router';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent {

  account:Account=new Account();

  accountCreate=false;

  constructor(private accountService:AccountService,private router:Router){}

  onSubmit()
  {
    this.saveAccount();
  }

  saveAccount()
  {
    this.accountService.createAccount(this.account).subscribe(data=>{

      console.log(data);
      this.accountCreate=true;
      setTimeout(() => {
        this.goToAccountList();
      },1000);

    })

 }

  goToAccountList()
  {
    this.router.navigate(['/accounts']);
  }

}
