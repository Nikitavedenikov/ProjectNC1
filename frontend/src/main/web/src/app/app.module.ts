import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorizationComponent } from './authorization/authorization.component';
import {HttpClientModule, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import {RegistrationComponent} from './registration/registration.component';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserCardComponent } from './user-card/user-card.component';
import {UserProfileRoutingModule} from "./user-profile/user-profile-routing.module";





@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    HomeComponent,
    RegistrationComponent,
    DashboardComponent,
    UserProfileComponent,
    UserCardComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    UserProfileRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
