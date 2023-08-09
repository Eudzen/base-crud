package com.example.crud.utils;

import com.example.crud.exception.*;
import lombok.experimental.*;

@UtilityClass
public class PageValidation {

    /**
     * Проверка на соответствие размера страницы установленному ограничению.
     *
     * @param pageSize - фактический размер страницы
     * @param maxValue - установленное ограничение на pageSize
     *
     * @throws IncorrectArgumentException
     */
    public void checkPageSize(int pageSize, int maxValue) {
        if (pageSize < 1 || pageSize > maxValue) {
            throw new IncorrectArgumentException(String.format(
                "Некорректное количество запрашиваемых объектов: 1 <= %s <= 20",
                pageSize
            ));
        }
    }

}
