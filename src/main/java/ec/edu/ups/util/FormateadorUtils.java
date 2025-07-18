package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class FormateadorUtils {
    /**
     * Formatea una cantidad como moneda según el locale especificado.
     * @param cantidad Valor numérico a formatear.
     * @param locale Locale para la internacionalización.
     * @return Cadena con el formato de moneda correspondiente.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Formatea una fecha según el locale especificado.
     * @param fecha Fecha a formatear.
     * @param locale Locale para la internacionalización.
     * @return Cadena con la fecha formateada.
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }
}
