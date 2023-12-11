'use client'

import { useRouter } from 'next/navigation';

export default function Komentarz() {
  const router = useRouter();
  return (
    <div className="container">
        <h1>Dodajesz komentarz</h1>
        <input type="text" placeholder="Twój pseudonim" />
        <input type="text" placeholder="Treść komentarza" />
        <div className="button-container">
          <button type="button">Opublikuj</button>
          <button type="button" onClick={() => router.back()}>Anuluj</button>
        </div>
    </div>
  )
}