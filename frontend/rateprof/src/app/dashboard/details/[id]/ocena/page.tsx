'use client'

import { useRouter, usePathname, useSearchParams } from 'next/navigation';

export default function Ocena() {
  const router = useRouter();
  const pathname = usePathname()
  const id = useSearchParams();

  const url = `${pathname}?${id}`
  console.log(url)
  return (
    <div className="container">
        <h1>Twoja ocena jakości prowadzenia zajęć:</h1>
        <form className="star-rating">
          <input className="radio-input" type="radio" id="star5" name="star-input" value="5" />
          <label className="radio-label" htmlFor="star5" title="5 stars">5 stars</label>

          <input className="radio-input" type="radio" id="star4" name="star-input" value="4" />
          <label className="radio-label" htmlFor="star4" title="4 stars">4 stars</label>

          <input className="radio-input" type="radio" id="star3" name="star-input" value="3" />
          <label className="radio-label" htmlFor="star3" title="3 stars">3 stars</label>

          <input className="radio-input" type="radio" id="star2" name="star-input" value="2" />
          <label className="radio-label" htmlFor="star2" title="2 stars">2 stars</label>

          <input className="radio-input" type="radio" id="star1" name="star-input" value="1" />
          <label className="radio-label" htmlFor="star1" title="1 star">1 star</label>
        </form>
        <h1>Twoja ocena trudności zaliczenia zajęć:</h1>
        <form className="star-rating">
          <input className="radio-input" type="radio" id="star25" name="star-input" value="5" />
          <label className="radio-label" htmlFor="star25" title="5 stars">5 stars</label>

          <input className="radio-input" type="radio" id="star24" name="star-input" value="4" />
          <label className="radio-label" htmlFor="star24" title="4 stars">4 stars</label>

          <input className="radio-input" type="radio" id="star23" name="star-input" value="3" />
          <label className="radio-label" htmlFor="star23" title="3 stars">3 stars</label>

          <input className="radio-input" type="radio" id="star22" name="star-input" value="2" />
          <label className="radio-label" htmlFor="star22" title="2 stars">2 stars</label>

          <input className="radio-input" type="radio" id="star21" name="star-input" value="1" />
          <label className="radio-label" htmlFor="star21" title="1 star">1 star</label>
        </form>
        <div className="button-container">
          <button type="button">Zatwierdź</button>
          <button type="button" onClick={() => router.back()}>Anuluj</button>
        </div>
        
    </div>
  )
}