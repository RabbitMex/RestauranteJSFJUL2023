/**
 * 
 */
package restauranteweb.converters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author Green
 *Convertidor de fechas a local time
 */
@ManagedBean
@ViewScoped
public class LocalDateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return LocalDateTime.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		LocalDateTime localDateTime = (LocalDateTime) value;
		Date fecha = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String fechaConverter = sdf.format(fecha);
		return fechaConverter;
	}

}
