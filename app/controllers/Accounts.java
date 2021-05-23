package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {

  public static void signup() {
    render("signup.html");
  }

  public static void login() {
    Logger.info("Rendering Login");
    render("login.html");
  }

  public static void update() {
    render("update.html");
  }

  public static void register(String firstname, String lastname, String email, String password) {
    Logger.info("Registering new user " + email);
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    redirect("/login");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      String error = "Authentication failed";
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout() {
    session.clear();
    redirect("/");
  }

  public static void updated(String firstname, String lastname, String email, String password) {
    Logger.info("updating user details");
    getLoggedInMember().firstname = firstname;
    getLoggedInMember().lastname = lastname;
    getLoggedInMember().email = email;
    getLoggedInMember().password = password;
    getLoggedInMember().save();
    session.clear();
    redirect("/login");
  }

  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
}