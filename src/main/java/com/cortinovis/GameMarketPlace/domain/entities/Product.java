  package com.cortinovis.GameMarketPlace.domain.entities;

  import com.cortinovis.GameMarketPlace.domain.valueObjects.OwnerId;
  import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
  import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductDescription;
  import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
  import lombok.Getter;
  import lombok.Setter;
  import org.jetbrains.annotations.Contract;
  import org.jspecify.annotations.NonNull;
  import org.jspecify.annotations.Nullable;

  import java.util.Date;

  @Getter
  public class Product {
    Integer id;
    @Setter
    OwnerId ownerId;
    @Setter
    ProductName name;
    @Setter
    ProductDescription description;
    @Setter
    Price price;
    @Setter
    boolean isEnable;
    @Setter
    Date created_at;
    @Setter
    Date updated_at;

    public Product(Integer id, OwnerId ownerId, ProductName name, ProductDescription description, Price price, boolean isEnable, Date created_at, Date updated_at){
      this.id = id;
      this.ownerId = ownerId;
      this.name = name;
      this.description = description;
      this.price = price;
      this.isEnable = isEnable;
      this.created_at = created_at;
      this.updated_at = updated_at;
    }

    @Contract(value = "_, _, _, _, _ -> new", pure = true)
    public static @NonNull Product create(OwnerId ownerId, ProductName name, ProductDescription description, Price price, boolean isEnable){
      return new Product(null, ownerId, name, description, price, isEnable, null, null);
    }

    @Contract(value = "_, _, _, _, _, _, _, _ -> new", pure = true)
    public static @NonNull Product restore(Integer id, OwnerId ownerId, ProductName name, ProductDescription description, Price price, boolean isEnable, Date created_at, Date updated_at){
      return new Product(id, ownerId, name, description, price, isEnable, created_at, updated_at);
    }

    public @Nullable Object getIsEnable() {
      return isEnable;
    }
  }
