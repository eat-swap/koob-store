package org.eatswap.koobstore.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity(indexes = {
        @Index(value = "id DESC", unique = true)
})
public class BaseEntity {
    @Id
    private Long id;
}
