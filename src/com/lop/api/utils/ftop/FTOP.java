package com.wandy.api.utils.ftop;

import com.massivecraft.factions.entity.Faction;

public class FTOP
  implements Comparable<FTOP>
{
  private Faction fac;
  private double money;
  private int total;
  
  public FTOP()
  {
    this.fac = null;
    this.total = 0;
    this.money = 0.0D;
  }
  
  public FTOP(Faction f)
  {
    this.fac = f;
    this.total = 0;
    this.money = 0.0D;
  }
  
  public boolean equals(Object obj)
  {
    if (!(obj instanceof FTOP)) {
      return false;
    }
    FTOP other = (FTOP)obj;
    return other.getNome().equals(getNome());
  }
  
  public int compareTo(FTOP other)
  {
    return getNome().compareToIgnoreCase(other.getNome());
  }
  
  public Faction getFaction()
  {
    return this.fac;
  }
  
  public String getNome()
  {
    return this.fac.getName();
  }
  
  public int getTotal()
  {
    return this.total;
  }
  
  public void setTotal(int total)
  {
    this.total = total;
  }
  
  public double getMoney()
  {
    return this.money;
  }
  
  public void setMoney(double value)
  {
    this.money = value;
  }
}
