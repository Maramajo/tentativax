package net.codejava.customer;

 
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import login.LoginRep;

@Controller
public class LoginController{
	
    @RequestMapping("loginForm")
    public String loginForm() {
        return "login";
    }
    @RequestMapping("logout")
    public String logout(HttpSession session) {
    //	session.removeAttribute("usuarioLogado");
    	session.invalidate();
    	return "redirect:loginForm";
    }
    @RequestMapping("efetuaLogin")
    public ModelAndView efetuaLogin(Usuario usuario, HttpSession session) {
    	String msg = "Seja bem-vindo: ";
    	LoginRep lr = new LoginRep();
    	if (lr.existeUsuario(usuario)){
       // if(usuario.getUsuario().equals("maramajo") && usuario.getSenha().equals("senha")) {
            session.setAttribute("usuarioLogado", usuario);
            System.out.println(usuario.getUsuario());
            System.out.println("senha - " + usuario.getSenha());
            ModelAndView mav = new ModelAndView("redirect:/");
            msg = msg + usuario.getUsuario();
            mav.addObject("msg", msg);
            return mav;
          //  return "redirect:/";
        }
        System.out.println("não existe usuario");
        System.out.println("usuario - " + usuario.getUsuario());
        System.out.println("usuario - " + usuario.getSenha());
        ModelAndView mav = new ModelAndView("login");
        msg = "Usuário inexistente. Tente novamente...";
        mav.addObject("msg", msg);
        return mav;
     //   return "redirect:loginForm";
    }
}

