package hu.bearmaster.itm.common.model;

public abstract class GenericVO<ID> {

   private ID id;
   
   public ID getId() {
      return id;
   }
   
   public void setId(ID id) {
      this.id = id;
   }
   
}