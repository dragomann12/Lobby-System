package de.dragonhard.lobby.components;

public class colorGenerator {

  private double tick = 0;
  private double lastTick = 0;
  private int tickMaxRange = 500;
  private boolean isEnabled = false;
  private String title = "";



  public String getColor(int tickMax, String title){

    this.tickMaxRange = tickMax;
    this.isEnabled = true;

    while(this.isEnabled) {
      int i = 0;
      for (i = 0; i < this.tickMaxRange; i++) {
        this.lastTick = this.tick * 2;
        this.tick = Math.random() + Math.random() + Math.random() * this.lastTick - (Math.random() * Math.random() + Math .random() + Math.cos(this.tick)) ;
        this.tick = this.tick *2;
        Math.round(this.tick);
        Math.nextUp(this.tick);

        if (this.tick < 10 && this.tick > 0) {
          ConsoleWriter.writeWithTag("[Color-Generator] the Color " + this.tick + " was generated!");
          this.isEnabled = false;
          break;
        }

        if(i == this.tickMaxRange){this.isEnabled = false; ConsoleWriter.writeWithTag("[Color-Generator] The generation of the color was canceled"); return "§b";}
      }

    }
    return " §" + this.tick;
  }

  public void generate(){


  }


}
