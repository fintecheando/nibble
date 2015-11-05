// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   PreReporteProveedores.java

package org.nibble.service.mostrador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
//import main.BeanError;

public class PreReimprimirFactura extends HttpServlet
{

    public PreReimprimirFactura()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        performTask(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        performTask(request, response);
    }

    public void init()
    {
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
    {
        String strAddress = null;
        RequestDispatcher dispatcher = null;
        try
        {
            strAddress = "/jsp/9_5.jsp";
        }
        catch(Throwable e)
        {
            e.printStackTrace();
            BeanError error = new BeanError();
            error.setErrorMessage(e.getMessage());
            request.setAttribute("error", error);
            strAddress = "/jsp/error.jsp";
        }
        finally
        {
            try
            {
                dispatcher = getServletContext().getRequestDispatcher(strAddress);
                dispatcher.forward(request, response);
            }
            catch(Exception ex)
            {
                System.out.println("FATAL:" + ex.getMessage());
            }
        }
    }
}
