package cc.raven.util;

import java.lang.reflect.Field;

/**
 * Created by fenghou on 2017/12/12.
 */
public class TransfromUtil {

    public static <M,D> D transform(M model, Class<D> clazz) throws Exception {
        D dto = clazz.newInstance();
        Field[] fields = dto.getClass().getDeclaredFields();
        Class<?> modelClass = model.getClass();
        for (Field field : fields) {
            try {
                Field fd = modelClass.getDeclaredField(field.getName());
                boolean b = fd.isAccessible();
                fd.setAccessible(true);
                Object value = fd.get(model);
                fd.setAccessible(b);
                boolean b1 = field.isAccessible();
                field.setAccessible(true);
                field.set(dto, value);
                field.setAccessible(b1);
            } catch (Throwable e) {

            }
        }
        return dto;
    }
}
