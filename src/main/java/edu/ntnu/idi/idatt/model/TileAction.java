package edu.ntnu.idi.idatt.model;

public interface TileAction {

  public void perform(Player player);

}
/*Dette er et interface som spesifiserer et standard grensesnitt for ulike typer aksjoner (engelsk: Action) som skal kunne utføres på en spiller som lander på et felt. Konkrete hendelser må defineres, og
må implementere dette grensesnittet (som f.eks. «LadderAction» og «SomeOtherAction». («SomeOtherAction» er tatt med her for å illustrere at man kan ha flere ulike typer actions.)
For eksempel kan et «LadderAction» objekt være tilknyttet spillfelt 23, med destinasjonsfelt 10.
Når en spiller lander på feltet 23, vil aksjonen som er tilknyttet feltet, utføre aksjonen på spilleren,
som i dette tilfelle er å flytte spilleren til nytt felt 10. (m.a.o. spilleren ramler ned stigen fra felt 23
til felt 10).*/
